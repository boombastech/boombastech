package uk.co.boombastech.authentication.register;

import org.junit.Before;
import org.junit.Test;
import uk.co.boombastech.utils.UUIDUniqueStringGenerator;
import uk.co.boombastech.utils.UniqueStringGenerator;
import uk.co.boombastech.authentication.UserRepository;
import uk.co.boombastech.authentication.UserRepositoryStub;
import uk.co.boombastech.authentication.exceptions.NonMatchingPasswordsException;
import uk.co.boombastech.authentication.exceptions.UserAlreadyExistsException;
import uk.co.boombastech.email.EmailServiceStub;
import uk.co.boombastech.encryption.EncryptionService;
import uk.co.boombastech.encryption.EncryptionServiceStub;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.boombastech.authentication.ProfileBuilder.newProfile;
import static uk.co.boombastech.authentication.register.RegistrationMessageBuilder.newRegisterMessage;

public class RegistrationServiceImplTest {

	private RegistrationService registrationService;

	private UserRepository userRepository;
	private EncryptionService encryptionService;
	private UniqueStringGenerator uniqueStringGenerator;
	private EmailServiceStub emailService;

	@Before
	public void setup() throws Exception {
		userRepository = new UserRepositoryStub()
				.withProfile(newProfile().withEmail("existing@email.com"));

		encryptionService = new EncryptionServiceStub();

		uniqueStringGenerator = new UUIDUniqueStringGenerator();

		emailService = new EmailServiceStub();

		registrationService = new RegistrationServiceImpl(userRepository, encryptionService, uniqueStringGenerator, emailService);
	}

	@Test
	public void shouldAddProfileToUserRepository() throws Exception {
		RegistrationMessage validRegistrationMessage = newRegisterMessage().withEmail("new@email.com").withPassword("password").withConfirmPassword("password").build();

		registrationService.register(validRegistrationMessage);

		assertThat(userRepository.findByEmail("new@email.com").isPresent()).isTrue();

		assertThat(emailService.getEmailsSentTo("new@email.com")).hasSize(1);
	}

	@Test(expected = UserAlreadyExistsException.class)
	public void shouldThrowExceptionWhenEmailAlreadyExists() throws Exception {
		RegistrationMessage validRegistrationMessage = newRegisterMessage().withEmail("existing@email.com").build();

		registrationService.register(validRegistrationMessage);
	}

	@Test(expected = NonMatchingPasswordsException.class)
	public void shouldThrowExceptionWhenPasswordsSuppliedDoNotMatch() throws Exception {
		RegistrationMessage validRegistrationMessage = newRegisterMessage().withEmail("new@email.com").withPassword("password1").withConfirmPassword("password2").build();

		registrationService.register(validRegistrationMessage);
	}
}