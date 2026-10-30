package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("1. App start() lefutott, FXML betöltése...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"));
            Parent root = loader.load();

            System.out.println("2. FXML sikeresen betöltve!");

            Scene scene = new Scene(root, 400, 300);
            primaryStage.setTitle("Student MVC");
            primaryStage.setScene(scene);

            System.out.println("3. Abalk megjelenítése (show)...");
            primaryStage.show();

        } catch (Exception e) {
            // Kiírja a pontos hibát a konzolra, ha elakad!
            System.out.println("HIBA TÖRTÉNT AZ FXML BETÖLTÉSEKOR:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}