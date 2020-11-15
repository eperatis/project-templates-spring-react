package hu.uni.eku.camping.controller.dto;

import hu.uni.eku.camping.model.SlotStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.geo.Point;

@Data
@Builder
public class CampingSlotDto {
    private int id;
    private String name;
    private Point startCoordinate;
    private Point endCoordinate;
    private SlotStatus slotStatus;
    private String description;
}
