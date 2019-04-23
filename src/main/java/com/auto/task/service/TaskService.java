package com.auto.task.service;

import java.util.HashSet;
import java.util.Stack;

import org.springframework.stereotype.Service;

import com.auto.model.BalanceTestResult;
import com.auto.task.api.exception.ValidationException;

/**
 * This service implements the GET validate brackets task
 *
 */
@Service
public class TaskService {

	public void validate(String input) throws ValidationException{
		if(input.isEmpty() || input.length() > StringConstants.MAX_LENGTH) {
			throw new ValidationException("Must be between 1 and " + StringConstants.MAX_LENGTH + " chars long");
		}
	}
	/**
	 * Function to validate the brackets, stored in input string parameter
	 * '(' and ')' can be set as is
	 * '[',']','{','}' need to be URL encoded
	 * Other characters are permissible, they will be filtered out
	 * 
	 * @param input
	 * @return
	 * @throws ValidationException
	 */
	public BalanceTestResult validateBrackets(String input) throws ValidationException {
		validate(input);
		Stack<String> stack = new Stack<>();
		HashSet<String> set = new HashSet<>();

		set.add(StringConstants.OPENING_BRACE);
		set.add(StringConstants.CLOSING_BRACE);
		set.add(StringConstants.OPENING_BRACKET);
		set.add(StringConstants.CLOSING_BRACKET);
		set.add(StringConstants.OPENING_PARENTHESIS);
		set.add(StringConstants.CLOSING_PARENTHESIS);

		input.codePoints().mapToObj(c -> String.valueOf((char) c)).filter(set::contains)
				.forEach(newStr -> stackHelper(newStr, stack));

		if (stack.isEmpty()) {
			return new BalanceTestResult().input(input).isBalanced(true);
		}
		return new BalanceTestResult().input(input).isBalanced(false);
	}

	public void stackHelper(String newStr, Stack<String> stack) {
		if (stack.isEmpty()) {
			stack.push(newStr);
			return;
		}
		switch (newStr) {
		case StringConstants.CLOSING_BRACE:
			if (stack.peek().equals(StringConstants.OPENING_BRACE)) {
				stack.pop();
				return;
			}

		case StringConstants.CLOSING_BRACKET:
			if (stack.peek().equals(StringConstants.OPENING_BRACKET)) {
				stack.pop();
				return;
			}
		case StringConstants.CLOSING_PARENTHESIS:
			if (stack.peek().equals(StringConstants.OPENING_PARENTHESIS)) {
				stack.pop();
				return;
			}
		}
		stack.push(newStr);
	}
	
//	public static void main(String[] args) {
//		TaskService t = new TaskService();
//		System.out.println(t.validateBrackets("{[()]}"));
//		System.out.println(t.validateBrackets("[()]"));
//		System.out.println(t.validateBrackets("{[()]"));
//		System.out.println(t.validateBrackets(")]}{[("));
//		System.out.println(t.validateBrackets(")]}[{[("));
//	}
}
