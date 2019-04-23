package com.auto.task.api;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.threeten.bp.Instant;

import com.auto.model.ToDoItem;
import com.auto.model.ToDoItemAddRequest;
import com.auto.model.ToDoItemUpdateRequest;
import com.auto.task.api.exception.NotFoundException;
import com.auto.task.api.exception.ValidationException;
import com.auto.task.service.ToDoService;

@RunWith(MockitoJUnitRunner.class)
public class ToDoControllerTest {

	@Mock
	private ToDoService service;
	
	@InjectMocks
	private ToDoController controller;
	
	@Test
	public void testGetToDo() throws ValidationException, NotFoundException {
		BigDecimal id = BigDecimal.valueOf(1);
		String text = "homework";
		ToDoItem todo = new ToDoItem().id(id).createdAt(Instant.now().toString()).text(text).isCompleted(false);
		
		Mockito.when(service.getToDoItem(id)).thenReturn(todo);
		
		assertTrue(controller.getToDo(id).getBody().equals(todo));
	}
	
	@Test
	public void testCreateToDo() throws ValidationException, NotFoundException {
		BigDecimal id = BigDecimal.valueOf(1);
		String text = "homework";
		ToDoItem todo = new ToDoItem().id(id).createdAt(Instant.now().toString()).text(text).isCompleted(false);
		
		Mockito.when(service.createToDo(text)).thenReturn(todo);
		
		assertTrue(controller.postToDo(new ToDoItemAddRequest().text(text)).getBody().equals(todo));
	}
	
	@Test
	public void updateToDo() throws ValidationException, NotFoundException {
		BigDecimal id = BigDecimal.valueOf(1);
		String text = "homework";
		String newtext = "washing";
		ToDoItem todo = new ToDoItem().id(id).createdAt(Instant.now().toString()).text(text).isCompleted(false);
		ToDoItemUpdateRequest updateRequest = new ToDoItemUpdateRequest().text(text);
		Mockito.when(service.patchToDo(id, updateRequest)).thenReturn(todo);
		
		assertTrue(controller.patchToDo(id, updateRequest).getBody().equals(todo.text(newtext)));
	}
}
