package hu.uni.eku.camping.model;

import java.util.Collection;

public abstract class ExpanseDecorator extends Billable {
    @Override
    public abstract Collection<Expense> getExpenses();
}
