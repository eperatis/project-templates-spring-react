package hu.uni.eku.camping.controller.dto;

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
    private String name;
    private Point startCoordinate;
    private Point endCoordinate;
    private String description;
}
