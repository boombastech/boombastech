package uk.co.boombastech.authentication.login;

public class LogoutServiceImpl implements LogoutService {

	private final ActiveUsersRepository activeUsersRepository;

	public LogoutServiceImpl(ActiveUsersRepository activeUsersRepository) {
		this.activeUsersRepository = activeUsersRepository;
	}

	@Override
	public void logout(String sessionId) {
		activeUsersRepository.removeProfile(sessionId);
	}
}