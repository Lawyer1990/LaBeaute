package com.example.LaBeaute.repo;

import com.example.LaBeaute.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Posts, Long> {
    Posts getById(Long id);
}
