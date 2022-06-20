package ba.reservation.airlinemanagement.gui.events;

import ba.reservation.airlinemanagement.gui.Controller;
import ba.reservation.airlinemanagement.gui.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogoutEvent implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Controller.instance().setLoggedUser(null);
        Controller.instance().getStage().setTitle("Login");
        LoginView loginView = new LoginView();
        Controller.instance().setLoginView(loginView);
        Scene scene = new Scene(loginView, 600, 180);
        Stage stage = new Stage();
        Controller.instance().getStage().setScene(scene);
    }
}
