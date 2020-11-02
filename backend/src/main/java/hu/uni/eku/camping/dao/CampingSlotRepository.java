package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CampingSlotEntity;
import org.springframework.data.repository.CrudRepository;

public interface CampingSlotRepository extends CrudRepository<CampingSlotEntity, Integer> {
    CampingSlotEntity findById(int id);
}
