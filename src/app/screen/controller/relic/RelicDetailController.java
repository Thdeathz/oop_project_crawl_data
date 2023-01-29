package app.screen.controller.relic;

import app.history.person.Person;
import app.history.relic.Relic;
import app.history.store.Store;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class RelicDetailController {
    @FXML
    private VBox mainContent;
    @FXML
    private VBox sideBar;

    private Button currentSideBarBtn;

    private final Relic relicData;

    public RelicDetailController(Relic relicData) {
        this.relicData = relicData;
    }

    @FXML
    public void initialize() {
        initMainContent(this.relicData);
        initSideBar();
    }

    public void initSideBar() {
        for (Relic item: Store.relics) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getTitle());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if(Objects.equals(item.getId(), relicData.getId())) {
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

    public void initMainContent(Relic relicData) {
        //CREATE UI FROM DATA
        ImageView relicImage = new ImageView();
        Image image = null;
        try {
            image = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/relic/"+ relicData.getImgUrl());
        } catch (Exception e) {
            image = null;
        }
        relicImage.setImage(image);
        relicImage.setFitWidth(400);
        relicImage.setFitHeight(550);

        Label relicTitle = new Label(relicData.getTitle());
        relicTitle.getStylesheets().add(this.getClass().getResource("css/detail.css").toExternalForm());
        relicTitle.getStyleClass().add("title");
        relicTitle.setPadding(new Insets(20, 0, 20, 0));
        relicTitle.setWrapText(true);

        Label relicContent = new Label(""+relicData.getContent());
        relicContent.getStyleClass().add("content");
        relicContent.setPadding(new Insets(0, 0, 10, 0));
        relicContent.setWrapText(true);

        Label relicDestination = new Label("Địa điểm: " + relicData.getAddress());
        relicDestination.getStyleClass().add("content");
        relicDestination.setPadding(new Insets(0, 0, 10, 0));
        relicDestination.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        int row = 0;
        int column = 0;
        for(Person item: relicData.getRelatedHistoricalPerson()) {
            VBox vBox = new VBox();
            vBox.setMinWidth(200);
            GridPane.setRowIndex(vBox, row);
            GridPane.setColumnIndex(vBox, column);

            Label kingName = new Label(item.getName());
            kingName.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
            kingName.getStyleClass().add("king-name");
            kingName.setCursor(Cursor.HAND);

            ImageView avatar = new ImageView();
            Image personImg = null;
            try {
                personImg = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/person/"+ item.getId() +".png");
            } catch (Exception e) {
                personImg = null;
            }
            avatar.setImage(personImg);
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
                relicTitle,
                relicDestination,
                relicImage,
                relicContent,
                gridPane
        );
    }
}