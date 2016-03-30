package uk.co.boombastech.validation;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ValidationReport implements Iterable<ValidationError> {

	private final List<ValidationError> validationErrors;

	public ValidationReport(List<ValidationError> validationErrors) {
		this.validationErrors = validationErrors;
	}

	public List<ValidationError> getValidationErrors() {
		return validationErrors;
	}

	@Override
	public Iterator<ValidationError> iterator() {
		return validationErrors.iterator();
	}

	@Override
	public void forEach(Consumer<? super ValidationError> action) {
		validationErrors.forEach(action);
	}

	@Override
	public Spliterator<ValidationError> spliterator() {
		return validationErrors.spliterator();
	}
}