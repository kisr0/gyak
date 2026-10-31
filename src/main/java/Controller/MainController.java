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
    @FXML
    private void initialize() {
        rendszamSzineKivalaszto.getItems().addAll("Fehér", "Zöld");
        rendszamSzineKivalaszto.setValue("Fehér");
    }
    @FXML
    private void HandleButton(ActionEvent event) {
        String rendszam = rendszamBekeroKeret.getText();
        if (rendszam != null) {
            rendszam = rendszam.toUpperCase();
        }

        String szin = rendszamSzineKivalaszto.getValue();
        //Teszt
        System.out.println("Rendszám: " + rendszam + ", Szín: " + szin);

        if (text != null) {
            //Mentés
        }
    }

    }


