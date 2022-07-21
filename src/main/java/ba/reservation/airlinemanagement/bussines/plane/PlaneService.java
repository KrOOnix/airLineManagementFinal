package ba.reservation.airlinemanagement.bussines.plane;

import ba.reservation.airlinemanagement.bussines.model.Plane;
import ba.reservation.airlinemanagement.bussines.service.AbstractService;
import ba.reservation.airlinemanagement.coomons.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class PlaneService extends AbstractService<Plane,Integer> {

     PlaneService() {
        super(Plane.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }
}
