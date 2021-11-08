package com.example.LaBeaute.repo;

import com.example.LaBeaute.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    

}
