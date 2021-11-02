package com.example.LaBeaute.repo;


import com.example.LaBeaute.models.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long> {
}
