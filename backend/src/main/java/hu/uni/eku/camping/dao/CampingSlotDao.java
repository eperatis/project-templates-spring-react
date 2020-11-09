package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.model.CampingSlot;

import java.time.LocalDate;
import java.util.Collection;

public interface CampingSlotDao {

    void create(CampingSlot campingSlot);

    Collection<CampingSlot> readAll();

    CampingSlot findByCustomerId(int id);

    CampingSlot findById(int id);

    void update(CampingSlot original, CampingSlot updated);

    void delete(CampingSlot campingSlot);

    Collection<CampingSlot> findAllBetweenInterval(LocalDate start, LocalDate end);

    boolean isReserved(int id, LocalDate date);

    boolean isReserved(int id, LocalDate start, LocalDate end);
}
