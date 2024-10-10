package com.springboot.taskApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.taskApp.dto.MessageDto;
import com.springboot.taskApp.enums.Priority;
import com.springboot.taskApp.enums.Status;
import com.springboot.taskApp.exception.InvalidIdException;
import com.springboot.taskApp.model.Task;
import com.springboot.taskApp.service.TaskService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/task/getAll")
	public List<Task> retriveAllTask() {
		return taskService.retriveAllTask();
	}
	
	@GetMapping("/task/getById/{taskId}")
	public ResponseEntity<?> getById(@PathVariable int taskId,MessageDto dto) {
		try {
			Task task=taskService.getById(taskId);
			return ResponseEntity.ok(task);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@PostMapping("/task/add")
	public Task addNewTask(@RequestBody Task task) {
		return taskService.addNewTask(task);
	}
	
	@PutMapping("/task/update/{taskId}")
	public ResponseEntity<?> updateTask(@RequestBody Task task,@PathVariable int taskId,MessageDto dto) {
		try {
			task=taskService.updateTask(taskId,task);
			return ResponseEntity.ok(task);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
	@DeleteMapping("/task/delete/{taskId}")
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
	
	@GetMapping("/task/status")
	public List<Status> getAllStatus(){
		return List.of(Status.values());
	}
	
	@GetMapping("/task/priority")
	public List<Priority> getAllPriority(){
		return List.of(Priority.values());
	}

}
