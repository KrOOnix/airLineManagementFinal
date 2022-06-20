package ba.reservation.airlinemanagement.gui;

import ba.reservation.airlinemanagement.bussines.model.User;
import ba.reservation.airlinemanagement.gui.admin.AdminView;
import ba.reservation.airlinemanagement.gui.employee.EmployeeView;
import ba.reservation.airlinemanagement.gui.events.EventBus;
import ba.reservation.airlinemanagement.gui.login.LoginView;
import javafx.stage.Stage;

public class Controller {

    private static Controller INSTANCE = null;
    private LoginView loginView;
    private EmployeeView employeeView;
    private AdminView adminView;
    private User loggedUser;
    private Stage stage;
    private EventBus eventBus = new EventBus();


    private Controller() {

    }

    public static Controller instance() {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public static void setINSTANCE(Controller INSTANCE) {
        Controller.INSTANCE = INSTANCE;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public EmployeeView getEmployeeView() {
        return employeeView;
    }

    public void setEmployeeView(EmployeeView employeeView) {
        this.employeeView = employeeView;
    }

    public AdminView getAdminView() {
        return adminView;
    }

    public void setAdminView(AdminView adminView) {
        this.adminView = adminView;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
