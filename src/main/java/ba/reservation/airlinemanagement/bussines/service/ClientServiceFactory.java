package ba.reservation.airlinemanagement.bussines.service;

public enum ClientServiceFactory {

    CLIENT_SERVICE(new ClientService());

    private ClientServiceLocal clientService;

    ClientServiceFactory(ClientServiceLocal clientServiceLocal) {
        this.clientService = clientServiceLocal;
    }

    public ClientServiceLocal getClientService() {
        return clientService;
    }
}
