package hu.uni.eku.camping.dao.entity;

import lombok.*;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.Collection;

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
    private String description;

    @OneToMany(
            mappedBy = "campingSlot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Collection<ReservationEntity> reservations;
}
