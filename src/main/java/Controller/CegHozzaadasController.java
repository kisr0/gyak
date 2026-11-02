package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class CegHozzaadasController {
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField rendszamBekeroKeret;


    @FXML
    private void cegHozzaadasaButton(ActionEvent event)
    {


        Tab newTab = new Tab(rendszamBekeroKeret.getText());
        newTab.setClosable(false);

        // 2. Tartalom létrehozása a lapfül belsejébe (példaként egy egyszerű nézet)
        StackPane content = new StackPane();
        content.getChildren().add(new Label("Ez az új tab tartalma"));

        // 3. Tartalom hozzárendelése a Tab-hoz
        newTab.setContent(content);

        // 4. Tab hozzáadása a TabPane-hez
        tabPane.getTabs().add(newTab);

        // Optional: Azonnal az új tabra ugrik a nézet
        tabPane.getSelectionModel().select(newTab);

    }
}
