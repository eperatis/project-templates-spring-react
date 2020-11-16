package hu.uni.eku.camping.service;

import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.service.exceptions.CustomerAlreadyExistsException;
import hu.uni.eku.camping.service.exceptions.EmptyStringException;

import java.util.Collection;

public interface CustomerService {

    void record(Customer customer) throws CustomerAlreadyExistsException, EmptyStringException;

    Customer findById(int id);

    Collection<Customer> readAll();
}
