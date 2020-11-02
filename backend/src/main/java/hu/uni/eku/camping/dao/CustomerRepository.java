package hu.uni.eku.camping.dao;

import hu.uni.eku.camping.dao.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
    CustomerEntity findById(int id);
}
