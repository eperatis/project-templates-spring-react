package hu.uni.eku.camping.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRecordRequestDto {
    private CustomerRecordRequestDto customer;
    private int slotId;
    private int receptionistId;
    private Date start;
    private Date end;
    private boolean electricity;
}