package hu.uni.eku.camping.model;

import java.util.Collection;

public class ElectricityExpanse extends ExpanseDecorator {
    Billable billable;
    public static final int ELECTRICITY_PRICE = 400;

    public ElectricityExpanse(Billable billable) {
        this.billable = billable;
    }

    @Override
    public Collection<Expense> getExpenses() {
        Collection<Expense> expenses = billable.getExpenses();
        expenses.add(Expense.builder().name("electricity").price(ELECTRICITY_PRICE).build());
        return expenses;
    }
}
