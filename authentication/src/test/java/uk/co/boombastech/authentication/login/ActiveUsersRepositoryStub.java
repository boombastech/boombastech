package uk.co.boombastech.authentication.login;

import uk.co.boombastech.authentication.Profile;
import uk.co.boombastech.authentication.login.ActiveUsersRepository;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ActiveUsersRepositoryStub implements ActiveUsersRepository {

	private final Map<String, Profile> activeUsers;

	public ActiveUsersRepositoryStub() {
		activeUsers = newHashMap();
	}

	@Override
	public void addProfile(String sessionId, Profile profile) {
		activeUsers.put(sessionId, profile);
	}

	@Override
	public void removeProfile(String sessionId) {
		activeUsers.remove(sessionId);
	}

	@Override
	public Profile getActiveUser(String sessionId) {
		return activeUsers.get(sessionId);
	}
}