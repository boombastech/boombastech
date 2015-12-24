package uk.co.boombastech.authentication.register;

import uk.co.boombastech.utils.Builder;

public class RegistrationMessageBuilder implements Builder<RegistrationMessage> {
	private String email;
	private String password;
	private String confirmPassword;

	public RegistrationMessageBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public RegistrationMessageBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public RegistrationMessageBuilder withConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}

	@Override
	public RegistrationMessage build() {
		return new RegistrationMessage(email, password, confirmPassword);
	}

	public static RegistrationMessageBuilder newRegisterMessage() {
		return new RegistrationMessageBuilder();
	}
}