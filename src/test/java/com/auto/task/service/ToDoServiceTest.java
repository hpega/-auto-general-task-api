package com.auto.task.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.threeten.bp.Instant;

import com.auto.model.ToDoItem;
import com.auto.model.ToDoItemUpdateRequest;
import com.auto.task.api.exception.NotFoundException;
import com.auto.task.api.exception.ValidationException;
import com.auto.task.entity.ToDo;
import com.auto.task.entity.ToDoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ToDoServiceTest {

	@Mock
	private ToDoRepository todoRepository;

	@InjectMocks
	private ToDoService service;

	@Before
	public void setUp() {
		ToDo toDo = new ToDo();
		toDo.setCreatedAt(Instant.now().toString());
		toDo.setId(BigDecimal.valueOf(1));
		toDo.setText("homework");
		toDo.setIsCompleted(Boolean.FALSE);
		Mockito.when(todoRepository.save(Mockito.any())).thenReturn(toDo);
		Mockito.when(todoRepository.saveAndFlush(Mockito.any())).thenReturn(toDo);
		Mockito.when(todoRepository.findById(BigDecimal.valueOf(1))).thenReturn(Optional.of(toDo));
	}

	@Test
	public void testGetToDo() throws ValidationException, NotFoundException {
		ToDo todo = service.getToDo(BigDecimal.valueOf(1));
		assertTrue(Long.valueOf(1L).equals(todo.getId().longValue()));
		assertTrue("homework".equals(todo.getText()));
		assertTrue(todo.getIsCompleted().booleanValue() == Boolean.FALSE);
	}

	@Test
	public void testGet() throws ValidationException, NotFoundException {
		ToDoItem todo = service.getToDoItem(BigDecimal.valueOf(1));
		assertTrue(Long.valueOf(1L).equals(todo.getId().longValue()));
		assertTrue("homework".equals(todo.getText()));
		assertTrue(todo.isIsCompleted().booleanValue() == Boolean.FALSE);
	}

	@Test
	public void testCreate() throws ValidationException {				
		ToDoItem todo = service.createToDo("homework");
		assertTrue(todo.getText().equals("homework"));
		assertNotNull(todo.getCreatedAt());
		assertTrue(todo.getCreatedAt().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}[.]\\d{3}Z"));
		assertFalse(todo.isIsCompleted());
	}

	@Test
	public void testUpdate() throws NotFoundException, ValidationException {
		String text = "homework";
		
		ToDoItem todo = service.patchToDo(BigDecimal.valueOf(1), new ToDoItemUpdateRequest().text(text));
		assertTrue(todo.getId().equals(BigDecimal.valueOf(1)));
		assertTrue(todo.getText().equals(text));
	}

}
