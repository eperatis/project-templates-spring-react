package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.model.Billable;
import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.model.Reservation;

import java.util.Collection;

public interface ReservationDao {

    void create(Reservation reservation, Customer customer, int campingSlotId);

    Collection<Billable> readAll();

    Billable findByCustomer(int id);

    void update(Reservation original, Reservation updated);

    void delete(Reservation reservation);

    void payReservation(int customerId);
}
