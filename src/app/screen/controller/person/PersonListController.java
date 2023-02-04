package app.screen.controller.person;

import app.history.dynasty.Dynasty;
import app.history.person.Person;
import app.history.store.Store;
import app.screen.controller.components.ContentController;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class PersonListController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox paginationContainer;

    @FXML
    public void initialize() {
        initContent();
        Store.filteredPersons.addListener((ListChangeListener<Person>) c -> {
            initContent();
        });
    }

    private void initContent() {
        // clear old data
        paginationContainer.getChildren().clear();

        if(Store.filteredPersons.isEmpty()) {
            Label emptyLabel = new Label();
            emptyLabel.getStyleClass().add("text-title");
            emptyLabel.setText("Không có kết quả nào ><!");
            gridPane.getChildren().clear();
            gridPane.getChildren().add(emptyLabel);
            paginationContainer.getChildren().add(gridPane);
        } else {
            //Create pagination
            Pagination pagination = new Pagination();
            pagination.setPageCount(Store.filteredPersons.size()/12 + 1);
            pagination.setCurrentPageIndex(0);
            pagination.setMaxPageIndicatorCount(5);

            pagination.setPageFactory((pageIndex) -> {
                gridPane.getChildren().clear();

                int gridCol = 0;
                int gridRow = 0;

                int startItemIndex = 12 * pagination.getCurrentPageIndex();
                int endItemIndex = startItemIndex + 12;
                if(endItemIndex > Store.filteredPersons.size()) endItemIndex = Store.filteredPersons.size();

                for (Person item: Store.filteredPersons.subList(startItemIndex, endItemIndex)){
                    VBox vBox = new VBox();
                    vBox.setMinWidth(200);

                    Label personName = new Label(item.getName().toString());
                    personName.getStyleClass().add("text-title");
                    personName.setCursor(Cursor.HAND);

                    ImageView avatar = new ImageView();
                    Image image = null;
                    try {
                        image = new Image(Objects.requireNonNull(getClass().getResource("/app/history/store/img/person/"+ item.getId() +".png")).openStream());
                    } catch (Exception e) {
                        try {
                            image = new Image(Objects.requireNonNull(getClass().getResource("/app/history/store/img/person/no_image.png")).openStream());
                        } catch (IOException ex) {
                            image = null;
                        }
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
}
