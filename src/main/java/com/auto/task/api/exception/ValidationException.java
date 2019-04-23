package com.auto.task.api.exception;

import java.util.ArrayList;
import java.util.List;

import com.auto.model.ToDoItemValidationError;
import com.auto.model.ToDoItemValidationErrorDetails;

/**
 * Class which specifies the ValidationException Exception, which is thrown for
 * an API HTTP 400 response, when the input is in an invalid format
 *
 */
public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1527578242061465022L;

	public static final String name = "ValidationError";

	public ToDoItemValidationError errorSpecific;

	public ValidationException(String msg) {
		super(msg);
		List<ToDoItemValidationErrorDetails> details = new ArrayList<ToDoItemValidationErrorDetails>();
		ToDoItemValidationErrorDetails validationResult = new ToDoItemValidationErrorDetails().location("params")
				.msg(msg).param("text").value("");

		details.add(validationResult);
		errorSpecific = new ToDoItemValidationError().name(name).details(details);
	}

	public static String getName() {
		return name;
	}
}
