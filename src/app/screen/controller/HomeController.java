package app.screen.controller;

import app.screen.controller.components.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class HomeController {

    @FXML
    private BorderPane homePane;

    @FXML
    void handleStartCrawl(ActionEvent event) {
        Transition.startFadeTransition(homePane, "/app/screen/fxml/crawler.fxml");
    }

    @FXML
    void handleStartView(ActionEvent event) {
        Transition.startFadeTransition(homePane, "/app/screen/fxml/layout.fxml");
    }
}
