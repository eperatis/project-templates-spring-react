package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.model.CampingSlot;
import hu.uni.eku.camping.model.SlotStatus;

import java.util.Collection;

public interface CampingSlotDao {

    void create(CampingSlot campingSlot);

    Collection<CampingSlot> readAll();

    CampingSlot findById(int id);

    void update(CampingSlot original, CampingSlot updated);

    void delete(CampingSlot campingSlot);

    void setStatus(int campingSlotId, SlotStatus slotStatus);
}
