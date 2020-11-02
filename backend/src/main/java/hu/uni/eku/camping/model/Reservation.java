package hu.uni.eku.camping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation extends Billable {
    public static final int DAILY_PRICE = 2500;
    private LocalDate start;
    private LocalDate end;
    private boolean electricity;
    private PaymentStatus paymentStatus;

    @Override
    public Collection<Expense> getExpenses() {
        Collection<Expense> expanses = new ArrayList<>();
        expanses.add(Expense.builder().name("daily price").price(DAILY_PRICE).build());
        return expanses;
    }

    @Override
    public int getDays() {
        return 0;
    }
//    private CampingSlot campingSlot;

//    private int price(){
//        return ((int)DAYS.between(getStart(), getEnd()) * DAILY_PRICE + (isElectricity() ? ELECTRICITY_PRICE : 0))
//                * (int)(campingSlot.getStartCoordinate().getX() - campingSlot.getEndCoordinate().getX())
//                * (int)(campingSlot.getStartCoordinate().getX() - campingSlot.getEndCoordinate().getX());
//    }
}
