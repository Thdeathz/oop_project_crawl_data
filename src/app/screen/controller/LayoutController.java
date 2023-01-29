package app.screen.controller;

import app.history.store.Store;
import app.screen.controller.dynasty.DynastyDetailController;
import app.screen.controller.dynasty.DynastyListController;
import app.screen.controller.event.EventListController;
import app.screen.controller.person.PersonListController;
import app.screen.controller.relic.RelicListController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LayoutController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Button dynastyBtn;

    @FXML
    private BorderPane dynastyPane;

    @FXML
    private Button eventBtn;

    @FXML
    private Button festivalBtn;

    @FXML
    private Button personBtn;

    @FXML
    private Button relicBtn;
    
    private String currentPane;

    @FXML
    void handleBackToHome(ActionEvent event) {
        Transition.startFadeTransition(dynastyPane, "/app/screen/fxml/home.fxml");
    }

    @FXML
    void handleDynastyBtnClicked(ActionEvent event) {
        DynastyListController dynastyController = new DynastyListController(contentArea);
        selectPane(contentArea, dynastyBtn, dynastyController);
        currentPane = "dynasty";
    }

    @FXML
    void handleEventBtnClicked(ActionEvent event) {
        EventListController eventController = new EventListController(contentArea);
        selectPane(contentArea, eventBtn, eventController);
        currentPane = "event";
    }

    @FXML
    void handleFestivalBtnClicked(ActionEvent event) {
        contentArea.getChildren().clear();
        currentPane = "festival";
        festivalBtn.getStyleClass().remove("menu-bar");
        festivalBtn.getStyleClass().add("selected-menu");
    }

    @FXML
    void handlePersonBtnClicked(ActionEvent event) {
        PersonListController personController = new PersonListController(contentArea);
        selectPane(contentArea, personBtn, personController);
        currentPane = "person";
    }

    @FXML
    void handleRelicBtnClicked(ActionEvent event) {
        RelicListController relicController = new RelicListController(contentArea);
        selectPane(contentArea, relicBtn, relicController);
        currentPane = "relic";
    }

    @FXML
    public void initialize() {
        dynastyPane.setOpacity(0);
        Transition.endFadeTransition(dynastyPane);

        currentPane = "dynasty";
        DynastyListController dynastyController = new DynastyListController(contentArea);
        selectPane(contentArea, dynastyBtn, dynastyController);
        currentPane = "dynasty";
    }
    
    public void selectPane(StackPane contentArea, Button menuBtn, Object controller) {
        // clear current pane content
        contentArea.getChildren().clear();
        switch (currentPane) {
            case "dynasty" -> {
                dynastyBtn.getStyleClass().add("menu-bar");
                dynastyBtn.getStyleClass().remove("selected-menu");
            }
            case "event" -> {
                eventBtn.getStyleClass().add("menu-bar");
                eventBtn.getStyleClass().remove("selected-menu");
            }
            case "festival" -> {
                festivalBtn.getStyleClass().add("menu-bar");
                festivalBtn.getStyleClass().remove("selected-menu");
            }
            case "person" -> {
                personBtn.getStyleClass().add("menu-bar");
                personBtn.getStyleClass().remove("selected-menu");
            }
            case "relic" -> {
                relicBtn.getStyleClass().add("menu-bar");
                relicBtn.getStyleClass().remove("selected-menu");
            }
            default -> {}
        }

        // load new pane
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/screen/fxml/list.fxml"));
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
            contentArea.getChildren().setAll(root);

            // set selected menu
            menuBtn.getStyleClass().remove("menu-bar");
            menuBtn.getStyleClass().add("selected-menu");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
