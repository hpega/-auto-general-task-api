package com.auto.task.api;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.auto.model.ToDoItem;
import com.auto.model.ToDoItemAddRequest;
import com.auto.model.ToDoItemUpdateRequest;
import com.auto.task.api.exception.NotFoundException;
import com.auto.task.api.exception.ValidationException;
import com.auto.task.service.ToDoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller endpoint for /todo
 *
 */
@Controller
public class ToDoController {

	@Autowired
	ToDoService todoService;
	
	@ApiOperation(value = "Retrieve a specific item by id", nickname = "todoIdGet", notes = "", response = ToDoItem.class, tags = {
			"todo", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ToDoItem.class),
			@ApiResponse(code = 400, message = "Validation error", response = ValidationException.class),
			@ApiResponse(code = 404, message = "Not Found Error", response = NotFoundException.class) })
	@GetMapping(value = "/todo/{id}", produces = { "application/json" })
	ResponseEntity<ToDoItem> getToDo(@ApiParam(value = "id", required = true) @PathVariable("id") BigDecimal id) 
			throws ValidationException, NotFoundException{
		return ResponseEntity.ok().body(todoService.getToDoItem(id));
	}

	@ApiOperation(value = "Modify an item", nickname = "todoIdPatch", notes = "", response = ToDoItem.class, tags = {
			"todo", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ToDoItem.class),
			@ApiResponse(code = 400, message = "Validation error", response = ValidationException.class),
			@ApiResponse(code = 404, message = "Not Found Error", response = NotFoundException.class) })
	@PatchMapping(value = "/todo/{id}", produces = { "application/json" }, consumes = {
			"application/json" })
	ResponseEntity<ToDoItem> patchToDo(@ApiParam(value = "id", required = true) @PathVariable("id") BigDecimal id,
			@Valid @RequestBody ToDoItemUpdateRequest body) throws ValidationException, NotFoundException{
		return ResponseEntity.ok().body(todoService.patchToDo(id, body));
	}

	@ApiOperation(value = "Create a to do item", nickname = "todoPost", notes = "", response = ToDoItem.class, tags = {
			"todo", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ToDoItem.class),
			@ApiResponse(code = 400, message = "Validation error", response = ValidationException.class) })
	@PostMapping(value = "/todo", produces = { "application/json" }, consumes = {
			"application/json" })
	
	ResponseEntity<ToDoItem> postToDo(
			@ApiParam(value = "", required = true) @RequestBody ToDoItemAddRequest body) throws ValidationException{
		return ResponseEntity.ok().body(todoService.createToDo(body.getText()));
	}

}
