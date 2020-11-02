package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CampingSlotEntity;
import hu.uni.eku.camping.dao.entity.ReservationEntity;
import hu.uni.eku.camping.model.Billable;
import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.model.PaymentStatus;
import hu.uni.eku.camping.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ReservationDaoImpl implements ReservationDao {
    private final ReservationRepository reservationRepository;

    @Override
    public void create(Reservation reservation, Customer customer, int campingSlotId) {
        ReservationEntity entity = ReservationEntityModelConverter.model2entity(reservation);
        entity.setCustomer(CustomerDaoImpl.CustomerEntityModelConverter.model2entity(customer));
        entity.setCampingSlot(CampingSlotEntity.builder().id(campingSlotId).build());
        reservationRepository.save(entity);
    }

    @Override
    public Collection<Billable> readAll() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .map(ReservationEntityModelConverter::entity2model)
                .collect(Collectors.toList());
    }

    @Override
    public Billable findByCustomer(int id) {
        return ReservationEntityModelConverter.entity2model(reservationRepository.findByCustomer_Id(id));
    }

    @Override
    public void update(Reservation original, Reservation updated) {

    }

    @Override
    public void delete(Reservation reservation) {

    }

    @Override
    public void payReservation(int customerId) {
        reservationRepository.findByCustomer_Id(customerId).setPaymentStatus(PaymentStatus.PAID);
    }

    private static class ReservationEntityModelConverter {
        private static Reservation entity2model(ReservationEntity entity){
            return new Reservation(
                    entity.getStart(),
                    entity.getEnd(),
                    entity.isElectricity(),
                    entity.getPaymentStatus()
            );
        }

        private static ReservationEntity model2entity(Reservation model){
            return ReservationEntity.builder()
                    .start(model.getStart())
                    .end(model.getEnd())
                    .electricity(model.isElectricity())
                    .paymentStatus(model.getPaymentStatus())
                    .build();
        }
    }
}
