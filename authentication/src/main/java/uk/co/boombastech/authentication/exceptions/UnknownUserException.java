package uk.co.boombastech.authentication.exceptions;

public class UnknownUserException extends AuthenticationException {

	private final String email;

	public UnknownUserException(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}