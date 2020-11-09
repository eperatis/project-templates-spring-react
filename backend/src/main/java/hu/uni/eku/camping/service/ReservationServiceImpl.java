package hu.uni.eku.camping.service;

import hu.uni.eku.camping.dao.CampingSlotDao;
import hu.uni.eku.camping.dao.CustomerDao;
import hu.uni.eku.camping.dao.ReservationDao;
import hu.uni.eku.camping.model.*;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyReservedException;
import hu.uni.eku.camping.service.exceptions.ReservationAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationDao reservationDao;
    private final CampingSlotDao campingSlotDao;
    private final CustomerDao customerDao;

    @Override
    public void record(Reservation reservation, Customer customer, int campingSlotId) throws ReservationAlreadyExistsException, CampingSlotAlreadyReservedException {
        final boolean isAlreadyRecorded = reservationDao.readAll()
                .stream()
                .anyMatch(r ->
                        ((Reservation) r).getStart().equals(reservation.getStart())
                                && ((Reservation) r).getEnd().equals(reservation.getEnd())
                                && ((Reservation) r).isElectricity() == reservation.isElectricity());
        if (isAlreadyRecorded) {
            log.info("Reservation {} is already recorded!", reservation);
            throw new ReservationAlreadyExistsException(String.format("Reservation (%s) already exists!", reservation.toString()));
        }
        if (campingSlotDao.isReserved(campingSlotId, reservation.getStart(), reservation.getEnd())) {
            log.info("Camping slot {} is already reserved!", campingSlotId);
            throw new CampingSlotAlreadyReservedException(String.format("Camping slot (%s) already reserved!", campingSlotId));
        }
        reservationDao.create(reservation, customer, campingSlotId);
    }

    @Override
    public Billable findByCustomerId(int id) {
        Billable reservation = reservationDao.findByCustomer(id);
        if (((Reservation) reservation).isElectricity()) {
            reservation = new ElectricityExpanse(reservation);
        }
        return reservation;
    }

    @Override
    public Collection<Billable> readAll() {
        Collection<Billable> reservations = reservationDao.readAll();
        Collection<Billable> newReservations = new ArrayList<>();
        for (int i = 0; i < reservations.size(); i++) {
            if (((Reservation) reservations.toArray()[i]).isElectricity()) {
                newReservations.add(new ElectricityExpanse((Billable) reservations.toArray()[i]));
            } else {
                newReservations.add((Billable) reservations.toArray()[i]);
            }
        }
        return newReservations;
    }

    @Override
    public int getPrice(int id) {
        Billable reservation = reservationDao.findByCustomer(id);
        CampingSlot campingSlot = campingSlotDao.findByCustomerId(id);
        return (int) DAYS.between(((Reservation) reservation).getStart(), ((Reservation) reservation).getEnd()) * (Reservation.DAILY_PRICE +
                (((Reservation) reservation).isElectricity() ? ElectricityExpanse.ELECTRICITY_PRICE : 0) *
                        (int) (campingSlot.getStartCoordinate().getX() - campingSlot.getEndCoordinate().getX()) *
                        (int) (campingSlot.getStartCoordinate().getX() - campingSlot.getEndCoordinate().getX()));
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customerDao.findById(customerId);
    }

    @Override
    public void payReservation(int customerId) {
        reservationDao.payReservation(customerId);
    }
}
