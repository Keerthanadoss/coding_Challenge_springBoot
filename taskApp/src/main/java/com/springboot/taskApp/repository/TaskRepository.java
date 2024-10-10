package com.springboot.taskApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.taskApp.model.Task;

 
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
