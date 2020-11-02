package hu.uni.eku.camping.dao.entity;

import hu.uni.eku.camping.model.SlotStatus;
import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "camping_slots")
public class CampingSlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Point startCoordinate;
    @Column
    private Point endCoordinate;
    @Column
    private int price;
    @Column
    @Enumerated(EnumType.STRING)
    private SlotStatus slotStatus;
    @Column
    private String description;
    @OneToOne(mappedBy = "campingSlot")
    private ReservationEntity reservation;
}
