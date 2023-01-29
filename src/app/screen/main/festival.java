package app.screen.main;

import app.screen.controller.FestivalListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class festival extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/screen/fxml/list.fxml"));
            FestivalListController festivalListController = new FestivalListController();
            festivalListController.setStage(primaryStage);
            loader.setController(festivalListController);
//            (P)loader.getController().
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("Nguoi Ke Su");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
