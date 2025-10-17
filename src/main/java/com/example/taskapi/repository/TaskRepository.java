package com.example.taskapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.taskapi.model.Task;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByNameContainingIgnoreCase(String name);
}