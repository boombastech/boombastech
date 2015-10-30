package uk.co.boombastech.authentication.login;

import uk.co.boombastech.authentication.exceptions.InvalidPasswordLoginException;
import uk.co.boombastech.authentication.exceptions.UnknownUserException;

public interface LoginService {
	LoginResult login(LoginMessage loginMessage) throws UnknownUserException, InvalidPasswordLoginException;
}