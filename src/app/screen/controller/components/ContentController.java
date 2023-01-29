package app.screen.controller.components;

import app.screen.controller.base.DetailBaseController;
import app.screen.controller.dynasty.DynastyDetailController;
import app.screen.controller.dynasty.DynastyListController;
import app.screen.controller.event.EventDetailController;
import app.screen.controller.event.EventListController;
import app.screen.controller.festival.FestivalController;
import app.screen.controller.person.PersonDetailController;
import app.screen.controller.person.PersonListController;
import app.screen.controller.relic.RelicDetailController;
import app.screen.controller.relic.RelicListController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ContentController {

    @FXML
    private static StackPane contentArea;

    @FXML
    private static Button dynastyBtn;

    @FXML
    private static Button eventBtn;

    @FXML
    private static Button festivalBtn;

    @FXML
    private static Button personBtn;

    @FXML
    private static Button relicBtn;

    private static String currentPane = "unknown";

    public static void setContentArea(StackPane contentArea) { ContentController.contentArea = contentArea; }
    public static void setDynastyBtn(Button dynastyBtn) { ContentController.dynastyBtn = dynastyBtn; }
    public static void setEventBtn(Button eventBtn) { ContentController.eventBtn = eventBtn; }
    public static void setFestivalBtn(Button festivalBtn) { ContentController.festivalBtn = festivalBtn; }
    public static void setPersonBtn(Button personBtn) { ContentController.personBtn = personBtn; }
    public static void setRelicBtn(Button relicBtn) { ContentController.relicBtn = relicBtn; }

    public static void goToDetail(DetailBaseController controller) {
        if(controller instanceof DynastyDetailController) {
            selectMenu(dynastyBtn);
            currentPane = "dynasty";
        }
        if(controller instanceof EventDetailController) {
            selectMenu(eventBtn);
            currentPane = "event";
        }
        if(controller instanceof PersonDetailController) {
            selectMenu(personBtn);
            currentPane = "person";
        }
        if(controller instanceof RelicDetailController) {
            selectMenu(relicBtn);
            currentPane = "relic";
        }

        FXMLLoader loader = new FXMLLoader(ContentController.class.getResource("/app/screen/fxml/detail.fxml"));
        // Set controller cho detail page
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
            contentArea.getChildren().clear();
            contentArea.getChildren().setAll(root);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void goToDynasty() {
        DynastyListController dynastyController = new DynastyListController();
        selectMenu(dynastyBtn);
        loadPane(dynastyController);
        currentPane = "dynasty";
    }

    public static void goToEvent() {
        EventListController eventController = new EventListController();
        selectMenu(eventBtn);
        loadPane(eventController);
        currentPane = "event";
    }

    public static void goToFestival() {
        selectMenu(festivalBtn);

        // clear current pane content
        contentArea.getChildren().clear();

        // load new pane
        FXMLLoader loader = new FXMLLoader(ContentController.class.getResource("/app/screen/fxml/festival.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            contentArea.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        currentPane = "festival";
    }

    public static void goToPerson() {
        PersonListController personController = new PersonListController();
        selectMenu(personBtn);
        loadPane(personController);
        currentPane = "person";
    }

    public static void goToRelic() {
        RelicListController relicController = new RelicListController();
        selectMenu(relicBtn);
        loadPane(relicController);
        currentPane = "relic";
    }

    private static void selectMenu(Button menuBtn) {
        // clear current navBar selected menu css
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

        // set selected navBar selected menu
        menuBtn.getStyleClass().remove("menu-bar");
        menuBtn.getStyleClass().add("selected-menu");
    }

    private static void loadPane(Object controller) {
        // clear current pane content
        contentArea.getChildren().clear();

        // load new pane
        FXMLLoader loader = new FXMLLoader(ContentController.class.getResource("/app/screen/fxml/list.fxml"));
        loader.setController(controller);
        Parent root = null;
        try {
            root = loader.load();
            contentArea.getChildren().setAll(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
