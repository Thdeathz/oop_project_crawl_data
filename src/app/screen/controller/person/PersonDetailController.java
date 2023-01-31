package app.screen.controller.person;

import app.history.dynasty.Dynasty;
import app.history.person.Person;
import app.history.store.Store;
import app.screen.controller.base.DetailBaseController;
import app.screen.controller.components.ContentController;
import app.screen.controller.dynasty.DynastyDetailController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.util.Objects;

public class PersonDetailController extends DetailBaseController {

    @FXML
    private VBox mainContent;

    @FXML
    private VBox sideBar;

    private Button currentSideBarBtn;

    private final Person personData;

    public PersonDetailController(Person personData) {
        this.personData = personData;
    }

    @FXML
    public void initialize() {
        initMainContent(this.personData);
        initSideBar();
    }

    public void initSideBar() {
        for (Person item: Store.persons.subList(0, 100)) {
            Button sideBarBtn = new Button();
            sideBarBtn.setText("> " + item.getName());
            sideBarBtn.getStyleClass().add("side-bar-btn");
            if(Objects.equals(item.getId(), personData.getId())) {
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

    //CREATE UI FROM DATA
    public void initMainContent(Person currentPerson) {
        ImageView avatar = new ImageView();
        Image image = null;
        try {
            image = new Image(
                "D:/Học linh tinh/Học Java/Projects/oop_project_crawl_data/src/app/history/store/img/person/" +
                currentPerson.getId() +
                ".png"
            );
        } catch (Exception e) {
            image = null;
        }
        avatar.setImage(image);
        avatar.setFitWidth(400);
        avatar.setFitHeight(550);

        Label personName = new Label(currentPerson.getName());
        personName.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("css/detail.css")).toExternalForm());
        personName.getStyleClass().add("title");
        personName.setPadding(new Insets(20, 0, 20, 0));
        personName.setWrapText(true);

        Label personGivenName = new Label("Tên gọi khác: " + currentPerson.getGivenName());
        personGivenName.getStyleClass().add("content");
        personGivenName.setPadding(new Insets(0, 0, 10, 0));
        personGivenName.setWrapText(true);

        Label personFather = new Label("Tên cha: " + currentPerson.getFather());
        personFather.getStyleClass().add("content");
        personFather.setPadding(new Insets(0, 0, 10, 0));
        personFather.setWrapText(true);

        Label personReign = new Label("Thời gian cai trị: " + currentPerson.getReign());
        personReign.getStyleClass().add("content");
        personReign.setPadding(new Insets(0, 0, 10, 0));
        personReign.setWrapText(true);

        Label personDateOfBirth = new Label("Sinh: " + currentPerson.getDateOfBirth());
        personDateOfBirth.getStyleClass().add("content");
        personDateOfBirth.setPadding(new Insets(0, 0, 10, 0));
        personDateOfBirth.setWrapText(true);

        Label personDateOfDeath = new Label("Mất: " + currentPerson.getDateOfDeath());
        personDateOfDeath.getStyleClass().add("content");
        personDateOfDeath.setPadding(new Insets(0, 0, 10, 0));
        personDateOfDeath.setWrapText(true);

        Text personDescription = new Text(currentPerson.getDescription());
        personDescription.getStyleClass().add("description");
        personDescription.setWrappingWidth(500);

        Label personDynasty = new Label();
        Dynasty dynasty = currentPerson.getDynasty();
        String text = "Triều Đại: ";
        if(dynasty != null) {
            text += currentPerson.getDynasty().getName();
            personDynasty.setOnMouseClicked(event -> {
                DynastyDetailController dynastyDetailController = new DynastyDetailController(dynasty);
                ContentController.goToDetail(dynastyDetailController);
            });
        } else {
            text += "Không rõ";
        }

        personDynasty.setText(text);
        personDynasty.getStyleClass().add("dynasty");
        personDynasty.setPadding(new Insets(10, 0, 0, 0));
        personDynasty.setWrapText(true);

        mainContent.getChildren().clear();
        mainContent.getChildren().addAll(
                personName,
                personGivenName,
                personFather,
                personReign,
                personDateOfBirth,
                personDateOfDeath,
                avatar,
                personDescription,
                personDynasty
        );
    }
}
