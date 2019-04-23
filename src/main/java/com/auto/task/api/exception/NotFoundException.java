package com.auto.task.api.exception;

/**
 * Class which specifies the Not Found Exception, which is
 * thrown for an API HTTP 404 response
 *
 */
public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7352874405743657473L;

	public NotFoundErrorDetailSection details;

	public NotFoundErrorDetailSection getDetails() {
		return details;
	}

	public void setDetails(NotFoundErrorDetailSection details) {
		this.details = details;
	}

	public NotFoundException(String msg) {
		super(msg);
		NotFoundErrorDetails detail = new NotFoundErrorDetails(msg);
		NotFoundErrorDetails[] detailArray = { detail };
		details = new NotFoundErrorDetailSection(detailArray);
	}

	/**
	 * Inner class specifying the Not Found Error detail section,
	 * which contains the 'details' tag
	 * 
	 * @author
	 *
	 */
	class NotFoundErrorDetailSection {

		public NotFoundErrorDetailSection(NotFoundErrorDetails[] details) {
			super();
			this.details = details;
		}

		public NotFoundErrorDetails[] getDetails() {
			return details;
		}

		public void setDetails(NotFoundErrorDetails[] details) {
			this.details = details;
		}

		public String getName() {
			return name;
		}

		public NotFoundErrorDetails[] details;
		public static final String name = "NotFoundError";

	}

	/**
	 * Inner class which specifies the message tag
	 *
	 */
	class NotFoundErrorDetails {

		public NotFoundErrorDetails(String message) {
			super();
			this.message = message;
		}

		public String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
