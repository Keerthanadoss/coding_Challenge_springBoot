package com.springboot.taskApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.taskApp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
