package uk.co.boombastech.authentication.login;

public class LoginMessage {

	private final String email;
	private final String password;
	private final boolean remember;

	public LoginMessage(String email, String password, boolean remember) {
		this.email = email;
		this.password = password;
		this.remember = remember;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public boolean isRemember() {
		return remember;
	}
}