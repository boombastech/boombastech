package uk.co.boombastech.authentication.login;

import uk.co.boombastech.utils.UniqueStringGenerator;
import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.UserRepository;
import uk.co.boombastech.authentication.exceptions.InvalidPasswordLoginException;
import uk.co.boombastech.authentication.exceptions.UnknownUserException;
import uk.co.boombastech.encryption.EncryptionService;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;
	private final EncryptionService encryptionService;
	private final ActiveUsersRepository activeUsersRepository;
	private final UniqueStringGenerator uniqueStringGenerator;

	public LoginServiceImpl(UserRepository userRepository, EncryptionService encryptionService, ActiveUsersRepository activeUsersRepository, UniqueStringGenerator uniqueStringGenerator) {
		this.userRepository = userRepository;
		this.encryptionService = encryptionService;
		this.activeUsersRepository = activeUsersRepository;
		this.uniqueStringGenerator = uniqueStringGenerator;
	}

	@Override
	public LoginResult login(LoginMessage loginMessage) throws UnknownUserException, InvalidPasswordLoginException {
		Optional<Profile> potentialProfile = userRepository.findByEmail(loginMessage.getEmail());
		Profile profile = potentialProfile.orElseThrow(() -> new UnknownUserException(loginMessage.getEmail()));

		String encryptedPassword = encryptionService.encrypt(loginMessage.getPassword());
		if (!encryptedPassword.equals(profile.getPassword())) {
			throw new InvalidPasswordLoginException(loginMessage.getEmail());
		}

		String sessionId = uniqueStringGenerator.getUniqueString();

		activeUsersRepository.addProfile(sessionId, profile);

		return new LoginResult(profile, sessionId);
	}
}