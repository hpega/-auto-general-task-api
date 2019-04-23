package com.auto.task.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.auto.task.api.exception.ValidationException;

@RunWith(Theories.class)
public class TaskServiceTheoryTest {

	private TaskService service = new TaskService();

	private static ValidationException validationException = new ValidationException(
			"Must be between 1 and " + StringConstants.MAX_LENGTH + " chars long");

	@DataPoints
	public static Collection<Pair<String, ValidationException>> validateDataPoints() {
		Collection<Pair<String, ValidationException>> collection = new ArrayList<>();
		collection.add(new Pair<String, ValidationException>("", validationException));

		collection.add(new Pair<String, ValidationException>("a string", null));
		collection
				.add(new Pair<String, ValidationException>(
						"a string longer than 100 chars,a string longer than 100 chars,"
								+ "a string longer than 100 chars,a string longer than 100 chars",
						validationException));
		return collection;
	}

	@Theory
	public void testValidate(Pair<String, ValidationException> input) {
		try {
			service.validate(input.getFirst());
		} catch (Exception ex) {
			assertTrue(ex instanceof ValidationException);
			assertTrue(input.getSecond().equals(validationException));
		}
	}
}
