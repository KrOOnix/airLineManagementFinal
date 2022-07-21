package ba.reservation.airlinemanagement.bussines.service;

public enum ReservationServiceFactory {

    RESERVATION_SERVICE (new ReservationService());

    private ReservationService reservationService;

    ReservationServiceFactory(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }
}
