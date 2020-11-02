package hu.uni.eku.camping.controller.dto;

import hu.uni.eku.camping.model.Expense;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder
public class ReservationDto {
    private CustomerDto customer;
    private int price;
    private Collection<Expense> expanses;
}
