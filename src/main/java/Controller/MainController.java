package Controller;

import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class MainController {
    @FXML
    private TextField inputTextField;

    @FXML
    private Label text;

    @FXML
    private void HandleButton(ActionEvent event) {
        text.setText("Cigány");
    }


    // Tab controller
    /*
    @FXML
    private TableView<RowData> myTableView;

    @FXML
    private TableColumn<RowData, String> colName;

    @FXMLaaa
    private TableColumn<RowData, String> colStatus;

    // Ebben a listában tároljuk a táblázat adatait
    private ObservableList<RowData> dataList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // 1. Oszlopok összekötése az Adatmodell getterjeivel (getName, getStatus)
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 2. Adatlista hozzárendelése a táblázathoz
        myTableView.setItems(dataList);

        // 3. Sorok egyedi színezése a státusz alapján
        myTableView.setRowFactory(tv -> new TableRow<RowData>() {
            @Overridelogout
            protected void updateItem(RowData item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setStyle("");
                } else if ("ERROR".equals(item.getStatus())) {
                    setStyle("-fx-background-color: #ffcdd2;"); // Pirosas háttér
                } else if ("OK".equals(item.getStatus())) {
                    setStyle("-fx-background-color: #c8e6c9;"); // Zöldes háttér
                } else {
                    setStyle(""); // Alapértelmezett háttér
                }
            }
        });
    }

    // Új sor hozzáadása gombnyomásra vagy eseményre
    public void addRow(String name, String status) {
        dataList.add(new RowData(name, status));
        // A görgetés automatikusan az új elemhez ugrik, ha szükséges:
        myTableView.scrollTo(dataList.size() - 1);
    }
*/

}
