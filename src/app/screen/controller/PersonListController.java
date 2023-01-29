package app.screen.controller;

import app.history.person.Person;
import app.history.store.Store;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.text.LabelView;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PersonListController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    // Khai báo stage đang hiển thị trên màn hình
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage stage = new Stage();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        // CREATE UI FROM DATA



        //Count data
        int countData = 0;
        for (Person item : Store.persons){
            countData++;
        }
        int countPage = countData/12 + 1;
        System.out.println(countPage);

        //Create pagination
        Pagination pagination = new Pagination();
        pagination.setPageCount(countPage);
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);



        pagination.setPageFactory((pageIndex) -> {
            int gridCol = 0;
            int gridRow = 0;
            gridPane.getChildren().clear();
            System.out.println("" + (12* pagination.getCurrentPageIndex()) + " - " + (12* pagination.getCurrentPageIndex()+11));
            for (Person item: Store.persons.subList(12* pagination.getCurrentPageIndex(), 12* pagination.getCurrentPageIndex()+11)){
                VBox vBox = new VBox();
                vBox.setMinWidth(200);
//            vBox.setMinHeight(200);

                Label personName = new Label(item.getName().toString());
                personName.getStylesheets().add(this.getClass().getResource("css/list.css").toExternalForm());
                personName.getStyleClass().add("text-title");
                personName.setCursor(Cursor.HAND);



                ImageView avatar = new ImageView();
                Image image = null;
                try {
                    image = new Image("C:/Users/HLEE/Documents/GitHub/oop_project_crawl_data/src/app/screen/images/person/"+ item.getId() +".png");
                } catch (Exception e) {
                    image = null;
                }
                avatar.setImage(image);
                avatar.setFitWidth(200);
                avatar.setFitHeight(250);



                Text dynastyName = new Text(item.getDynastyName());
                dynastyName.setWrappingWidth(200);
                dynastyName.getStyleClass().add("text-description");

                Text date = new Text( item.getDateOfBirth().toString() + " - " + item.getDateOfDeath().toString() );
                date.setWrappingWidth(200);
                date.getStyleClass().add("text-description");

                vBox.getChildren().addAll(avatar, personName,  dynastyName, date);

                //constrait grid pane col and row index
                GridPane.setColumnIndex(vBox, gridCol);
                GridPane.setRowIndex(vBox, gridRow);

                gridPane.getChildren().add(vBox);
//                System.out.println("" + gridCol + "-" + gridRow);
                gridCol++;
                if (gridCol == 4){
                    gridCol = 0;
                    gridRow++;
                }

                // xu ly su kien click
                personName.setOnMouseClicked(e -> {
                    // Lấy layout t file fxml
                    Parent root = null;
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/app/screen/fxml/detail.fxml"));
                    // Set controller cho detail page
                    PersonDetailController personDetailController = new PersonDetailController();
                    // Truyền ID
                    personDetailController.setPersonID(item.getId());
                    loader.setController(personDetailController);

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
            return new VBox(gridPane);
        });
        VBox paginationVBox = new VBox(pagination);
        paginationContainer.getChildren().add(paginationVBox);

    }
}
