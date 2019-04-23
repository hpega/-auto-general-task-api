package com.auto.task.service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.auto.model.ToDoItem;
import com.auto.model.ToDoItemUpdateRequest;
import com.auto.task.api.exception.NotFoundException;
import com.auto.task.api.exception.ValidationException;
import com.auto.task.entity.ToDo;
import com.auto.task.entity.ToDoRepository;

/**
 * This service implements each of the methods to retrieve, create and update a
 * TODO item.
 *
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 300)
public class ToDoService {

	@Autowired
	public ToDoService(ToDoRepository repository) {
		this.repository = repository;
	}

	private ToDoRepository repository;

	/**
	 * Get operation to return a ToDoItem based on the input parameter id
	 * 
	 * @param id
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	public ToDoItem getToDoItem(BigDecimal id) throws ValidationException, NotFoundException {
		ToDoItem item = map(getToDo(id));
		validate(item.getText());
		return item;
	}

	public void validate(String item) throws ValidationException {
		int len = item.length();
		if (len == 0 || len > StringConstants.MAX_TODO_LENGTH) {
			throw new ValidationException("Must be between 1 and " + StringConstants.MAX_TODO_LENGTH + " chars long");
		}
	}

	/**
	 * Fetch a ToDo from Repository based on input parameter id
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public ToDo getToDo(BigDecimal id) throws NotFoundException {
		try {
			ToDo todo = repository.findById(id).get();
			return todo;
		} catch (Exception ex) {
			throw new NotFoundException("Item with id " + id + " not found");
		}
	}

	public ToDo defaultToDo(BigDecimal id) throws NotFoundException {
		throw new NotFoundException("Item with id " + id + " not found");
	}

	/**
	 * Throw an error, there is no default
	 * 
	 * @return
	 */
	public static Supplier<? extends NotFoundException> throwErrorNoToDoFound(BigDecimal id) throws NotFoundException {
		throw new NotFoundException("Item with " + id + " not found");
	}

	/**
	 * Update a ToDoItem with given id parameter based on a request body
	 * 'ToDoItemUpdateRequest'
	 * 
	 * @param id
	 * @param update
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	public ToDoItem patchToDo(BigDecimal id, ToDoItemUpdateRequest update)
			throws NotFoundException, ValidationException {
		ToDo todo = getToDo(id);
		validate(update.getText());
		todo.setText(update.getText());
		todo.setIsCompleted(update.isIsCompleted());
		todo = repository.saveAndFlush(todo);
		return map(todo);
	}

	/**
	 * Create a new ToDo based on a given text
	 * 
	 * @param text
	 * @return
	 * @throws ValidationException
	 */
	public ToDoItem createToDo(String text) throws ValidationException {
		validate(text);
		ToDo todo = new ToDo();
		todo.setText(text);
		todo.setIsCompleted(false);
		todo.setCreatedAt(Instant.now().toString());
		todo = repository.save(todo);
		return map(todo);
	}

	/**
	 * Helper method to map a ToDo to a ToDoItemMap. Alternately, could have used
	 * DozerMapper
	 * 
	 * @param todo
	 * @return
	 */
	public ToDoItem map(ToDo todo) {
		ToDoItem item = new ToDoItem().id(todo.getId()).text(todo.getText()).createdAt(todo.getCreatedAt())
				.isCompleted(todo.getIsCompleted());
		return item;
	}
}
