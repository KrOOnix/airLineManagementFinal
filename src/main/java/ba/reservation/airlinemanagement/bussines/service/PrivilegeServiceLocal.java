package ba.reservation.airlinemanagement.bussines.service;

import ba.reservation.airlinemanagement.bussines.model.Privilege;

import java.util.List;

public interface PrivilegeServiceLocal {
    List<Privilege> findAll();
}
