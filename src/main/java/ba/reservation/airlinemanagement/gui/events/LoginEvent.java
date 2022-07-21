package ba.reservation.airlinemanagement.gui.events;

import ba.reservation.airlinemanagement.gui.Controller;
import ba.reservation.airlinemanagement.bussines.model.Privilege;
import ba.reservation.airlinemanagement.bussines.model.User;
import ba.reservation.airlinemanagement.bussines.service.UserServiceFactory;
import ba.reservation.airlinemanagement.gui.admin.AdminView;
import ba.reservation.airlinemanagement.gui.employee.EmployeeView;
import ba.reservation.airlinemanagement.gui.login.LoginView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class LoginEvent  implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        LoginView loginView = Controller.instance().getLoginView();
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            loginView.setLoginMessage("Polja ne mogu biti prazna");
            return;
        }
        User user = UserServiceFactory.USER_SERVICE.getUserService().login(username, password);
        if (user == null) {
            loginView.setLoginMessage("Neispravna kombinacija usernama i passworda");

        } else {

            Controller.instance().setLoggedUser(user);
            Privilege privilege = user.getIdPrivilege();
            BorderPane mainPanel;
            if ("admin".equalsIgnoreCase(privilege.getName())) {
                mainPanel = new AdminView();
                Controller.instance().setAdminView((AdminView) mainPanel);
                Controller.instance().getStage().setTitle("Admin Panel: " + user.getName()+ " "+ user.getSurname());
            } else {
                mainPanel = new EmployeeView();
                Controller.instance().setEmployeeView((EmployeeView) mainPanel);
                Controller.instance().getStage().setTitle("Employe Panel: " + user.getName()+ " " + user.getSurname());
            }

            Scene scene = new Scene(mainPanel, 960, 400);
            Controller.instance().getStage().setScene(scene);


        }

    }
}
