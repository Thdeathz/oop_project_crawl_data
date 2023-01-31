package app.screen.controller.dynasty;

import app.history.dynasty.Dynasty;
import app.history.person.Person;
import app.history.store.Store;
import app.screen.controller.base.DetailBaseController;
import app.screen.controller.components.ContentController;
import app.screen.controller.person.PersonDetailController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class DynastyDetailController extends DetailBaseController {

    @FXML
    private VBox mainContent;

    @FXML
    private VBox sideBar;

    private Button currentSideBarBtn;

    private final Dynasty dynastyData;

    public DynastyDetailController(Dynasty dynastyData) {
        this.dynastyData = dynastyData;
    }

    @FXML
    public void initialize() {
        initSideBar();
        initMainContent(this.dynastyData);
    }

    public void initSideBar() {
        for (Dynasty item: Store.dynasties) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getName());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if(Objects.equals(item.getId(), dynastyData.getId())) {
                currentSideBarBtn = sideBarBtn;
                sideBarBtn.getStyleClass().add("current-content-btn");
            }

            sideBarBtn.setOnAction(event -> {
                currentSideBarBtn.getStyleClass().remove("current-content-btn");
                sideBarBtn.getStyleClass().add("current-content-btn");
                currentSideBarBtn = sideBarBtn;
                initMainContent(item);
            });

            sideBar.getChildren().add(sideBarBtn);
        }
    }

    public void initMainContent(Dynasty dynastyData) {
        Label dynastyName = new Label(dynastyData.getName());
        dynastyName.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
        dynastyName.getStyleClass().add("title");
        dynastyName.setPadding(new Insets(10, 0, 10, 0));
        dynastyName.setWrapText(true);

        Label exitedTime = new Label(dynastyData.getExitedTime());
        exitedTime.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
        exitedTime.getStyleClass().add("sub-title");
        exitedTime.setPadding(new Insets(0, 0, 10, 0));
        exitedTime.setWrapText(true);

        Label capital = new Label(dynastyData.getCapital());
        capital.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
        capital.getStyleClass().add("description");
        capital.setPadding(new Insets(0, 0, 10, 0));
        capital.setWrapText(true);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        int row = 0;
        int column = 0;
        for(Person item: dynastyData.getKing()) {
            VBox vBox = new VBox();
            vBox.setMinWidth(200);
            GridPane.setRowIndex(vBox, row);
            GridPane.setColumnIndex(vBox, column);

            Label kingName = new Label(item.getName());
            kingName.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
            kingName.getStyleClass().add("king-name");
            kingName.setCursor(Cursor.HAND);

            kingName.setOnMouseClicked(e -> {
                PersonDetailController personDetailController = new PersonDetailController(item);
                ContentController.goToDetail(personDetailController);
            });

            ImageView avatar = new ImageView();
            Image image = null;
            try {
                image = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/person/"+ item.getId() +".png");
            } catch (Exception e) {
                image = null;
            }
            avatar.setImage(image);
            avatar.setFitWidth(150);
            avatar.setFitHeight(200);

            vBox.getChildren().addAll(avatar, kingName);
            gridPane.getChildren().add(vBox);

            column++;
            if (column == 3) {
                column = 0;
                row++;
            }
        }

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                dynastyName,
                exitedTime,
                capital,
                gridPane
        );
    }
}
