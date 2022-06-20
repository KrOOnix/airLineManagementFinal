package ba.reservation.airlinemanagement.bussines.service;

import ba.reservation.airlinemanagement.coomons.Constants;
import ba.reservation.airlinemanagement.bussines.model.User;

import javax.persistence.*;

class UserService extends AbstractService<User> implements UserServiceLocal {

    public UserService() {
        super(User.class);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        if (password == null || password.isEmpty()) {
            return null;
        }

        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", username);
        try {
            User user = (User) query.getSingleResult();
            if (user != null && password.equals(user.getPassword())) {
                return user;

            }
        } catch (NoResultException noResultException) {
            System.err.println("not exist user with username" + username);
            return null;
        }
        return null;
    }

    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(Constants.PU_NAME);
        return entityManagerFactory.createEntityManager();

    }
}
