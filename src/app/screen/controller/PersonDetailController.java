package app.screen.controller;

import app.history.person.Person;
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

public class PersonDetailController implements Initializable {
    @FXML
    private VBox Detail;
    public int personID;

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println(this.personID);
        int currentID = this.personID;
        String name = null;
        String givenName = null;
        String father = null;
        String reign = null;
        String dateOfBirth = null;
        String dateOfDeath = null;
        String description = null;
        String dynasty = null;


        // filter data
        for (Person item : Store.persons) {
            if (item.getId() == currentID) {
                name = item.getName();
                givenName = item.getGivenName();
                father = item.getFather();
                reign = item.getReign();
                dateOfBirth = item.getDateOfBirth();
                dateOfDeath = item.getDateOfDeath();
                description = item.getDescription();
                dynasty = item.getDynastyName();
                break;
            }
        }
        //System.out.println("" + name + "" + givenName + father + reign + dateOfDeath + dateOfBirth + description + dynasty);

        //CREATE UI FROM DATA
        ImageView avatar = new ImageView();
        Image image = null;
        try {
            image = new Image("C:/Users/HLEE/Documents/GitHub/oop_project_crawl_data/src/app/screen/images/person/"+ currentID +".png");
        } catch (Exception e) {
            image = null;
        }
        avatar.setImage(image);
        avatar.setFitWidth(400);
        avatar.setFitHeight(550);

        Label personName = new Label(name);
        personName.getStylesheets().add(this.getClass().getResource("css/detail.css").toExternalForm());
        personName.getStyleClass().add("title");
        personName.setPadding(new Insets(20, 0, 20, 0));
        personName.setWrapText(true);

        Label personGivenName = new Label("Tên gọi khác: " + givenName);
        personGivenName.getStyleClass().add("content");
        personGivenName.setPadding(new Insets(0, 0, 10, 0));
        personGivenName.setWrapText(true);

        Label personFather = new Label("Tên cha: " + father);
        personFather.getStyleClass().add("content");
        personFather.setPadding(new Insets(0, 0, 10, 0));
        personFather.setWrapText(true);

        Label personReign = new Label("Thời gian cai trị: " + reign);
        personReign.getStyleClass().add("content");
        personReign.setPadding(new Insets(0, 0, 10, 0));
        personReign.setWrapText(true);

        Label personDateOfBirth = new Label("Sinh: " + dateOfBirth);
        personDateOfBirth.getStyleClass().add("content");
        personDateOfBirth.setPadding(new Insets(0, 0, 10, 0));
        personDateOfBirth.setWrapText(true);

        Label personDateOfDeath = new Label("Mất: " + dateOfDeath);
        personDateOfDeath.getStyleClass().add("content");
        personDateOfDeath.setPadding(new Insets(0, 0, 10, 0));
        personDateOfDeath.setWrapText(true);

        Text personDescription = new Text(description);
        personDescription.getStyleClass().add("description");
        personDescription.setWrappingWidth(500);

        Label personDynasty = new Label("Triều Đại: " + dynasty);
        personDynasty.getStyleClass().add("content");
        personDynasty.setPadding(new Insets(10, 0, 0, 0));
        personDynasty.setWrapText(true);

        Detail.getChildren().addAll(avatar, personName, personGivenName, personFather, personReign, personDateOfBirth, personDateOfDeath, personDescription, personDynasty);

    }
}
