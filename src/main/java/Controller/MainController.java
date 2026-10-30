package Controller;

import javafx.fxml.FXML;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.w3c.dom.Text;

public class MainController {

    @FXML
    private Label text;

    @FXML
    private void HandleButton(ActionEvent event) {
        text.setText("Cigány");
    }
}
