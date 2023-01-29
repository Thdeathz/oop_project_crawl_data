package app.screen.controller.relic;

import app.history.event.Event;
import app.history.person.Person;
import app.history.relic.Relic;
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

public class RelicDetailController implements Initializable {
    @FXML
    private VBox mainContent;
    public int relicID;

    public void setRelicID(int relicID) {
        this.relicID = relicID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println(this.relicID);
        int currentID = this.relicID;
        String title = null;
        String content = null;
        String address = null;
        String imgUrl = null;


        // filter data
        for (Relic item : Store.relics) {
            if (item.getId() == currentID) {
                title = item.getTitle();
                content = item.getContent();
                address = item.getAddress();
                imgUrl = item.getImgUrl();
                break;
            }
        }

        //CREATE UI FROM DATA
        ImageView relicImage = new ImageView();
        Image image = null;
        try {
            image = new Image("C:/Users/HLEE/Documents/GitHub/oop_project_crawl_data/src/app/screen/images/person/"+ imgUrl);
        } catch (Exception e) {
            image = null;
        }
        relicImage.setImage(image);
        relicImage.setFitWidth(400);
        relicImage.setFitHeight(550);

        Label relicTitle = new Label(title);
        relicTitle.getStylesheets().add(this.getClass().getResource("css/detail.css").toExternalForm());
        relicTitle.getStyleClass().add("title");
        relicTitle.setPadding(new Insets(20, 0, 20, 0));
        relicTitle.setWrapText(true);

        Label relicContent = new Label("Nội dung: " + content);
        relicContent.getStyleClass().add("content");
        relicContent.setPadding(new Insets(0, 0, 10, 0));
        relicContent.setWrapText(true);

        Label relicDestination = new Label("Địa điểm: " + address);
        relicDestination.getStyleClass().add("content");
        relicDestination.setPadding(new Insets(0, 0, 10, 0));
        relicDestination.setWrapText(true);


        mainContent.getChildren().addAll(relicImage, relicTitle, relicDestination, relicContent);
    }
}