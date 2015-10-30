package uk.co.boombastech.authentication.login;

import uk.co.boombastech.authentication.Profile;

public class LoginResult {
	private final Profile profile;
	private final String sessionId;

	public LoginResult(Profile profile, String sessionId) {
		this.profile = profile;
		this.sessionId = sessionId;
	}

	public Profile getProfile() {
		return profile;
	}

	public String getSessionId() {
		return sessionId;
	}
}