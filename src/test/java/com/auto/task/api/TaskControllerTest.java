package com.auto.task.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.auto.model.BalanceTestResult;
import com.auto.task.api.exception.ValidationException;
import com.auto.task.service.TaskService;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

	@Mock
	private TaskService service;
	
	@InjectMocks
	private TaskController controller;
	
	@Test
	public void testGetTask() throws ValidationException {
		String input = "()";
		BalanceTestResult value = new BalanceTestResult().input(input).isBalanced(true);
		Mockito.when(service.validateBrackets(input)).thenReturn(value);
		
		assertTrue(controller.tasksValidateBracketsGet(input)
				.getBody().equals(value));
	}
	
}
