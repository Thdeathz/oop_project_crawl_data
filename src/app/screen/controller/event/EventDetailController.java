package app.screen.controller.event;

import app.history.event.Event;
import app.history.person.Person;
import app.history.store.Store;
import app.screen.controller.base.DetailBaseController;
import app.screen.controller.components.ContentController;
import app.screen.controller.person.PersonDetailController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class EventDetailController extends DetailBaseController {
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

            sideBarBtn.setOnAction(event -> {
                currentSideBarBtn.getStyleClass().remove("current-content-btn");
                sideBarBtn.getStyleClass().add("current-content-btn");
                currentSideBarBtn = sideBarBtn;
                initMainContent(item);
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

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        int row = 0;
        int column = 0;
        for(Person item: eventData.getRelativePersons()) {
            VBox vBox = new VBox();
            vBox.setMinWidth(200);
            GridPane.setRowIndex(vBox, row);
            GridPane.setColumnIndex(vBox, column);

            Label kingName = new Label(item.getName());
            kingName.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
            kingName.getStyleClass().add("king-name");
            kingName.setCursor(Cursor.HAND);

            kingName.setOnMouseClicked(e -> {
                PersonDetailController personDetailController = new PersonDetailController(item);
                ContentController.goToDetail(personDetailController);
            });

            ImageView avatar = new ImageView();
            Image image2 = null;
            try {
                image2 = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/person/"+ item.getId() +".png");
            } catch (Exception e) {
                image2 = null;
            }
            avatar.setImage(image2);
            avatar.setFitWidth(150);
            avatar.setFitHeight(200);

            vBox.getChildren().addAll(avatar, kingName);
            gridPane.getChildren().add(vBox);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                eventName,
                eventTime,
                eventDestination,
                eventImage,
                eventDescription,
                gridPane
        );
    }
}