package app.screen.controller.dynasty;

import app.history.dynasty.Dynasty;
import app.history.store.Store;
import app.screen.controller.components.ContentController;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DynastyListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    @FXML
    public void initialize() {
        int countPage = Store.dynasties.size()/12 + 1;

        //Create pagination
        Pagination pagination = new Pagination();
        pagination.setPageCount(countPage);
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);

        pagination.setPageFactory((pageIndex) -> {
            int gridCol = 0;
            int gridRow = 0;
            gridPane.getChildren().clear();
            for (Dynasty item: Store.dynasties){
                VBox vBox = new VBox();
                vBox.setMinWidth(200);

                Label dynastyName = new Label(item.getName());
                dynastyName.getStyleClass().add("text-title");
                dynastyName.setCursor(Cursor.HAND);

                vBox.getChildren().addAll(dynastyName);

                //constraint grid pane col and row index
                GridPane.setColumnIndex(vBox, gridCol);
                GridPane.setRowIndex(vBox, gridRow);

                gridPane.getChildren().add(vBox);
                gridCol++;
                if (gridCol == 4){
                    gridCol = 0;
                    gridRow++;
                }

                // xu ly su kien click
                dynastyName.setOnMouseClicked(e -> {
                    DynastyDetailController dynastyDetailController = new DynastyDetailController(item);
                    ContentController.goToDetail(dynastyDetailController);
                });
            }
            return new VBox(gridPane);
        });
        VBox paginationVBox = new VBox(pagination);
        paginationContainer.getChildren().add(paginationVBox);
    }
}
