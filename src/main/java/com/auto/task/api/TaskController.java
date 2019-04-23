package com.auto.task.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auto.model.BalanceTestResult;
import com.auto.task.api.exception.ValidationException;
import com.auto.task.service.TaskService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller endpoint for '/tasks'
 *
 */
@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = BalanceTestResult.class),
			@ApiResponse(code = 400, message = "Validation error", response = ValidationException.class) })
	@GetMapping(value = "/tasks/validateBrackets", produces = { "application/json" })
	ResponseEntity<BalanceTestResult> tasksValidateBracketsGet(
			@RequestParam(value = "input", required = true) String input) throws ValidationException {
		return ResponseEntity.ok().body(taskService.validateBrackets(input));
	}

}
