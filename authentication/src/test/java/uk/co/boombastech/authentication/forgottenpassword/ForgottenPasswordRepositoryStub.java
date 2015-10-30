package uk.co.boombastech.authentication.forgottenpassword;

import com.google.common.collect.Maps;
import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.forgottenpassword.ForgottenPasswordRepository;

import java.util.Map;
import java.util.Optional;

public class ForgottenPasswordRepositoryStub implements ForgottenPasswordRepository {

	private final Map<String, String> passwordReset;

	public ForgottenPasswordRepositoryStub() {
		passwordReset = Maps.newHashMap();
	}

	@Override
	public Optional<String> getPasswordReset(Profile profile) {
		if (passwordReset.containsKey(profile.getEmail())) {
			return Optional.of(passwordReset.get(profile.getEmail()));
		}

		return Optional.empty();
	}

	@Override
	public String createPasswordReset(Profile profile) {
		passwordReset.put(profile.getEmail(), profile.getId());

		return null;
	}

	@Override
	public void exhaustCode(Profile profile) {
		passwordReset.remove(profile.getEmail());
	}
}