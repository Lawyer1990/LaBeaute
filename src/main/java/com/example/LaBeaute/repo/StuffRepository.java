package com.example.LaBeaute.repo;

import com.example.LaBeaute.models.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StuffRepository extends JpaRepository<Stuff, Long> {
    

}
