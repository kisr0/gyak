package Controller;

import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MainController {
    @FXML
    private TextField inputTextField;
    @FXML
    private ChoiceBox<String> rendszamSzineKivalaszto;
    @FXML
    private Label text;
    @FXML
    private TextField rendszamBekeroKeret;
    @FXML
    private Button rendszamButton;

    private void Initialize() {
        rendszamSzineKivalaszto.getItems().addAll("Fehér", "Zöld");
        rendszamSzineKivalaszto.setValue("Fehér");
    }

    // @FXML
    private void HandleRendszam() {
        String a = rendszamBekeroKeret.getText();
        a = a.toUpperCase();


    }
}


