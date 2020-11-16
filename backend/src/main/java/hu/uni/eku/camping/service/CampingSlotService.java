package hu.uni.eku.camping.service;

import hu.uni.eku.camping.model.CampingSlot;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyExistsException;
import hu.uni.eku.camping.service.exceptions.StartDateBeforeEndDateException;
import hu.uni.eku.camping.service.exceptions.StartDateBeforeTodayException;

import java.time.LocalDate;
import java.util.Collection;

public interface CampingSlotService {

    void record(CampingSlot campingSlot) throws CampingSlotAlreadyExistsException;

    Collection<CampingSlot> readAll();

    Collection<CampingSlot> findAllBetweenInterval(LocalDate start, LocalDate end)
            throws StartDateBeforeTodayException, StartDateBeforeEndDateException;

    boolean isReserved(int id, LocalDate date);
}
