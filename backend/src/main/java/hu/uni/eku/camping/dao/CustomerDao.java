package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.model.Customer;

import java.util.Collection;

public interface CustomerDao {

    void create(Customer customer);

    Collection<Customer> readAll();

    Customer findById(int id);

    void update(Customer original, Customer updated);

    void delete(Customer customer);

    int getId(Customer customer);
}
