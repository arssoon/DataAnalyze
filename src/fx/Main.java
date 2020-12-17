package fx;

import controller.MainController;
import controller.Path;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application implements Path {
    @Override
    public void start(Stage stage) {
        try {
            Scene scene;
            scene = getScene(Path.PATH_MAIN);
            scene.getStylesheets().add(Path.PATH_STYLE);
            stage.setScene(scene);

            stage.setTitle("Analiza statystyczna");
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Scene getScene(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        Pane pane = loader.load();

        MainController mainController = loader.getController();
        loader.setController(mainController);

        return new Scene(pane, 800, 600);
    }

}