package app.screen.controller;

import java.io.IOException;

import app.history.storage.Storage;
import javafx.fxml.FXML;

public class CrawlerController {
  @FXML
  public void initialize() {
    try {
      Storage.crawl();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
