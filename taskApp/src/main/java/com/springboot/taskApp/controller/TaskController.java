package com.springboot.taskApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.taskApp.dto.MessageDto;
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
	
	@GetMapping("/getById/{taskId}")
	public ResponseEntity<?> getById(@PathVariable int taskId,MessageDto dto) {
		try {
			Task task=taskService.getById(taskId);
			return ResponseEntity.ok(task);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/add")
	public Task addNewTask(@RequestBody Task task) {
		return taskService.addNewTask(task);
	}
	
	@PutMapping("/update/{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable int taskId,MessageDto dto) {
		try {
			Task t=taskService.updateTask(taskId,task);
			return ResponseEntity.ok(t);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable int taskId,MessageDto dto) {
		try {
			taskService.deleteTask(taskId);
			dto.setMsg("Task Deleted...");
			return ResponseEntity.ok(dto);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}

}
