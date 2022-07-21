package ba.reservation.airlinemanagement.bussines.plane;

public enum PlaneServiceFactory {

    SERVICE(new PlaneService());

    private PlaneService planeService;

    PlaneServiceFactory(PlaneService planeService){
        this.planeService = planeService;
    }

    public PlaneService getPlaneService() {
        return planeService;
    }
}
