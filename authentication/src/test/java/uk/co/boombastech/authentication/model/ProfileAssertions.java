package uk.co.boombastech.authentication.model;

import org.assertj.core.api.AbstractAssert;
import uk.co.boombastech.authentication.Profile;

public class ProfileAssertions extends AbstractAssert<ProfileAssertions, Profile> {

	protected ProfileAssertions(Profile actual) {
		super(actual, ProfileAssertions.class);
	}

	public ProfileAssertions hasEmail(String expectedEmail) {
		if (!actual.getEmail().equalsIgnoreCase(expectedEmail)) {
			failWithMessage("\nExpected: Profile with email address: \"%s\" \n     but: had email address: \"%s\"", expectedEmail, actual.getEmail());
		}

		return this;
	}

	public ProfileAssertions hasPassword(String expectedPassword) {
		if (!actual.getPassword().equalsIgnoreCase(expectedPassword)) {
			failWithMessage("\nExpected: Profile with password: \"%s\" \n     but: had password: \"%s\"", expectedPassword, actual.getEmail());
		}

		return this;
	}

	public static ProfileAssertions assertThat(Profile profile) {
		return new ProfileAssertions(profile);
	}
}