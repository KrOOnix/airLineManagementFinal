package ba.reservation.airlinemanagement.bussines.service;

import ba.reservation.airlinemanagement.bussines.model.Reservation;
import ba.reservation.airlinemanagement.coomons.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ReservationService extends AbstractService<Reservation,Integer>{

    public ReservationService() {
        super(Reservation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
