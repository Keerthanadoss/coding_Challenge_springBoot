package com.springboot.taskApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskApp.model.Task;
import com.springboot.taskApp.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public List<Task> retriveAllTask() {
		return taskRepository.findAll();
		
	}

}
