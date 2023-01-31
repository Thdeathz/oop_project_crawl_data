package app.screen.controller.relic;

import app.history.relic.Relic;
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

import java.io.*;

public class RelicListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    @FXML
    public void initialize() {
        // CREATE UI FROM DATA
        int countPage = Store.relics.size()/12 + 1;

        //Create pagination
        Pagination pagination = new Pagination();
        pagination.setPageCount(countPage);
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);

        pagination.setPageFactory((pageIndex) -> {
            int gridCol = 0;
            int gridRow = 0;
            gridPane.getChildren().clear();
            for (Relic item: Store.relics.subList(12* pagination.getCurrentPageIndex(), 12* pagination.getCurrentPageIndex()+11)){
                VBox vBox = new VBox();
                vBox.setMinWidth(200);

                Label relicTitle = new Label(item.getTitle());
                relicTitle.getStylesheets().add(this.getClass().getResource("css/list.css").toExternalForm());
                relicTitle.getStyleClass().add("text-title");
                relicTitle.setCursor(Cursor.HAND);
                relicTitle.setMaxWidth(200);
                relicTitle.setWrapText(true);

                ImageView relicImage = new ImageView();
                Image image = null;
                try {
                    image = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/relic/"+ item.getImgUrl());
                } catch (Exception e) {
                    image = null;
                }
                relicImage.setImage(image);
                relicImage.setFitWidth(200);
                relicImage.setFitHeight(250);

                vBox.getChildren().addAll(relicImage, relicTitle);
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
                relicTitle.setOnMouseClicked(e -> {
                    RelicDetailController relicDetailController = new RelicDetailController(item);
                    ContentController.goToDetail(relicDetailController);
                });
            }
            return new VBox(gridPane);
        });
        VBox paginationVBox = new VBox(pagination);
        paginationContainer.getChildren().add(paginationVBox);

    }
}