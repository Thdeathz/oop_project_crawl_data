package app.screen.controller;

import app.history.festival.Festival;
import app.history.person.Person;
import app.history.store.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FestivalDetailController implements Initializable {
    @FXML
    private VBox Detail;

    public void setFestivalID(int festivalID) {
        this.festivalID = festivalID;
    }

    public int festivalID;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(this.festivalID);
        int currentID = this.festivalID;
        String name = null;
        String time = null;
        String destination = null;
        String description = null;



        // filter data
        for (Festival item : Store.festivals) {
            if (item.getId() == currentID) {
                name = item.getName();
                time = item.getTime();
                destination = item.getDestination();
                description = item.getDescription();
                break;
            }
        }
//        System.out.println("" + name + "" + time + destination  + description );

        //CREATE UI FROM DATA
        Label festivalName = new Label(name);
        festivalName.getStylesheets().add(this.getClass().getResource("css/detail.css").toExternalForm());
        festivalName.getStyleClass().add("title");
        festivalName.setPadding(new Insets(20, 0, 20, 0));
        festivalName.setWrapText(true);

        Label festivalTime = new Label("Thời gian: " + time);
        festivalTime.getStyleClass().add("content");
        festivalTime.setPadding(new Insets(0, 0, 10, 0));
        festivalTime.setWrapText(true);

        Label festivalDestination = new Label("Địa điểm: " + destination);
        festivalDestination.getStyleClass().add("content");
        festivalDestination.setPadding(new Insets(0, 0, 10, 0));
        festivalDestination.setWrapText(true);

        Label festivalDescription = new Label("Mô tả: " + description);
        festivalDescription.getStyleClass().add("content");
        festivalDescription.setPadding(new Insets(0, 0, 10, 0));
        festivalDescription.setWrapText(true);

        Detail.getChildren().addAll(festivalName, festivalTime, festivalDestination, festivalDescription);
    }
}
