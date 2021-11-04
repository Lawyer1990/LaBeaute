package com.example.LaBeaute.repo;

import com.example.LaBeaute.models.Stuff;
import org.springframework.data.repository.CrudRepository;

public interface StuffRepository extends CrudRepository<Stuff, Long> {
    Stuff getStuff(Long id);

}
