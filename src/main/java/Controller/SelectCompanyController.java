package Controller;

import Model.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import database.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectCompanyController {

    @FXML
    private ListView<Company> companyList;

    @FXML
    public void initialize() {
        setupCompanyList();
        loadCompanies();
    }

    // Lista megjelenítésének beállítása
    private void setupCompanyList() {

        companyList.setCellFactory(list -> new ListCell<>() {

            @Override
            protected void updateItem(Company company, boolean empty) {
                super.updateItem(company, empty);

                if (empty || company == null) {
                    setText(null);
                } else {
                    setText(
                            company.getName()
                                    + " | Parkolóhelyek: "
                                    + company.getParkingspaceNum()
                                    + " | Mozgáskorlátozott helyek: "
                                    + company.getDisabilityPspaceNum()
                                    + " | Óradíj: "
                                    + company.getPricePHour()
                                    + " Ft"
                    );
                }
            }
        });
    }

    // Cégek betöltése az adatbázisból
    private void loadCompanies() {

        String sql = """
                SELECT id,
                       name,
                       parkingspace_num,
                       disabilityPspace_num,
                       price_p_hour,
                       free
                FROM Companies
                ORDER BY name
                """;

        ObservableList<Company> companies =
                FXCollections.observableArrayList();

        try (
                Connection connection = Database.connect();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()
        ) {

            while (result.next()) {

                Company company = new Company(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("parkingspace_num"),
                        result.getInt("disabilityPspace_num"),
                        result.getDouble("price_p_hour"),
                        result.getInt("free")
                );

                companies.add(company);
            }

            companyList.setItems(companies);

        } catch (SQLException e) {

            e.printStackTrace();

            showAlert(
                    Alert.AlertType.ERROR,
                    "Adatbázishiba",
                    "Nem sikerült betölteni a cégeket!\n" + e.getMessage()
            );
        }
    }

    // Kiválasztott cég megnyitása
    @FXML
    private void enterCompany() throws IOException {

        Company selected =
                companyList.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(
                    Alert.AlertType.WARNING,
                    "Figyelem",
                    "Először válassz ki egy céget!"
            );

            return;
        }

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/hu/parkolo/dashboard.fxml")
        );

        if (loader.getLocation() == null) {

            showAlert(
                    Alert.AlertType.ERROR,
                    "FXML hiba",
                    "A dashboard.fxml fájl nem található!"
            );

            return;
        }

        Scene scene = new Scene(loader.load());

       // DashboardController controller = loader.getController();

       // controller.setCompany(selected);

        Stage stage =
                (Stage) companyList.getScene().getWindow();

        stage.setTitle(
                "ParkolóManager - " + selected.getName()
        );

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    // Ablak bezárása
    @FXML
    private void cancel() {

        Stage stage =
                (Stage) companyList.getScene().getWindow();

        stage.close();
    }

    // Üzenetek megjelenítése
    private void showAlert(
            Alert.AlertType type,
            String title,
            String message
    ) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
