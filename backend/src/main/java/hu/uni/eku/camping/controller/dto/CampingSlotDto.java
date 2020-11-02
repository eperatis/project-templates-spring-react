package hu.uni.eku.camping.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
@Builder
public class CampingSlotDto {
    private int id;
    private Point startCoordinate;
    private Point endCoordinate;
    private int price;
    private boolean reserved;
    private String description;
}
