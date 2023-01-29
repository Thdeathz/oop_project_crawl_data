package app.screen.controller;


import app.history.festival.Festival;
import app.history.person.Person;
import app.history.relic.Relic;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RelicController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        get data from json
        Type listType = new TypeToken<ArrayList<Person>>() {}.getType();
        Gson gson = new Gson();
        ArrayList<Person> items = null;
        try (FileReader reader = new FileReader("src/app/history/store/json/person.json")) {
            items = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Person item : items) {
            System.out.println(""+item.getName());
            //System.out.println("" + item.toString());
        }
    }



}
