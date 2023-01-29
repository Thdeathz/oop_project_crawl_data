package app.screen.controller.event;

import app.history.event.Event;
import app.history.person.Person;
import app.history.store.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventDetailController implements Initializable {
    @FXML
    private VBox mainContent;
    public int eventID;

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println(this.eventID);
        int currentID = this.eventID;
        String name = null;
        String time = null;
        String destination = null;
        String description = null;


        // filter data
        for (Event item : Store.events) {
            if (item.getId() == currentID) {
                name = item.getName();
                time = item.getTime();
                destination = item.getDestination();
                description = item.getDescription();
                break;
            }
        }
        //System.out.println("" + name + "" + givenName + father + reign + dateOfDeath + dateOfBirth + description + dynasty);

        //CREATE UI FROM DATA
        ImageView eventImage = new ImageView();
        Image image = null;
        try {
            image = new Image("C:/Users/HLEE/Documents/GitHub/oop_project_crawl_data/src/app/screen/images/person/"+ currentID +".png");
        } catch (Exception e) {
            image = null;
        }
        eventImage.setImage(image);
        eventImage.setFitWidth(400);
        eventImage.setFitHeight(550);

        Label eventName = new Label(name);
        eventName.getStylesheets().add(this.getClass().getResource("css/detail.css").toExternalForm());
        eventName.getStyleClass().add("title");
        eventName.setPadding(new Insets(20, 0, 20, 0));
        eventName.setWrapText(true);

        Label eventTime = new Label("Thời gian: " + time);
        eventTime.getStyleClass().add("content");
        eventTime.setPadding(new Insets(0, 0, 10, 0));
        eventTime.setWrapText(true);

        Label eventDestination = new Label("Địa điểm: " + destination);
        eventDestination.getStyleClass().add("content");
        eventDestination.setPadding(new Insets(0, 0, 10, 0));
        eventDestination.setWrapText(true);

        Label eventDescription = new Label("Thông tin chi tiết: " + description);
        eventDescription.getStyleClass().add("content");
        eventDescription.setPadding(new Insets(0, 0, 10, 0));
        eventDescription.setWrapText(true);

        mainContent.getChildren().addAll(eventImage, eventName, eventTime, eventDestination, eventDescription);

    }
}