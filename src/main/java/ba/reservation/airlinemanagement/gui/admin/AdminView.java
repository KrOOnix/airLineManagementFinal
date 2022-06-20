package ba.reservation.airlinemanagement.gui.admin;

import ba.reservation.airlinemanagement.gui.Controller;
import ba.reservation.airlinemanagement.gui.admin.plane.PlaneAdminPanel;
import ba.reservation.airlinemanagement.gui.admin.user.UserAdminPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class AdminView extends BorderPane {

    private final ToggleButton userToggleButton = new ToggleButton("Korisnici");
    private final ToggleButton planeToggleButton = new ToggleButton("Avioni");
    private final Button logoutButton = new Button("Odjava");


    UserAdminPanel userAdminPanel = new UserAdminPanel();
    PlaneAdminPanel planeAdminPanel = new PlaneAdminPanel();


    public AdminView() {
        setCenter(userAdminPanel);

        ToggleGroup toggleGroup = new ToggleGroup();
        userToggleButton.setToggleGroup(toggleGroup);
        planeToggleButton.setToggleGroup(toggleGroup);

        userToggleButton.setSelected(true);

        HBox mainMenu = new HBox();
        mainMenu.setSpacing(10);
        mainMenu.setPadding(new Insets(20, 20, 20, 20));
        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        userToggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        planeToggleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        mainMenu.getChildren().addAll(userToggleButton, planeToggleButton);

        HBox logoutHBox = new HBox(logoutButton);
        logoutHBox.setAlignment(Pos.CENTER_RIGHT);
        logoutHBox.setPadding(new Insets(20, 20, 20, 20));


        GridPane topPane = new GridPane();
        topPane.add(mainMenu, 0, 0);
        topPane.add(logoutHBox, 1, 0);
        setTop(topPane);


    }

}
