package hu.uni.eku.camping.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRecordRequestDto {
    private CustomerRecordRequestDto customer;
    private int slotId;
    private LocalDate start;
    private LocalDate end;
    private boolean electricity;
}
