package uk.co.boombastech.validation;

public class ValidationError {
	private final String field;
	private final String message;
	private final String annotation;

	public ValidationError(String field, String message, String annotation) {
		this.field = field;
		this.message = message;
		this.annotation = annotation;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

	public String getAnnotation() {
		return annotation;
	}
}