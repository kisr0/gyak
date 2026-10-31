package Controller;

import Model.NumberPlate;
import Model.Parking_ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;


public class MainController {

    @FXML
    private TableColumn<NumberPlate, String> rendszamOszlop;

    @FXML
    private TableColumn<Parking_ticket, LocalDateTime> erkezesOszlop;

    @FXML
    private TableColumn<Parking_ticket, LocalDateTime> tavozasOszlop;

    @FXML
    private TableColumn<NumberPlate, Boolean> berletOszlop;

    @FXML
    private TableView<NumberPlate> tablazat;

    private ObservableList<NumberPlate> autoLista = FXCollections.observableArrayList();

    @FXML
    public void  setRendszamOszlop()
    {
        rendszamOszlop.setCellValueFactory(new PropertyValueFactory<>("plate"));
        tablazat.setItems(autoLista);
    }

    @FXML
    private void ujSorHozzaadasa() {
        // Új objektum hozzáadása a listához -> A TableView automatikusan frissül!
        autoLista.add(new NumberPlate(1,"ABC-123","Auto","H",'W', true));
    }

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

    @FXML
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
