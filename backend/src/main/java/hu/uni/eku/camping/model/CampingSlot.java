package hu.uni.eku.camping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingSlot {
    private int id;
    private Point startCoordinate;
    private Point endCoordinate;
    private String description;
}
