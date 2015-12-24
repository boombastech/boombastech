package uk.co.boombastech.authentication.login;

import org.junit.Before;
import org.junit.Test;
import uk.co.boombastech.utils.UUIDUniqueStringGenerator;
import uk.co.boombastech.utils.UniqueStringGenerator;
import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.UserRepository;
import uk.co.boombastech.authentication.UserRepositoryStub;
import uk.co.boombastech.authentication.exceptions.InvalidPasswordLoginException;
import uk.co.boombastech.authentication.exceptions.UnknownUserException;
import uk.co.boombastech.encryption.EncryptionService;
import uk.co.boombastech.encryption.EncryptionServiceStub;

import static uk.co.boombastech.authentication.ProfileBuilder.newProfile;
import static uk.co.boombastech.authentication.login.LoginMessageBuilder.loginMessage;
import static uk.co.boombastech.authentication.model.ProfileAssertions.assertThat;

public class LoginServiceImplTest {

	private LoginService loginService;

	private UserRepository userRepository;
	private EncryptionService encryptionService;
	private ActiveUsersRepository activeUsersRepository;
	private UniqueStringGenerator uniqueStringGenerator;

	@Before
	public void setup() throws Exception {
		userRepository = new UserRepositoryStub()
				.withProfile(newProfile()
						.withEmail("valid1@email.com")
						.withPassword("validPassword"));

		encryptionService = new EncryptionServiceStub();

		activeUsersRepository = new ActiveUsersRepositoryStub();

		uniqueStringGenerator = new UUIDUniqueStringGenerator();

		loginService = new LoginServiceImpl(userRepository, encryptionService, activeUsersRepository, uniqueStringGenerator);
	}

	@Test
	public void shouldReturnValidProfileIfLoginMessageCorrect() throws Exception {
		LoginMessage validLoginMessage = loginMessage().withLoginName("valid1@email.com").withPassword("validPassword").withRemember(true).build();

		LoginResult login = loginService.login(validLoginMessage);

		Profile profile = login.getProfile();

		assertThat(profile).hasEmail("valid1@email.com");
	}

	@Test(expected = UnknownUserException.class)
	public void shouldThrowFailedLoginExceptionWithUnknownUserMessageWhenInvalidEmailProvided() throws Exception {
		LoginMessage invalidLoginMessage = loginMessage().withLoginName("invalid@email.com").withPassword("validPassword").withRemember(true).build();

		loginService.login(invalidLoginMessage);
	}

	@Test(expected = InvalidPasswordLoginException.class)
	public void shouldThrowFailedLoginExceptionWithInvalidPasswordMessageWhenIncorrectPasswordProvided() throws Exception {
		LoginMessage invalidLoginMessage = loginMessage().withLoginName("valid1@email.com").withPassword("invalidPassword").withRemember(true).build();

		loginService.login(invalidLoginMessage);
	}
}