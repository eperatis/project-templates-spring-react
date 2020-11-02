package hu.uni.eku.camping.service;

import hu.uni.eku.camping.dao.CampingSlotDao;
import hu.uni.eku.camping.model.CampingSlot;
import hu.uni.eku.camping.service.exceptions.CampingSlotAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class CampingSlotServiceImpl implements CampingSlotService {
    private final CampingSlotDao dao;
    @Override
    public void record(CampingSlot campingSlot) throws CampingSlotAlreadyExistsException {
        final boolean isAlreadyRecorded = dao.readAll()
                .stream()
                .anyMatch(cs ->
                        cs.getStartCoordinate() == campingSlot.getStartCoordinate()
                                && cs.getEndCoordinate() == campingSlot.getEndCoordinate()
                                && cs.getDescription().equals(campingSlot.getDescription()));

        if (isAlreadyRecorded) {
            log.info("Camping Slot {} is already recorded!", campingSlot);
            throw new CampingSlotAlreadyExistsException(String.format("Camping Slot (%s) already exists!", campingSlot.toString()));
        }
        dao.create(campingSlot);
    }

    @Override
    public Collection<CampingSlot> readAll() {
        return dao.readAll();
    }
}
