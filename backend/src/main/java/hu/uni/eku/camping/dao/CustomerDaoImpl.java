package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CustomerEntity;
import hu.uni.eku.camping.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class CustomerDaoImpl implements CustomerDao {
    private final CustomerRepository repository;

    @Override
    public void create(Customer customer) {
        repository.save(CustomerEntityModelConverter.model2entity(customer));
    }

    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(CustomerEntityModelConverter::entity2model)
                .collect(Collectors.toList());
    }

    @Override
    public Customer findById(int id) {
        return CustomerEntityModelConverter.entity2model(repository.findById(id));
    }

    @Override
    public void update(Customer original, Customer updated) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public int getId(Customer customer) {
        Customer id = readAll().stream().filter(c -> c.getFirstName().equals(customer.getFirstName())
                && c.getLastName().equals(customer.getLastName())
                && c.getAddress().equals(customer.getAddress())).findAny().orElse(null);
        if (id == null) {
            return -1;
        }
        return id.getId();
    }

    public static class CustomerEntityModelConverter {
        public static Customer entity2model(CustomerEntity entity) {
            return new Customer(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAddress(),
                    entity.getPhoneNumber()
            );
        }

        public static CustomerEntity model2entity(Customer model) {
            return CustomerEntity.builder()
                    .firstName(model.getFirstName())
                    .lastName(model.getLastName())
                    .address(model.getAddress())
                    .phoneNumber(model.getPhoneNumber())
                    .build();
        }
    }
}
