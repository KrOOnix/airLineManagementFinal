package ba.reservation.airlinemanagement.gui.admin.user;

import ba.reservation.airlinemanagement.bussines.model.Client;
import ba.reservation.airlinemanagement.bussines.model.Privilege;
import ba.reservation.airlinemanagement.bussines.model.User;
import ba.reservation.airlinemanagement.bussines.service.PrivilegeServiceFactory;
import ba.reservation.airlinemanagement.bussines.service.UserServiceFactory;
import ba.reservation.airlinemanagement.bussines.service.UserServiceLocal;
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

public class UserAdminPanel extends VBox {
    private Label titleLabel = new Label("Administracija korisnika");
    private ObservableList<User> userObservableList;
    private TableView<User> userTableView = new TableView<>();

    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final TextField nameTextField = new TextField();
    private final TextField surnameTextField = new TextField();
    private final ChoiceBox<Privilege> prvilegeChoicBox = new ChoiceBox<>();
    private Button addButton = new Button("Dodaj");
    private Button deleteUserButton = new Button("Obriši");
    private final Label messageLabel = new Label();


    public UserAdminPanel() {
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setStyle("-fx-background-color: yellow;-fx-border-color: black; -fx-text-fill: black;");
        addButton.setStyle("-fx-background-color: green;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        deleteUserButton.setStyle("-fx-background-color: red;-fx-border-color: black; -fx-text-fill: black;-fx-font-weight: bold");
        userTableView.setStyle("-fx-background-color: black;-fx-font-weight: bold");
        nameTextField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        surnameTextField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        usernameField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        passwordField.setStyle("-fx-border-color: black;-fx-font-weight: bold");
        prvilegeChoicBox.setStyle("-fx-border-color: black;");
        userTableView.setMaxWidth(880);
        userTableView.setMaxHeight(200);
        setSpacing(10);
        setPadding(new Insets(10, 10, 10, 10));


        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));


        TableColumn<User, String> usernameColumn = new TableColumn<>("Korisničko ime");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));


        TableColumn<User, String> nameColumn = new TableColumn<>("Ime");
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));


        TableColumn<User, String> surnameColumn = new TableColumn<>("Prezime");
        surnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("surname"));


        TableColumn<User, String> privilegeColumn = new TableColumn<>("Privilegija");
        privilegeColumn.setCellValueFactory(new PropertyValueFactory<>("idPrivilege"));


        List<User> userList = UserServiceFactory.USER_SERVICE.getUserService().findAll();
        userObservableList = FXCollections.observableList(userList);
        userTableView.setItems(userObservableList);
        userTableView.getColumns().addAll(idColumn,usernameColumn, nameColumn, surnameColumn, privilegeColumn);
        getChildren().addAll(titleLabel, userTableView, getForm(), messageLabel);

    }

    private HBox getForm() {
        HBox form = new HBox();
        form.setSpacing(10);

        List<Privilege> privilegeList = PrivilegeServiceFactory.PRIVILEGE_SERVICE.getPrivilegeService().findAll();
        prvilegeChoicBox.setItems(FXCollections.observableList(privilegeList));
        prvilegeChoicBox.getSelectionModel().select(0);

        nameTextField.setPromptText("Ime");
        surnameTextField.setPromptText("Prezime");
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Lozinka");
        addButton.setOnAction(this::addUser);
        deleteUserButton.setOnAction(this::removeUser);
        form.getChildren().addAll(nameTextField, surnameTextField, usernameField, passwordField, prvilegeChoicBox, addButton, deleteUserButton);
        return form;
    }


    private void removeUser(ActionEvent actionEvent) {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        UserServiceLocal userService = UserServiceFactory.USER_SERVICE.getUserService();
        userService.removeById(selectedUser.getId());
        userObservableList.remove(selectedUser);

    }

    private void addUser(ActionEvent event) {
        if (validate()) {
            User user = new User();
            user.setName(nameTextField.getText());
            user.setSurname(surnameTextField.getText());
            user.setUsername(usernameField.getText());
            user.setPassword(passwordField.getText());
            user.setIdPrivilege(prvilegeChoicBox.getValue());
            UserServiceLocal userServiceLocal = UserServiceFactory.USER_SERVICE.getUserService();
            userServiceLocal.create(user);
            userObservableList.addAll(user);
            clearInput();
        } else {
            messageLabel.setText("Sva polja moraju biti popunjena!");
            messageLabel.setStyle("-fx-text-fill: red;-fx-font-weight: bold");
        }

    }

    private boolean validate() {
        return !usernameField.getText().isBlank()
                && !passwordField.getText().isBlank()
                && !nameTextField.getText().isBlank()
                && !surnameTextField.getText().isBlank();
    }

    private void clearInput() {
        nameTextField.clear();
        surnameTextField.clear();
        usernameField.clear();
        passwordField.clear();
    }
}
