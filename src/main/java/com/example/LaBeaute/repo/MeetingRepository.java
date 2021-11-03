package com.example.LaBeaute.repo;


import com.example.LaBeaute.models.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
}
