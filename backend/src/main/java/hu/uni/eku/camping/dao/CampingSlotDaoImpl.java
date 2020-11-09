package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CampingSlotEntity;
import hu.uni.eku.camping.model.CampingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CampingSlotDaoImpl implements CampingSlotDao {
    private final CampingSlotRepository repository;

    @Override
    public void create(CampingSlot campingSlot) {
        repository.save(CampingSlotEntityModelConverter.model2entity(campingSlot));
    }

    @Override
    public Collection<CampingSlot> readAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(CampingSlotEntityModelConverter::entity2model)
                .collect(Collectors.toList());
    }

    @Override
    public CampingSlot findByCustomerId(int id) {
        return CampingSlotEntityModelConverter.entity2model(repository.findByReservation_Customer_Id(id));
    }

    @Override
    public CampingSlot findById(int id) {
        return CampingSlotEntityModelConverter.entity2model(repository.findById(id));
    }

    @Override
    public void update(CampingSlot original, CampingSlot updated) {

    }

    @Override
    public void delete(CampingSlot campingSlot) {

    }

    @Override
    public Collection<CampingSlot> findAllBetweenInterval(LocalDate start, LocalDate end) {
        return repository.findAllBetweenInterval(start, end).stream()
                .map(CampingSlotEntityModelConverter::entity2model)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isReserved(int id, LocalDate date) {
        return repository.numberOfReservationsForSlot(id, date) > 0;
    }

    @Override
    public boolean isReserved(int id, LocalDate start, LocalDate end) {
        return findAllBetweenInterval(start, end).stream()
                .anyMatch(s -> s.getId() == id);
    }

    public static class CampingSlotEntityModelConverter {
        public static CampingSlot entity2model(CampingSlotEntity entity) {
            return new CampingSlot(
                    entity.getId(),
                    entity.getStartCoordinate(),
                    entity.getEndCoordinate(),
                    entity.getDescription()
            );
        }

        public static CampingSlotEntity model2entity(CampingSlot model) {
            return CampingSlotEntity.builder()
                    .startCoordinate(model.getStartCoordinate())
                    .endCoordinate(model.getEndCoordinate())
                    .description(model.getDescription())
                    .build();
        }
    }
}
