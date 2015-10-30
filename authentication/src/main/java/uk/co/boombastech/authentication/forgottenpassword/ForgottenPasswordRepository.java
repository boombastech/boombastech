package uk.co.boombastech.authentication.forgottenpassword;

import uk.co.boombastech.authentication.Profile;

import java.util.Optional;

public interface ForgottenPasswordRepository {
	Optional<String> getPasswordReset(Profile profile);
	String createPasswordReset(Profile passwordResetObject);
	void exhaustCode(Profile profile);
}