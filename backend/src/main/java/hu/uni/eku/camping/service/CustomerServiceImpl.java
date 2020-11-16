package hu.uni.eku.camping.service;

import hu.uni.eku.camping.dao.CustomerDao;
import hu.uni.eku.camping.model.Customer;
import hu.uni.eku.camping.service.exceptions.CustomerAlreadyExistsException;
import hu.uni.eku.camping.service.exceptions.EmptyStringException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao dao;

    @Override
    public void record(Customer customer) throws CustomerAlreadyExistsException, EmptyStringException {
        final boolean isAlreadyRecorded = dao.readAll()
                .stream()
                .anyMatch(c ->
                        c.getFirstName().equals(customer.getFirstName())
                                && c.getLastName().equals(customer.getLastName())
                                && c.getAddress().equals(customer.getAddress())
                                && c.getPhoneNumber().equals(customer.getPhoneNumber()));
        if (isAlreadyRecorded) {
            log.info("Customer {} is already recorded!", customer);
            throw new CustomerAlreadyExistsException(String.format("A vásárló (%s) már létezik!", customer.toString()));
        }
        CheckCustomerInput(customer);
        dao.create(customer);
    }

    static void CheckCustomerInput(Customer customer) throws EmptyStringException {
        if (customer.getAddress().equals("")) {
            throw new EmptyStringException("A cím megadása kötelező");
        }
        if (customer.getFirstName().equals("")) {
            throw new EmptyStringException("A keresztnév megadása kötelező");
        }
        if (customer.getLastName().equals("")) {
            throw new EmptyStringException("A vezetéknév megadása kötelező");
        }
        if (customer.getPhoneNumber().equals("")) {
            throw new EmptyStringException("A telefonszám megadása kötelező");
        }
    }

    @Override
    public Customer findById(int id) {
        return dao.findById(id);
    }

    @Override
    public Collection<Customer> readAll() {
        return dao.readAll();
    }
}
