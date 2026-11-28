package Controller;

import Model.Company;
import Model.ParkingRecord;
import database.Database;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<ParkingRecord> parkingTable;

    @FXML
    private TableColumn<ParkingRecord, String> plateColumn;

    @FXML
    private TableColumn<ParkingRecord, String> nationalityColumn;

    @FXML
    private TableColumn<ParkingRecord, String> colorColumn;

    @FXML
    private TableColumn<ParkingRecord, String> checkInColumn;

    @FXML
    private TableColumn<ParkingRecord, String> checkOutColumn;

    @FXML
    private TableColumn<ParkingRecord, String> durationColumn;

    @FXML
    private TableColumn<ParkingRecord, String> statusColumn;

    private Company company;
    private Timeline autoRefresh;
    private final ObservableList<ParkingRecord> masterData = FXCollections.observableArrayList();
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

    @FXML
    public void initialize() {
        plateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        FilteredList<ParkingRecord> filteredData = new FilteredList<>(masterData, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(record -> {
                if (newValue == null || newValue.isBlank()) {
                    return true;
                }
                String filter = newValue.trim().toLowerCase();
                return record.getLicensePlate().toLowerCase().contains(filter)
                        || record.getNationality().toLowerCase().contains(filter);
            });
        });

        parkingTable.setItems(filteredData);

        // 30 másodpercenként automatikusan frissíti a bent töltött időt
        autoRefresh = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(30), e -> loadParkingData())
        );
        autoRefresh.setCycleCount(Animation.INDEFINITE);
        autoRefresh.play();
    }

    public void setCompany(Company company) {
        this.company = company;
        loadParkingData();
    }

    @FXML
    private void handleCheckOut() {
        ParkingRecord selected = parkingTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Nincs kijelölve jármű",
                    "Kattints egy sorra a táblázatban a kihajtatáshoz!");
            return;
        }

        if (!"Bent parkol".equals(selected.getStatus())) {
            showAlert(Alert.AlertType.INFORMATION, "Már kihajtott",
                    "Ez a jármű (" + selected.getLicensePlate() + ") már korábban kihajtott.");
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        String sql = """
                UPDATE parking
                SET check_out = ?
                WHERE company_id = ?
                  AND check_out IS NULL
                  AND car_id = (SELECT id FROM cars WHERE licenseplate = ? LIMIT 1)
                """;

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, now.toString());
            statement.setInt(2, company.getId());
            statement.setString(3, selected.getLicensePlate());

            int updated = statement.executeUpdate();
            if (updated > 0) {
                LocalDateTime checkInTime = LocalDateTime.parse(selected.getCheckIn(), DISPLAY_FORMAT);
                long minutes = Math.max(0, Duration.between(checkInTime, now).toMinutes());
                double cost = calculateCost(minutes, company.getPricePHour());

                loadParkingData();

                showAlert(Alert.AlertType.INFORMATION, "Sikeres kihajtás",
                        "Rendszám: " + selected.getLicensePlate() + "\n" +
                                "Bent töltött idő: " + formatDuration(Duration.ofMinutes(minutes)) + "\n" +
                                "Fizetendő díj: " + Math.round(cost) + " Ft");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Adatbázishiba", e.getMessage());
        }
    }

    private double calculateCost(long minutes, double pricePerHour) {
        if (minutes < 60) {
            return 0.0;
        }
        long extraMinutes = minutes - 60;
        long billableHours = (long) Math.ceil(extraMinutes / 60.0);
        return billableHours * pricePerHour;
    }

    private void loadParkingData() {
        if (company == null) {
            return;
        }

        masterData.clear();

        String sql = """
                SELECT c.licenseplate, c.nationality, c.licenseplate_color,
                       p.check_in, p.check_out
                FROM parking p
                JOIN cars c ON p.car_id = c.id
                WHERE p.company_id = ?
                ORDER BY p.check_in DESC
                """;

        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, company.getId());

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String plate = rs.getString("licenseplate");
                    String nationality = rs.getString("nationality");
                    String color = rs.getString("licenseplate_color");
                    String checkInRaw = rs.getString("check_in");
                    String checkOutRaw = rs.getString("check_out");

                    LocalDateTime checkInTime = LocalDateTime.parse(checkInRaw);
                    LocalDateTime endTime = (checkOutRaw != null && !checkOutRaw.isBlank())
                            ? LocalDateTime.parse(checkOutRaw)
                            : LocalDateTime.now();

                    String checkInFormatted = checkInTime.format(DISPLAY_FORMAT);
                    String checkOutFormatted = (checkOutRaw != null && !checkOutRaw.isBlank())
                            ? endTime.format(DISPLAY_FORMAT)
                            : "-";

                    String durationText = formatDuration(Duration.between(checkInTime, endTime));
                    String status = (checkOutRaw == null || checkOutRaw.isBlank())
                            ? "Bent parkol"
                            : "Kihajtott";

                    masterData.add(new ParkingRecord(
                            plate, nationality, color,
                            checkInFormatted, checkOutFormatted,
                            durationText, status
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String formatDuration(Duration duration) {
        long totalMinutes = Math.max(0, duration.toMinutes());
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;

        if (hours > 0) {
            return hours + " óra " + minutes + " perc";
        }
        return minutes + " perc";
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void closeWindow() {
        if (autoRefresh != null) {
            autoRefresh.stop();
        }
        Stage stage = (Stage) searchField.getScene().getWindow();
        stage.close();
    }
}