package hu.uni.eku.camping.dao.entity;

import hu.uni.eku.camping.model.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reservations")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private LocalDate start;

    @Column
    private LocalDate end;

    @Column
    private boolean electricity;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private CampingSlotEntity campingSlot;

    @Transient
    private long startTime;

    @Transient
    private long endTime;

    @PostLoad
    private void postLoad() {
        this.startTime = start.toEpochDay();
        this.endTime = end.toEpochDay();
    }
}
