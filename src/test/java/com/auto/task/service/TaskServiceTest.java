package com.auto.task.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import com.auto.model.BalanceTestResult;

@RunWith(JUnit4.class)
public class TaskServiceTest {

	@Autowired
	private TaskService service;

	@Test
	public void testValidateBrackets() {
		BalanceTestResult result;
		try {
			result = service.validateBrackets("{[()]}");
			assertTrue(result.isIsBalanced());
			
			result = service.validateBrackets("[()]");
			assertTrue(result.isIsBalanced());
			
			result = service.validateBrackets("{[()]");
			assertFalse(result.isIsBalanced());
			
			result = service.validateBrackets(")]}{[(");
			assertFalse(result.isIsBalanced());
			
			result = service.validateBrackets(")]}[{[(");
			assertFalse(result.isIsBalanced());
			
		} catch (Exception ex) {

		}
	}

}
