package Controller;

import Model.NumberPlate;
import Model.Parking_ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;


public class MainController {

        @FXML
        private TableView<NumberPlate> tablazat;

        @FXML
        private TableColumn<NumberPlate, String> rendszamOszlop;

        @FXML
        private TableColumn<NumberPlate, String> ekezesOszlop; // FXML-ben ekezesOszlop szerepel

        @FXML
        private TableColumn<NumberPlate, String> tavozasOszlop;

        @FXML
        private TableColumn<NumberPlate, Boolean> berletOszlop;

        @FXML
        private TableColumn<NumberPlate, String> cegOszlop;

        @FXML
        private TextField rendszamBekeroKeret;

        @FXML
        private ChoiceBox<String> rendszamSzineKivalaszto;

        @FXML
        private Button button;

        @FXML
        private Label text;
    @FXML
    private void HandleButton(ActionEvent event) {
        System.out.println("Gomb megnyomva!");
    }

        private ObservableList<NumberPlate> autoLista = FXCollections.observableArrayList();

        // Ez a metódus fut le AUTOMATIKUSAN az FXML betöltésekor!
        @FXML
        public void initialize() {
            // 1. Oszlopok összekötése a NumberPlate getterjeivel
            rendszamOszlop.setCellValueFactory(new PropertyValueFactory<>("plate")); // getPlate()
            berletOszlop.setCellValueFactory(new PropertyValueFactory<>("guest"));  // isGuest() -> "guest"

            // 2. Lista hozzárendelése a táblázathoz
            tablazat.setItems(autoLista);

            // 3. Teszt adatsor hozzáadása
            autoLista.add(new NumberPlate(1, "ABC-123", "Személyautó", "H", 'W', true));
            autoLista.add(new NumberPlate(2, "XYZ-987", "Teherautó", "H", 'Y', false));
        }

}
