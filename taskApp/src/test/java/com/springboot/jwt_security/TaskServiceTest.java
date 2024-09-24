package com.springboot.jwt_security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.taskApp.JwtSecurityApplication;
import com.springboot.taskApp.enums.Priority;
import com.springboot.taskApp.enums.Status;
import com.springboot.taskApp.exception.InvalidIdException;
import com.springboot.taskApp.model.Task;
import com.springboot.taskApp.service.TaskService;

@SpringBootTest(classes = JwtSecurityApplication.class)
public class TaskServiceTest {
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void retriveAllTaskTest() {
		int expectedNum=3;
		int actualNum=taskService.retriveAllTask().size();
		assertEquals(expectedNum,actualNum);
	}
	
	@Test
	public void getTaskById() {
		
		//Use case 1: Valid Id
		int expectedNum=1;
		int id=1;
		List<Task> list=new ArrayList<>();
		try {
			Task task=taskService.getById(id);
			list.add(task);
			int actualNum=list.size();
			assertEquals(expectedNum, actualNum);
		} catch (InvalidIdException e) {
			e.getMessage();
		}
		
		//Use case 2:Invalid Id
		int Invalidid=4;
		try {
			taskService.getById(Invalidid);
		} catch (InvalidIdException e) {
			assertEquals(e.getMessage(),"Invalid task id");
		}	
	}
	
	@Test
	public void addTaskTest() {
		
		int expectedNum=3;
		int actualNum=taskService.retriveAllTask().size();
		assertEquals(expectedNum,actualNum); 
		
		int expectedNum1=4;
		Task task=new Task(4,"create salary api","create new salary api for project",LocalDate.now(),Priority.valueOf("HIGH"),Status.valueOf("PENDING"));
		Task task1=taskService.addNewTask(task);
		int actualNum1=taskService.retriveAllTask().size();
		assertEquals(expectedNum1, actualNum1);
	}
	

}
