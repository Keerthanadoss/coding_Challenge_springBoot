package com.springboot.taskApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.taskApp.exception.InvalidIdException;
import com.springboot.taskApp.model.Task;
import com.springboot.taskApp.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public List<Task> retriveAllTask() {
		return taskRepository.findAll();
		
	}

	public Task getById(int taskId) throws InvalidIdException {
		Optional<Task> optional=taskRepository.findById(taskId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid task id");
		Task task=optional.get();
		return task;	
	}

	public Task addNewTask(Task task) {
		 return taskRepository.save(task);		
	}

	public Task updateTask(int taskId, Task task) throws InvalidIdException {
		Optional<Task> optional=taskRepository.findById(taskId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid task id");
		Task t=optional.get();
		t.setDueDate(task.getDueDate());
		t.setStatus(task.getStatus());
		return t;
	}

	public void deleteTask(int taskId) throws InvalidIdException {
		Optional<Task> optional=taskRepository.findById(taskId);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid task id");
		taskRepository.deleteById(taskId);	
	}

}
