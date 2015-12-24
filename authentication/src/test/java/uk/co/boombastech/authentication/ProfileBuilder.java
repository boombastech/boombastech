package uk.co.boombastech.authentication;

import uk.co.boombastech.utils.Builder;

public class ProfileBuilder implements Builder<Profile> {
	private String id;
	private String email;
	private String password;

	public static ProfileBuilder newProfile() {
		return new ProfileBuilder();
	}

	public ProfileBuilder withId(String id) {
		this.id = id;
		return this;
	}

	public ProfileBuilder withEmail(String email) {
		this.email = email;
		return this;
	}

	public ProfileBuilder withPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public Profile build() {
		return new Profile(id, email, password);
	}
}