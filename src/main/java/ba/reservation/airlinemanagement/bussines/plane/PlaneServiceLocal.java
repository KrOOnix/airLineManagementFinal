package ba.reservation.airlinemanagement.bussines.plane;

import ba.reservation.airlinemanagement.bussines.model.Plane;
import ba.reservation.airlinemanagement.bussines.model.User;

import java.util.List;

public interface PlaneServiceLocal {
    List<Plane> findAll();

    void create(Plane plane);

    void edit(Plane plane);

    void remove(Plane plane);

    void removeById(Integer id);

    Plane find(Integer id);
}
