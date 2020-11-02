package hu.uni.eku.camping.model;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Billable {
    Collection<Expense> expenses;
    int days;

    public Collection<Expense> getExpenses() {
        return new ArrayList<>();
    }

    int getDays() {
        return 0;
    }
}
