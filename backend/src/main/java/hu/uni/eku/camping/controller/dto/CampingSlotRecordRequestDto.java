package hu.uni.eku.camping.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampingSlotRecordRequestDto {
    private Point startCoordinate;
    private Point endCoordinate;
    private int price;
    private boolean reserved;
    private String description;
}
