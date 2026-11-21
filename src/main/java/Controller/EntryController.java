package Controller;

import Model.Company;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        // TODO: implement entry saving
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