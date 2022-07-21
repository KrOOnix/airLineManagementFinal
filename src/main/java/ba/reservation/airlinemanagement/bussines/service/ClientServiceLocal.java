package ba.reservation.airlinemanagement.bussines.service;

import ba.reservation.airlinemanagement.bussines.model.Client;
import ba.reservation.airlinemanagement.bussines.model.User;

import java.util.List;

public interface ClientServiceLocal {

    List<Client> findAll();

    void create(Client client);
    void remove(Client client);
    void removeById(Integer id);
    Client find(Integer id);
}
