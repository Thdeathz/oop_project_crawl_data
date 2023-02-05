package app;

import app.history.storage.Storage;
import app.screen.controller.components.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class app extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/app/screen/fxml/home.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("DutCanTri");
        stage.setResizable(false);
        stage.setScene(scene);
        Transition.setRootStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
