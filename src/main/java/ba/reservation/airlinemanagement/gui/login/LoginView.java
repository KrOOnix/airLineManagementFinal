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
    private final Button loginButton = new Button("Prijava");
    private final Button cancelButton = new Button("Odustani");
    private final Label messageLabel = new Label();//ovdje ne piše ništa..sadržaj ćemo možda dinamički popuniti

    public LoginView() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(10, 10, 10, 10));

        usernameTextField.setPrefWidth(260);
        usernameTextField.setMaxWidth(260);
        passwordField.setPrefWidth(260);
        passwordField.setMaxWidth(260);


        setAlignment(Pos.CENTER_RIGHT);
        //username
        add(usernameLabel, 0, 0);
        add(usernameTextField, 1, 0);
        //password
        add(passwordLabel, 0, 1);
        add(passwordField, 1, 1);

        //FlowPane
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(5);
        loginButton.setOnAction(Controller.instance().getEventBus().getLoginEvent());
        cancelButton.setOnAction(Controller.instance().getEventBus().getCancleEvent());
        flowPane.getChildren().addAll(loginButton, cancelButton);
        add(flowPane, 1, 2);
        //message
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
