package uk.co.boombastech.authentication.forgottenpassword;

public class ForgottenPasswordCodeMessage {
	private final String email;

	public ForgottenPasswordCodeMessage(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}