package hu.uni.eku.camping.controller.dto;

import hu.uni.eku.camping.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {
    private CustomerRequestDto customer;
    private int slotId;
    private LocalDate start;
    private LocalDate end;
    private boolean electricity;
    private PaymentStatus paymentStatus;
}
