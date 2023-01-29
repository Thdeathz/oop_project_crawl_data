package app.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class HomeController {

    @FXML
    private BorderPane homePane;

    @FXML
    void handleStartCrawl(ActionEvent event) {

    }

    @FXML
    void handleStartView(ActionEvent event) {
        Transition.startFadeTransition(homePane, "/app/screen/fxml/layout.fxml");
    }
}
