package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CampingSlotEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface CampingSlotRepository extends CrudRepository<CampingSlotEntity, Integer> {
    @Query(
            "SELECT c FROM CampingSlotEntity c JOIN c.reservations r " +
                    "WHERE r.customer.id = ?1"
    )
    CampingSlotEntity findByReservation_Customer_Id(int id);

    CampingSlotEntity findById(int id);

    @Query(
            "SELECT DISTINCT c FROM CampingSlotEntity c LEFT JOIN c.reservations r " +
                    "WHERE r.id is null or r.start > ?2 or r.end < ?1"
    )
    Collection<CampingSlotEntity> findAllBetweenInterval(LocalDate start, LocalDate end);

    @Query(
            "SELECT COUNT(c) FROM CampingSlotEntity c JOIN c.reservations r " +
                    "WHERE c.id = ?1 AND r.start <= ?2 AND r.end >= ?2"
    )
    long numberOfReservationsForSlot(int id, LocalDate date);
}
