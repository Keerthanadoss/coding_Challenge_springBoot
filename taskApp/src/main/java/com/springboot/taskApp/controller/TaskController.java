package com.springboot.taskApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.taskApp.exception.InvalidIdException;
import com.springboot.taskApp.model.Task;
import com.springboot.taskApp.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/getAll")
	public List<Task> retriveAllTask() {
		return taskService.retriveAllTask();
	}
	
	@GetMapping("/getById")
	public ResponseEntity<?> getById(@PathVariable int taskId) {
		try {
			Task task=taskService.getById(taskId);
			return ResponseEntity.ok(task);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
