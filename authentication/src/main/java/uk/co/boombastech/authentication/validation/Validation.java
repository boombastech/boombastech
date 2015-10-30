package uk.co.boombastech.authentication.validation;

public interface Validation<T> {
	boolean validate(T t);
}