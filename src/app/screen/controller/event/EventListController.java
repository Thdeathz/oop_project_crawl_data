package app.screen.controller.event;

import app.history.event.Event;
import app.history.store.Store;
import app.screen.controller.components.ContentController;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.*;

public class EventListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    // CREATE UI FROM DATA
    @FXML
    public void initialize() {
        int countPage = Store.events.size()/12 + 1;

        //Create pagination
        Pagination pagination = new Pagination();
        pagination.setPageCount(countPage);
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);

        pagination.setPageFactory((pageIndex) -> {
            int gridCol = 0;
            int gridRow = 0;
            gridPane.getChildren().clear();
            for (Event item: Store.events.subList(12* pagination.getCurrentPageIndex(), 12* pagination.getCurrentPageIndex()+11)){
                VBox vBox = new VBox();
                vBox.setMinWidth(200);

                Label eventName = new Label(item.getName().toString());
                eventName.getStylesheets().add(this.getClass().getResource("css/list.css").toExternalForm());
                eventName.getStyleClass().add("text-title");
                eventName.setCursor(Cursor.HAND);
                eventName.setMaxWidth(200);
                eventName.setWrapText(true);

                ImageView eventImage = new ImageView();
                Image image = null;
                try {
                    image = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/"+ item.getImgPath());
                } catch (Exception e) {
                    image = null;
                }
                eventImage.setImage(image);
                eventImage.setFitWidth(200);
                eventImage.setFitHeight(250);

                Text eventTime = new Text(item.getTime());
                eventTime.setWrappingWidth(200);
                eventTime.getStyleClass().add("text-description");

                vBox.getChildren().addAll(eventImage, eventName,  eventTime);
                vBox.setMaxWidth(200);

                //constrait grid pane col and row index
                GridPane.setColumnIndex(vBox, gridCol);
                GridPane.setRowIndex(vBox, gridRow);

                gridPane.getChildren().add(vBox);
                gridCol++;
                if (gridCol == 4){
                    gridCol = 0;
                    gridRow++;
                }

                // xu ly su kien click
                eventName.setOnMouseClicked(e -> {
                    EventDetailController eventDetailController = new EventDetailController(item);
                    ContentController.goToDetail(eventDetailController);
                });
            }
            return new VBox(gridPane);
        });
        VBox paginationVBox = new VBox(pagination);
        paginationContainer.getChildren().add(paginationVBox);
    }
}