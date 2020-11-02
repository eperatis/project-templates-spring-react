package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {
    ReservationEntity findByCustomer_Id(int id);


}
