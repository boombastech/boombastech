package uk.co.boombastech.authentication.forgottenpassword;

import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.UserRepository;
import uk.co.boombastech.authentication.exceptions.AuthenticationException;
import uk.co.boombastech.authentication.exceptions.UnknownUserException;
import uk.co.boombastech.common.email.Email;
import uk.co.boombastech.common.email.EmailService;

public class ForgottenPasswordServiceImpl implements ForgottenPasswordService {

	private final UserRepository userRepository;
	private final ForgottenPasswordRepository forgottenPasswordRepository;
	private final EmailService emailService;

	public ForgottenPasswordServiceImpl(UserRepository userRepository, ForgottenPasswordRepository forgottenPasswordRepository, EmailService emailService) {
		this.userRepository = userRepository;
		this.forgottenPasswordRepository = forgottenPasswordRepository;
		this.emailService = emailService;
	}

	@Override
	public String createForgottenPasswordCode(ForgottenPasswordCodeMessage forgottenPasswordCodeMessage) throws AuthenticationException {
		Profile profile = userRepository.findByEmail(forgottenPasswordCodeMessage.getEmail()).orElseThrow(() -> new UnknownUserException(""));

		String passwordResetCode = forgottenPasswordRepository.createPasswordReset(profile);

		Email registrationEmail = new Email(profile.getEmail(), "emailFromPropertiesFile", "Password Reset", String.format("Here's your code: %s", passwordResetCode));
		emailService.send(registrationEmail);

		return passwordResetCode;
	}
}