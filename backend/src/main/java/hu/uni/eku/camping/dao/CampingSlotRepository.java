package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CampingSlotEntity;
import org.springframework.data.repository.CrudRepository;

public interface CampingSlotRepository extends CrudRepository<CampingSlotEntity, Integer> {
    CampingSlotEntity findByReservation_Customer_Id(int id);
    CampingSlotEntity findById(int id);
}
