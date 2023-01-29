package app.screen.controller.event;

import app.history.event.Event;
import app.history.person.Person;
import app.history.store.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class EventDetailController {
    @FXML
    private VBox mainContent;
    @FXML
    private VBox sideBar;

    private Button currentSideBarBtn;

    private final Event eventData;

    public EventDetailController(Event eventData) {
        this.eventData = eventData;
    }

    @FXML
    public void initialize() {
        initMainContent(this.eventData);
        initSideBar();

    }

    public void initSideBar() {
        for (Event item: Store.events) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getName());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if(Objects.equals(item.getId(), eventData.getId())) {
                currentSideBarBtn = sideBarBtn;
                sideBarBtn.getStyleClass().add("current-content-btn");
            }

            sideBarBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    currentSideBarBtn.getStyleClass().remove("current-content-btn");
                    sideBarBtn.getStyleClass().add("current-content-btn");
                    currentSideBarBtn = sideBarBtn;
                    initMainContent(item);
                }
            });

            sideBar.getChildren().add(sideBarBtn);
        }
    }

    //CREATE UI FROM DATA
    public void initMainContent(Event eventData) {
        ImageView eventImage = new ImageView();
        Image image = null;
        try {
            image = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/"+ eventData.getImgPath());
        } catch (Exception e) {
            image = null;
        }
        eventImage.setImage(image);
        eventImage.setFitWidth(400);
        eventImage.setFitHeight(550);

        Label eventName = new Label(eventData.getName());
        eventName.getStylesheets().add(this.getClass().getResource("css/detail.css").toExternalForm());
        eventName.getStyleClass().add("title");
        eventName.setPadding(new Insets(20, 0, 20, 0));
        eventName.setWrapText(true);

        Label eventTime = new Label("Thời gian: " + eventData.getTime());
        eventTime.getStyleClass().add("content");
        eventTime.setPadding(new Insets(0, 0, 10, 0));
        eventTime.setWrapText(true);

        Label eventDestination = new Label("Địa điểm: " + eventData.getDestination());
        eventDestination.getStyleClass().add("content");
        eventDestination.setPadding(new Insets(0, 0, 10, 0));
        eventDestination.setWrapText(true);

        Label eventDescription = new Label("Thông tin chi tiết: " + eventData.getDescription());
        eventDescription.getStyleClass().add("content");
        eventDescription.setPadding(new Insets(0, 0, 10, 0));
        eventDescription.setWrapText(true);

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                eventName,
                eventTime,
                eventDestination,
                eventImage,
                eventDescription
        );
    }
}