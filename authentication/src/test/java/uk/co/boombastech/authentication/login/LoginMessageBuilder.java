package uk.co.boombastech.authentication.login;

import uk.co.boombastech.utils.Builder;

public class LoginMessageBuilder implements Builder<LoginMessage> {
	private String loginName;
	private String password;
	private boolean remember;

	public LoginMessageBuilder withLoginName(String loginName) {
		this.loginName = loginName;
		return this;
	}

	public LoginMessageBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	public LoginMessageBuilder withRemember(boolean remember) {
		this.remember = remember;
		return this;
	}

	@Override
	public LoginMessage build() {
		return new LoginMessage(loginName, password, remember);
	}

	public static LoginMessageBuilder loginMessage() {
		return new LoginMessageBuilder();
	}
}