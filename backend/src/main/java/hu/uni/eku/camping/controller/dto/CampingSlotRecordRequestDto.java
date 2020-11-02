package hu.uni.eku.camping.controller.dto;

import hu.uni.eku.camping.model.SlotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampingSlotRecordRequestDto {
    private Point startCoordinate;
    private Point endCoordinate;
    private SlotStatus slotStatus;
    private String description;
}
