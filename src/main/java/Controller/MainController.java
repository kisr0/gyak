package Controller;

import Model.NumberPlate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController {

    @FXML
    private TableView<NumberPlate> tablazat;

    @FXML
    private TableColumn<NumberPlate, String> rendszamOszlop;

    @FXML
    private TableColumn<NumberPlate, String> ekezesOszlop;

    @FXML
    private TableColumn<NumberPlate, String> tavozasOszlop;

    @FXML
    private TableColumn<NumberPlate, Boolean> berletOszlop;

    @FXML
    private TableColumn<NumberPlate, String> cegOszlop;

    @FXML
    private TextField inputTextField;

    @FXML
    private TextField rendszamBekeroKeret;

    @FXML
    private ChoiceBox<String> rendszamSzineKivalaszto;

    @FXML
    private Button button;

    @FXML
    private Label text;

    private ObservableList<NumberPlate> autoLista = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 1. ChoiceBox feltöltése
        if (rendszamSzineKivalaszto != null) {
            rendszamSzineKivalaszto.getItems().addAll("Fehér", "Zöld");
            rendszamSzineKivalaszto.setValue("Fehér");
        }

        // 2. Oszlopok összekötése a NumberPlate getterjeivel
        if (rendszamOszlop != null) {
            rendszamOszlop.setCellValueFactory(new PropertyValueFactory<>("plate"));
        }
        if (berletOszlop != null) {
            berletOszlop.setCellValueFactory(new PropertyValueFactory<>("guest"));
        }

        // 3. Lista hozzárendelése a táblázathoz
        if (tablazat != null) {
            tablazat.setItems(autoLista);

            // Teszt adatsorok hozzáadása
            autoLista.add(new NumberPlate(1, "ABC-123", "Személyautó", "H", 'W', true));
            autoLista.add(new NumberPlate(2, "XYZ-987", "Teherautó", "H", 'Y', false));
        }
    }

    @FXML
    private void HandleButton(ActionEvent event) {
        String rendszam = "";
        if (rendszamBekeroKeret != null) {
            rendszam = rendszamBekeroKeret.getText();
            if (rendszam != null) {
                rendszam = rendszam.toUpperCase();
            }
        }

        String szin = "";
        if (rendszamSzineKivalaszto != null) {
            szin = rendszamSzineKivalaszto.getValue();
        }

        autoLista.add(new NumberPlate(1, rendszam, "Személyautó", "H", 'F', true));
    }
}