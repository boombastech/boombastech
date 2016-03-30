package uk.co.boombastech.validation;


public interface Validator {

	<T> ValidationReport validate(T object);
}