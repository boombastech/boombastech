package uk.co.boombastech.authentication.register;

import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.exceptions.AuthenticationException;

public interface RegistrationService {
	Profile register(RegistrationMessage registrationMessage) throws AuthenticationException;
}