package uk.co.boombastech.authentication.exceptions;

public class InvalidPasswordLoginException extends AuthenticationException {
	private final String email;

	public InvalidPasswordLoginException(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}