package app.screen.controller;

import java.io.IOException;

import app.history.storage.Storage;
import app.screen.controller.components.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class HomeController {

    @FXML
    private BorderPane homePane;

    @FXML
    void handleStartCrawl(ActionEvent event) {
        // try {
        //     Storage.crawl();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
        Transition.startFadeTransition(homePane, "/app/screen/fxml/crawler.fxml");
    }

    @FXML
    void handleStartView(ActionEvent event) {
        Transition.startFadeTransition(homePane, "/app/screen/fxml/layout.fxml");
    }
}
