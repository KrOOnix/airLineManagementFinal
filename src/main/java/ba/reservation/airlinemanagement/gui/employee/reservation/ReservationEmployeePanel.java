package ba.reservation.airlinemanagement.gui.employee.reservation;

import ba.reservation.airlinemanagement.bussines.model.Client;
import ba.reservation.airlinemanagement.bussines.model.Plane;
import ba.reservation.airlinemanagement.bussines.model.Reservation;
import ba.reservation.airlinemanagement.bussines.plane.PlaneServiceFactory;
import ba.reservation.airlinemanagement.bussines.service.ClientServiceFactory;
import ba.reservation.airlinemanagement.bussines.service.ReservationService;
import ba.reservation.airlinemanagement.bussines.service.ReservationServiceFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.util.List;

public class ReservationEmployeePanel extends VBox {
    private Label titleLabel = new Label("Administracija rezervacija");
    private ObservableList<Reservation> reservationObservableList;
    private TableView<Reservation> reservationTableView = new TableView<>();

    private final ChoiceBox<Client> clientChoiceBox = new ChoiceBox<>();
    private final ChoiceBox<Plane> planeChoiceBox = new ChoiceBox<>();
    private final TextField dateTextField = new TextField();
    private final TextField priceTextField = new TextField();
    private final Label messageLabel = new Label();
    private final Button addReservationButton = new Button("Dodaj");
    private final Button removeReservationButton = new Button("Obriši");

    public ReservationEmployeePanel(){
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-background-color: darkblue;-fx-text-fill: white");
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));
        reservationTableView.setMaxWidth(700);
        reservationTableView.setMaxHeight(200);
        reservationTableView.setStyle("-fx-background-color: #8A2BE2;-fx-font-weight: bold");
        clientChoiceBox.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        planeChoiceBox.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        dateTextField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        priceTextField.setStyle("-fx-border-color:#8A2BE2;-fx-font-weight: bold");
        addReservationButton.setStyle("-fx-background-color: green;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        removeReservationButton.setStyle("-fx-background-color: red;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");

        TableColumn<Reservation, Integer> idTableColumn = new TableColumn<>("Id");
        idTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("id"));

        TableColumn<Reservation, String> clientColumn = new TableColumn<>("Klijent");
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));

        TableColumn<Reservation, String> planeColumn = new TableColumn<>("Avion");
        planeColumn.setCellValueFactory(new PropertyValueFactory<>("idPlane"));

        TableColumn<Reservation, String> dateTableColumn = new TableColumn<>("Datum");
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));

        TableColumn<Reservation, String> priceTableColumn = new TableColumn<>("Cijena");
        priceTableColumn.setCellValueFactory(new PropertyValueFactory<Reservation, String>("price"));

        List<Reservation> reservationList = ReservationServiceFactory.RESERVATION_SERVICE.getReservationService().findAll();
        reservationObservableList = FXCollections.observableList(reservationList);
        reservationTableView.setItems(reservationObservableList);
        reservationTableView.getColumns().addAll(idTableColumn,clientColumn,planeColumn,dateTableColumn,priceTableColumn);
        getChildren().addAll(titleLabel,reservationTableView,getForm(),messageLabel);

    }

    private HBox getForm(){
        HBox form = new HBox();
        form.setSpacing(5);

        List<Client> clientList = ClientServiceFactory.CLIENT_SERVICE.getClientService().findAll();
        clientChoiceBox.setItems(FXCollections.observableList(clientList));
        clientChoiceBox.getSelectionModel().select(1);

        List<Plane> planeList = PlaneServiceFactory.SERVICE.getPlaneService().findAll();
        planeChoiceBox.setItems(FXCollections.observableList(planeList));
        planeChoiceBox.getSelectionModel().select(0);


        priceTextField.setPromptText("Cijena");
        dateTextField.setPromptText("Datum");
        addReservationButton.setOnAction(this::addReservation);
        removeReservationButton.setOnAction(this::removeReservation);
        form.getChildren().addAll(clientChoiceBox,planeChoiceBox,dateTextField,priceTextField, addReservationButton, removeReservationButton);
        return form;
    }

    private void removeReservation(ActionEvent actionEvent){
        Reservation selectedReservation = reservationTableView.getSelectionModel().getSelectedItem();
        ReservationService reservationService = ReservationServiceFactory.RESERVATION_SERVICE.getReservationService();
        reservationService.removeById(selectedReservation.getId());
        reservationObservableList.remove(selectedReservation);

    }

    private void addReservation(ActionEvent actionEvent){
        if (validate()){
            Reservation reservation = new Reservation();
            reservation.setIdClient(clientChoiceBox.getValue());
            reservation.setIdPlane(planeChoiceBox.getValue());
            reservation.setDate(dateTextField.getText());
            reservation.setPrice(priceTextField.getText());
            clearInput();
            ReservationService reservationService = ReservationServiceFactory.RESERVATION_SERVICE.getReservationService();
            reservationService.create(reservation);
            reservationObservableList.add(reservation);
        }else {
            messageLabel.setText("Sva polja moraju biti popunjena!");
            messageLabel.setStyle("-fx-text-fill: red; ");
        }

    }
    private boolean validate(){
        return !priceTextField.getText().isBlank()
                &&!dateTextField.getText().isBlank();
    }

    private void clearInput(){
        priceTextField.clear();
        dateTextField.clear();
    }

}

