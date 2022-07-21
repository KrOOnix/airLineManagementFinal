package ba.reservation.airlinemanagement.gui.admin;

import ba.reservation.airlinemanagement.gui.Controller;
import ba.reservation.airlinemanagement.gui.admin.plane.PlaneAdminPanel;
import ba.reservation.airlinemanagement.gui.admin.user.UserAdminPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

public class AdminView extends BorderPane {

    private final ToggleButton userToggleButton = new ToggleButton("Korisnici");
    private final ToggleButton planeToggleButton = new ToggleButton("Avioni");
    private final Button logoutButton = new Button("Odjava");

    UserAdminPanel userAdminPanel = new UserAdminPanel();
    PlaneAdminPanel planeAdminPanel = new PlaneAdminPanel();


    public AdminView() {
        setCenter(userAdminPanel);

        userAdminPanel.setStyle("-fx-background-color: rgba(61,139,71,0.53);");
        planeAdminPanel.setStyle("-fx-background-color: rgba(61,139,71,0.53);");
        userToggleButton.setStyle("-fx-background-color: yellow;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        planeToggleButton.setStyle("-fx-background-color: yellow;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        logoutButton.setStyle("-fx-background-color: yellow; -fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");

        ToggleGroup toggleGroup = new ToggleGroup();
        userToggleButton.setToggleGroup(toggleGroup);
        planeToggleButton.setToggleGroup(toggleGroup);

        userToggleButton.setSelected(true);

        HBox mainMenu = new HBox();
        mainMenu.setSpacing(10);
        mainMenu.setPadding(new Insets(20, 20, 20, 20));
        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        userToggleButton.setOnAction(e -> setCenter(userAdminPanel));
        planeToggleButton.setOnAction(e -> setCenter(planeAdminPanel));
        mainMenu.getChildren().addAll(userToggleButton, planeToggleButton);

        HBox logoutHBox = new HBox(logoutButton);
        logoutHBox.setAlignment(Pos.CENTER_RIGHT);
        logoutHBox.setPadding(new Insets(20, 20, 20, 20));

        GridPane topPane = new GridPane();
        topPane.add(mainMenu, 0, 0);
        topPane.add(logoutHBox, 1, 0);
        topPane.setStyle("-fx-background-color: rgba(61,139,71,0.53);");
        setTop(topPane);

    }

}
