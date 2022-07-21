package ba.reservation.airlinemanagement.bussines.service;

import ba.reservation.airlinemanagement.bussines.model.Client;
import ba.reservation.airlinemanagement.coomons.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientService extends AbstractService<Client, Integer> implements ClientServiceLocal {

    public ClientService() {
        super(Client.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
