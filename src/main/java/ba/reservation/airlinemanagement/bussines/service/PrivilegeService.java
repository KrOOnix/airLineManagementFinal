package ba.reservation.airlinemanagement.bussines.service;
import ba.reservation.airlinemanagement.bussines.model.Privilege;
import ba.reservation.airlinemanagement.coomons.Constants;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class PrivilegeService extends AbstractService<Privilege> implements PrivilegeServiceLocal {


    public PrivilegeService() {
        super(Privilege.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();
    }

}
