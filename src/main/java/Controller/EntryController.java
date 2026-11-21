package Controller;

import Model.Company;
import database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryController {

    @FXML
    private TextField licensePlateField;

    @FXML
    private TextField nationalityField;

    @FXML
    private RadioButton whiteRadio;

    @FXML
    private RadioButton yellowRadio;

    @FXML
    private RadioButton greenRadio;

    @FXML
    private ToggleGroup colorGroup;

    private Company company;

    @FXML
    public void initialize() {

        // Alapértelmezett szín
        whiteRadio.setSelected(true);

        // Színváltozás figyelése
        colorGroup.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) -> updatePlateColor()
        );

        updatePlateColor();
    }

    private void updatePlateColor() {

        if (whiteRadio.isSelected()) {
            licensePlateField.setStyle(
                    "-fx-control-inner-background: white;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-weight: bold;"
            );

        } else if (yellowRadio.isSelected()) {
            licensePlateField.setStyle(
                    "-fx-control-inner-background: #FFD700;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-weight: bold;"
            );

        } else if (greenRadio.isSelected()) {
            licensePlateField.setStyle(
                    "-fx-control-inner-background: #32CD32;" +
                            "-fx-text-fill: black;" +
                            "-fx-font-weight: bold;"
            );
        }
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @FXML
    private void saveEntry() {

        String licensePlate = licensePlateField.getText()
                .trim().toUpperCase();

        String nationality = nationalityField.getText().trim();

        if (licensePlate.isEmpty() || nationality.isEmpty()) {
            showAlert("Hiányzó adat",
                    "Kérlek, töltsd ki a rendszámot és a nemzetiséget!");
            return;
        }

        RadioButton selectedColor =
                (RadioButton) colorGroup.getSelectedToggle();

        String color = selectedColor.getText();

        String checkSql = """
                SELECT COUNT(*)
                FROM parking
                WHERE company_id = ?
                AND check_out IS NULL
                """;

        try (Connection connection = Database.connect()) {

            connection.setAutoCommit(false);

            try {
                try (PreparedStatement statement =
                             connection.prepareStatement(checkSql)) {

                    statement.setInt(1, company.getId());

                    try (ResultSet result = statement.executeQuery()) {

                        int occupied = result.getInt(1);

                        if (occupied >= company.getParkingspaceNum()) {
                            connection.rollback();
                            showAlert("Nincs szabad hely",
                                    "A parkoló megtelt!");
                            return;
                        }
                    }
                }

                // TODO: autó mentése és parkolás rögzítése

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Adatbázishiba", e.getMessage());
        }
    }

    @FXML
    private void cancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) licensePlateField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}