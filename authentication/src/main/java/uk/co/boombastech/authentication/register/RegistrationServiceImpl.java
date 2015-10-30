package uk.co.boombastech.authentication.register;

import uk.co.boombastech.common.utils.UniqueStringGenerator;
import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.UserRepository;
import uk.co.boombastech.authentication.exceptions.AuthenticationException;
import uk.co.boombastech.authentication.exceptions.NonMatchingPasswordsException;
import uk.co.boombastech.authentication.exceptions.UserAlreadyExistsException;
import uk.co.boombastech.common.email.Email;
import uk.co.boombastech.common.email.EmailService;
import uk.co.boombastech.common.encryption.EncryptionService;

public class RegistrationServiceImpl implements RegistrationService {

	private final UserRepository userRepository;
	private final EncryptionService encryptionService;
	private final UniqueStringGenerator uniqueStringGenerator;
	private final EmailService emailService;

	public RegistrationServiceImpl(UserRepository userRepository, EncryptionService encryptionService, UniqueStringGenerator uniqueStringGenerator, EmailService emailService) {
		this.userRepository = userRepository;
		this.encryptionService = encryptionService;
		this.uniqueStringGenerator = uniqueStringGenerator;
		this.emailService = emailService;
	}

	@Override
	public Profile register(RegistrationMessage registrationMessage) throws AuthenticationException {
		if (emailAlreadyExists(registrationMessage)) {
			throw new UserAlreadyExistsException();
		}

		if (!registrationMessage.getPassword().equals(registrationMessage.getConfirmPassword())) {
			throw new NonMatchingPasswordsException();
		}

		Profile profile = new Profile(uniqueStringGenerator.getUniqueString(), registrationMessage.getEmail(), encryptionService.encrypt(registrationMessage.getPassword()));

		profile = userRepository.create(profile);

		Email registrationEmail = new Email(profile.getEmail(), "emailFromPropertiesFile", "Welcome", "You're registered!");
		emailService.send(registrationEmail);

		return profile;
	}

	private boolean nonMatchingPasswords(RegistrationMessage registrationMessage) {
		return !registrationMessage.getPassword().equals(registrationMessage.getConfirmPassword());
	}

	private boolean emailAlreadyExists(RegistrationMessage registrationMessage) {
		return userRepository.findByEmail(registrationMessage.getEmail()).isPresent();
	}
}