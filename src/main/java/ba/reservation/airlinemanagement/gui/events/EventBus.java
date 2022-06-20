package ba.reservation.airlinemanagement.gui.events;

public class EventBus {
    private final LoginEvent loginEvent = new LoginEvent();
    private final LogoutEvent logoutEvent = new LogoutEvent();
    private final CancleEvent cancleEvent = new CancleEvent();

    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    public LogoutEvent getLogoutEvent() {
        return logoutEvent;
    }

    public CancleEvent getCancleEvent() {
        return cancleEvent;
    }
}
