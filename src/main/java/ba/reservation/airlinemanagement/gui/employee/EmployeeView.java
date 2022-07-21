package ba.reservation.airlinemanagement.gui.employee;

import ba.reservation.airlinemanagement.gui.Controller;
import ba.reservation.airlinemanagement.gui.employee.client.ClientEmployeePanel;
import ba.reservation.airlinemanagement.gui.employee.reservation.ReservationEmployeePanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EmployeeView extends BorderPane {

    private final ToggleButton clientToggleButton = new ToggleButton("Klijenti");
    private final ToggleButton reservationToggleButton = new ToggleButton("Rezervacije");
    private final Button logoutButton = new Button("Odjava");

    ClientEmployeePanel clientEmployeePanel = new ClientEmployeePanel();
    ReservationEmployeePanel reservationEmployeePanel = new ReservationEmployeePanel();

    public EmployeeView(){
        setCenter(clientEmployeePanel);
        clientEmployeePanel.setStyle("-fx-background-color: aqua");
        reservationEmployeePanel.setStyle("-fx-background-color: aqua");
        clientToggleButton.setStyle("-fx-background-color: darkblue;-fx-text-fill: white;-fx-font-weight: bold;-fx-border-color: white");
        reservationToggleButton.setStyle("-fx-background-color: darkblue;-fx-text-fill: white;-fx-border-color: white;-fx-font-weight: bold");
        logoutButton.setStyle("-fx-background-color: darkblue;-fx-text-fill: white;-fx-border-color: white;-fx-font-weight: bold");
        ToggleGroup toggleGroup = new ToggleGroup();
        clientToggleButton.setToggleGroup(toggleGroup);
        reservationToggleButton.setToggleGroup(toggleGroup);
        clientToggleButton.setSelected(true);

        HBox mainManu = new HBox();
        mainManu.setSpacing(15);
        mainManu.setPadding(new Insets(20,20,20,20));

        logoutButton.setOnAction(Controller.instance().getEventBus().getLogoutEvent());
        clientToggleButton.setOnAction(e-> setCenter(clientEmployeePanel));
        reservationToggleButton.setOnAction(e->setCenter(reservationEmployeePanel));

        mainManu.getChildren().addAll(clientToggleButton,reservationToggleButton);

        HBox logoutHBox = new HBox(logoutButton);
        logoutHBox.setAlignment(Pos.CENTER_RIGHT);
        logoutHBox.setPadding(new Insets(20,20,20,20));

        GridPane topPane = new GridPane();
        topPane.add(mainManu,0,0);
        topPane.add(logoutHBox,1,0);
        topPane.setStyle("-fx-background-color: aqua");
        setTop(topPane);
    }
}
