package app.screen.controller;

import app.history.festival.Festival;
import app.history.person.Person;
import app.history.store.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FestivalListController implements Initializable {
    @FXML
    private GridPane gridPane;

    // Khai báo stage đang hiển thị trên màn hình
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage stage = new Stage();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        // CREATE UI FROM DATA
        int gridCol = 0;
        int gridRow = 0;

        for (Festival item : Store.festivals){
            VBox vBox = new VBox();
            vBox.setMinWidth(200);
            vBox.setMinHeight(80);

            Label festivalName = new Label(item.getName().toString());
            festivalName.getStylesheets().add(this.getClass().getResource("css/list.css").toExternalForm());
            festivalName.getStyleClass().add("text-title");
            festivalName.setCursor(Cursor.HAND);
            festivalName.setWrapText(true);

            Text festivalTime = new Text(item.getTime());
            festivalTime.setWrappingWidth(200);
            festivalTime.getStyleClass().add("text-description");

            vBox.getChildren().addAll(festivalName, festivalTime);

            //constrait grid pane col and row index
            GridPane.setColumnIndex(vBox, gridCol);
            GridPane.setRowIndex(vBox, gridRow);
            gridPane.getChildren().add(vBox);
            System.out.println("" + gridCol + "-" + gridRow);
            gridCol++;
            if (gridCol == 4) {
                gridCol = 0;
                gridRow++;
            }

            festivalName.setOnMouseClicked(e -> {
                // Lấy layout t file fxml
                Parent root = null;
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app/screen/fxml/detail.fxml"));
                // Set controller cho detail page
                FestivalDetailController festivalDetailController = new FestivalDetailController();
                // Truyền ID
                festivalDetailController.setFestivalID(item.getId());
                loader.setController(festivalDetailController);

                // load fxml vào root
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // tạo stage mới và add scene root vào stage mới
                Stage newWindow = new Stage();
                newWindow.setScene(new Scene(root));
                newWindow.show();
//                this.stage.setTitle("Nguoi Ke Su");
//                this.stage.setScene(new Scene(root));
//                this.stage.show();
            });
        }
    }
}
