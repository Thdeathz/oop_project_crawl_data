package app.screen.controller;

import app.history.event.Event;
import app.history.festival.Festival;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventListController implements Initializable {
    @FXML
    private GridPane gridPane;

    // Khai báo stage đang hiển thị trên màn hình
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage stage = new Stage();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
