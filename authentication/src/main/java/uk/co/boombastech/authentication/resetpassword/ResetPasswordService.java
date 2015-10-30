package uk.co.boombastech.authentication.resetpassword;

import uk.co.boombastech.authentication.exceptions.AuthenticationException;

public interface ResetPasswordService {
	void resetPassword(ResetPasswordMessage resetPasswordMessage) throws AuthenticationException;
}