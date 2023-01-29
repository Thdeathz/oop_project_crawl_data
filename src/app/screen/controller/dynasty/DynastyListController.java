package app.screen.controller.dynasty;

import app.history.dynasty.Dynasty;
import app.history.store.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DynastyListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    private final StackPane contentArea;

    public DynastyListController(StackPane contentArea) {
        this.contentArea = contentArea;
    }

    // Khai báo stage đang hiển thị trên màn hình
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
                dynastyName.getStylesheets().add(this.getClass().getResource("css/list.css").toExternalForm());
                dynastyName.getStyleClass().add("text-title");
                dynastyName.setCursor(Cursor.HAND);

                vBox.getChildren().addAll(dynastyName);

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
                dynastyName.setOnMouseClicked(e -> {
                    // Lấy layout t file fxml
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app/screen/fxml/detail.fxml"));
                    // Set controller cho detail page
                    DynastyDetailController dynastyDetailController = new DynastyDetailController(item);
                    loader.setController(dynastyDetailController);
                    Parent root = null;
                    try {
                        root = loader.load();
                        contentArea.getChildren().clear();
                        contentArea.getChildren().setAll(root);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
            }
            return new VBox(gridPane);
        });
        VBox paginationVBox = new VBox(pagination);
        paginationContainer.getChildren().add(paginationVBox);
    }
}
