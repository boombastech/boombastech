package uk.co.boombastech.authentication.forgottenpassword;

import uk.co.boombastech.authentication.exceptions.AuthenticationException;

public interface ForgottenPasswordService {
	String createForgottenPasswordCode(ForgottenPasswordCodeMessage passwordResetMessage) throws AuthenticationException;
}