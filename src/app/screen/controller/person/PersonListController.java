package app.screen.controller.person;

import app.history.person.Person;
import app.history.store.Store;
import app.screen.controller.components.ContentController;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PersonListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    @FXML
    public void initialize() {
        int countPage = Store.persons.size()/12 + 1;

        //Create pagination
        Pagination pagination = new Pagination();
        pagination.setPageCount(countPage);
        pagination.setCurrentPageIndex(0);
        pagination.setMaxPageIndicatorCount(5);

        pagination.setPageFactory((pageIndex) -> {
            int gridCol = 0;
            int gridRow = 0;
            gridPane.getChildren().clear();
            for (Person item: Store.persons.subList(12* pagination.getCurrentPageIndex(), 12* pagination.getCurrentPageIndex()+11)){
                VBox vBox = new VBox();
                vBox.setMinWidth(200);

                Label personName = new Label(item.getName().toString());
                personName.getStylesheets().add(this.getClass().getResource("css/list.css").toExternalForm());
                personName.getStyleClass().add("text-title");
                personName.setCursor(Cursor.HAND);

                ImageView avatar = new ImageView();
                Image image = null;
                try {
                    image = new Image("D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/person/"+ item.getId() +".png");
                } catch (Exception e) {
                    image = null;
                }
                avatar.setImage(image);
                avatar.setFitWidth(200);
                avatar.setFitHeight(250);

                Text dynastyName = new Text(item.getDynastyName());
                dynastyName.setWrappingWidth(200);
                dynastyName.getStyleClass().add("text-description");

                Text date = new Text( item.getDateOfBirth().toString() + " - " + item.getDateOfDeath().toString() );
                date.setWrappingWidth(200);
                date.getStyleClass().add("text-description");

                vBox.getChildren().addAll(avatar, personName,  dynastyName, date);

                //constraint grid pane col and row index
                GridPane.setColumnIndex(vBox, gridCol);
                GridPane.setRowIndex(vBox, gridRow);

                gridPane.getChildren().add(vBox);
                gridCol++;
                if (gridCol == 4){
                    gridCol = 0;
                    gridRow++;
                }

                // xu ly su kien click
                personName.setOnMouseClicked(e -> {
                    PersonDetailController personDetailController = new PersonDetailController(item);
                    ContentController.goToDetail(personDetailController);
                });
            }
            return new VBox(gridPane);
        });
        VBox paginationVBox = new VBox(pagination);
        paginationContainer.getChildren().add(paginationVBox);
    }
}
