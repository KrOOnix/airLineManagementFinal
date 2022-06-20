package ba.reservation.airlinemanagement;

import ba.reservation.airlinemanagement.gui.Controller;
import ba.reservation.airlinemanagement.gui.login.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TravelsApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        LoginView loginView = new LoginView();
        Controller.instance().setStage(stage);
        Controller.instance().setLoginView(loginView);
        Scene scene = new Scene(loginView, 700, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}