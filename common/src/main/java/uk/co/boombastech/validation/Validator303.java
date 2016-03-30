package uk.co.boombastech.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

public class Validator303 implements Validator {

	private final javax.validation.Validator validator;

	public Validator303(javax.validation.Validator validator) {
		this.validator = validator;
	}

	@Override
	public <T> ValidationReport validate(T object) {
		Set<ConstraintViolation<T>> validationErrors = validator.validate(object);
		List<ValidationError> validationErrorList = newArrayList();

		for (ConstraintViolation<T> validationError : validationErrors) {
			String field = validationError.getPropertyPath().iterator().next().getName();
			String message = validationError.getMessage();
			String annotation = StringUtils.substringAfterLast(validationError.getConstraintDescriptor().getAnnotation().annotationType().toString(), ".");
			validationErrorList.add(new ValidationError(field, message, annotation));
		}

		return new ValidationReport(validationErrorList);
	}
}