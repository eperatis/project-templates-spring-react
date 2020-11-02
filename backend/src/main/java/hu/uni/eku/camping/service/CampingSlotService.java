package hu.uni.eku.camping.service;

import hu.uni.eku.camping.model.CampingSlot;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyExistsException;

import java.util.Collection;

public interface CampingSlotService {

    void record(CampingSlot campingSlot) throws CampingSlotAlreadyExistsException;

    Collection<CampingSlot> readAll();
}
