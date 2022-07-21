package ba.reservation.airlinemanagement.gui.login;

import ba.reservation.airlinemanagement.gui.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


public class LoginView extends GridPane {

    private final Label usernameLabel = new Label("Korisničko ime: ");
    private final Label passwordLabel = new Label("Lozinka: ");
    private final TextField usernameTextField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Button loginButton = new Button("Login");
    private final Label messageLabel = new Label();
    public LoginView() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));

        usernameTextField.setPrefWidth(225);
        usernameTextField.setMaxWidth(225);
        passwordField.setPrefWidth(225);
        passwordField.setMaxWidth(225);

        loginButton.setStyle("-fx-background-color: darkblue;-fx-text-fill: white");
        messageLabel.setStyle("-fx-text-fill: red");
        usernameLabel.setStyle("-fx-font-weight: bold");
        passwordLabel.setStyle("-fx-font-weight: bold");
        usernameTextField.setStyle("-fx-font-weight: bold; -fx-border-color: black;");
        passwordField.setStyle("-fx-font-weight: bold;-fx-border-color: black;");
        usernameTextField.setPromptText("Username");
        passwordField.setPromptText("Password");

        setAlignment(Pos.CENTER_RIGHT);

        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);

        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(5);
        loginButton.setOnAction(Controller.instance().getEventBus().getLoginEvent());

        flowPane.getChildren().addAll(loginButton);
        add(flowPane, 1, 2);
        add(messageLabel, 1, 3);
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }


    public void setLoginMessage(String message) {
        messageLabel.setText(message);
    }

}
