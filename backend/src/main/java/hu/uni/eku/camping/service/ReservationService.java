package hu.uni.eku.camping.service;

import hu.uni.eku.camping.model.Billable;
import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.model.Reservation;
import hu.uni.eku.camping.service.exceptions.ReservationAlreadyExistsException;

import java.util.Collection;

public interface ReservationService {
    void record(Reservation reservation, Customer customer, int campingSlotId) throws ReservationAlreadyExistsException;

    Billable findByCustomerId(int id);

    int getPrice(int customerId);

    Customer getCustomer(int customerId);

    Collection<Billable> readAll();

    void payReservation(int customerId);
}
