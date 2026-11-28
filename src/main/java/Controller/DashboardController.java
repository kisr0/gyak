package Controller;

import database.Database;
import Model.Company;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {

    @FXML
    private Label companyNameLabel;

    @FXML
    private Label totalSpacesLabel;

    @FXML
    private Label disabilitySpacesLabel;

    @FXML
    private Label occupiedSpacesLabel;

    @FXML
    private Label freeSpacesLabel;

    @FXML
    private Label priceLabel;

    private Company company;

    public void setCompany(Company company) {
        this.company = company;
        loadDashboard();
    }

    @FXML
    private void openEntry() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/entry.fxml")
        );

        Scene scene = new Scene(loader.load());

        EntryController controller = loader.getController();
        controller.setCompany(company);

        Stage stage = new Stage();
        stage.setTitle("Autó behajtása");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(companyNameLabel.getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        // Frissítjük az adatokat az ablak bezárása után
        loadDashboard();
    }

    @FXML
    private void openSearch() throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/search.fxml")
        );

        Scene scene = new Scene(loader.load());

        SearchController controller = loader.getController();
        controller.setCompany(company);

        Stage stage = new Stage();
        stage.setTitle("Rendszám kereső");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(companyNameLabel.getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        loadDashboard();
    }

    private void loadDashboard() {

        if (company == null) {
            return;
        }

        companyNameLabel.setText(company.getName());

        totalSpacesLabel.setText(
                String.valueOf(company.getParkingspaceNum())
        );

        disabilitySpacesLabel.setText(
                String.valueOf(company.getDisabilityPspaceNum())
        );

        priceLabel.setText(
                company.getPricePHour() + " Ft / óra"
        );

        String sql = """
                SELECT COUNT(*)
                FROM parking
                WHERE company_id = ?
                AND check_out IS NULL
                """;

        try (Connection connection = Database.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, company.getId());

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    int occupied = result.getInt(1);
                    int free = company.getParkingspaceNum() - occupied;

                    occupiedSpacesLabel.setText(
                            String.valueOf(occupied)
                    );

                    freeSpacesLabel.setText(
                            String.valueOf(free)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            occupiedSpacesLabel.setText("Hiba");
            freeSpacesLabel.setText("Hiba");
        }
    }
}