package ba.reservation.airlinemanagement.gui.employee.client;

import ba.reservation.airlinemanagement.bussines.model.*;
import ba.reservation.airlinemanagement.bussines.service.*;
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

public class ClientEmployeePanel extends VBox {

    private Label titleLabel = new Label("Administracija klijenata");
    private ObservableList<Client> clientsObservableList;

    private TableView<Client> clientTableView = new TableView<>();

    private final TextField documentField = new TextField();
    private final TextField nameField = new TextField();
    private final TextField surnameField = new TextField();
    private final TextField countryField = new TextField();
    private final TextField townField = new TextField();
    private final TextField addressField = new TextField();
    private final TextField houseNumberField = new TextField();
    private final TextField phoneNumberField = new TextField();
    private final TextField destinationField = new TextField();

    private final Label messageLabel = new Label();

    private Button addButton = new Button("Dodaj");
    private Button deleteClientButton = new Button("Obriši");

    public ClientEmployeePanel() {
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-background-color: darkblue;-fx-text-fill: white");
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));
        clientTableView.setMaxWidth(820);
        clientTableView.setMaxHeight(400);
        clientTableView.setStyle("-fx-background-color: #8A2BE2;-fx-font-weight: bold");
        documentField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        nameField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        surnameField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        countryField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        townField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        addressField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        houseNumberField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        phoneNumberField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        destinationField.setStyle("-fx-border-color:#8A2BE2 ;-fx-font-weight: bold");
        addButton.setStyle("-fx-background-color: green;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        deleteClientButton.setStyle("-fx-background-color: red;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");

        TableColumn<Client, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));

        TableColumn<Client, String> documentColumn = new TableColumn<>("Dokument");
        documentColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("document"));

        TableColumn<Client, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));

        TableColumn<Client, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("surname"));

        TableColumn<Client, String> countryColumn = new TableColumn<>("Država");
        countryColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("country"));

        TableColumn<Client, String> townColumn = new TableColumn<>("Grad");
        townColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("town"));

        TableColumn<Client, String> addressColumn = new TableColumn<>("Adresa");
        addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));

        TableColumn<Client, Integer> houseNumberColumn = new TableColumn<>("Broj kuće");
        houseNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("houseNumber"));

        TableColumn<Client, String> phoneNumberColumn = new TableColumn<>("Broj Telefona");
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("phoneNumber"));

        TableColumn<Client, String> destinationColumn = new TableColumn<>("Destinacija");
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("destination"));


        List<Client> clientList = ClientServiceFactory.CLIENT_SERVICE.getClientService().findAll();
        clientsObservableList = FXCollections.observableList(clientList);
        clientTableView.setItems(clientsObservableList);
        clientTableView.getColumns().addAll(idColumn, documentColumn, nameColumn, surnameColumn, countryColumn
                , townColumn, addressColumn
                , houseNumberColumn, phoneNumberColumn, destinationColumn);

        getChildren().addAll(titleLabel, clientTableView, getForm(), getForm1(),getForm2(),messageLabel);

    }

    private HBox getForm() {
        HBox form = new HBox();
        form.setSpacing(30);

        documentField.setPromptText("Dokument");
        nameField.setPromptText("Ime");
        surnameField.setPromptText("Prezime");
        countryField.setPromptText("Država");
        form.getChildren().addAll(documentField, nameField, surnameField, countryField);
        return form;
    }

    private HBox getForm1() {
        HBox form1 = new HBox();
        form1.setSpacing(30);

        townField.setPromptText("Grad");
        addressField.setPromptText("Adresa");
        houseNumberField.setPromptText("Broj kuće");
        phoneNumberField.setPromptText("Broj telefona");
        destinationField.setPromptText("Destinacija");
        form1.getChildren().addAll(townField, addressField, houseNumberField, phoneNumberField, destinationField);
        return form1;
    }

    private HBox getForm2(){
        HBox form2 = new HBox();
        form2.setSpacing(35);

        destinationField.setPromptText("Destinacija");
        addButton.setOnAction(this::addClient);
        deleteClientButton.setOnAction(this::removeClient);
        form2.getChildren().addAll(destinationField,addButton,deleteClientButton);
        return form2;
    }



    private void removeClient (ActionEvent actionEvent){
        Client selectedClient = clientTableView.getSelectionModel().getSelectedItem();
        ClientServiceLocal clientService = ClientServiceFactory.CLIENT_SERVICE.getClientService();
        clientService.removeById(selectedClient.getId());
        clientsObservableList.remove(selectedClient);

    }


    private void addClient(ActionEvent actionEvent) {

        if (validate()){

            Client client = new Client();

            client.setDocument(documentField.getText());
            client.setName(nameField.getText());
            client.setSurname(surnameField.getText());
            client.setCountry(countryField.getText());
            client.setTown(townField.getText());
            client.setAddress(addressField.getText());
            client.setHouseNumber(Integer.parseInt(houseNumberField.getText()));
            client.setPhoneNumber(phoneNumberField.getText());
            client.setDestination(destinationField.getText());
            clearInput();

            ClientServiceLocal clientService = ClientServiceFactory.CLIENT_SERVICE.getClientService();
            clientService.create(client);
            clientsObservableList.add(client);


        }else {
            messageLabel.setText("Sva polja moraju biti popunjena!");
            messageLabel.setStyle("-fx-text-fill: red; ");
        }



    }

    private boolean validate(){
        return! documentField.getText().isBlank()
                && !nameField.getText().isBlank()
                && !surnameField.getText().isBlank()
                && !countryField.getText().isBlank()
                && !townField.getText().isBlank()
                && !addressField.getText().isBlank()
                && !houseNumberField.getText().isBlank()
                && !phoneNumberField.getText().isBlank()
                && !documentField.getText().isBlank();
    }

    private void clearInput() {
        documentField.clear();
        nameField.clear();
        surnameField.clear();
        countryField.clear();
        townField.clear();
        addressField.clear();
        houseNumberField.clear();
        phoneNumberField.clear();
        destinationField.clear();

    }
}
