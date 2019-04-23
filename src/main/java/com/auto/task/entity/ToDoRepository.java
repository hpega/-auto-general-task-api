package com.auto.task.entity;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository class for JPA methods
 *
 */
public interface ToDoRepository extends JpaRepository<ToDo, BigDecimal> {
	
	Optional<ToDo> findById(BigDecimal id);
	
	@SuppressWarnings("unchecked")
	ToDo save(ToDo todo);
	
	@SuppressWarnings("unchecked")
	ToDo saveAndFlush(ToDo todo);
}
