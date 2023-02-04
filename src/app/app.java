package app;

import app.history.store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class app extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Store.init();
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/app/screen/fxml/home.fxml")));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("DutCanTri");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}