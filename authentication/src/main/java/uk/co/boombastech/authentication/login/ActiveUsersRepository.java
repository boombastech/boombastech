package uk.co.boombastech.authentication.login;

import uk.co.boombastech.authentication.Profile;

public interface ActiveUsersRepository {
	void addProfile(String sessionId, Profile profile);
	void removeProfile(String sessionId);

	Profile getActiveUser(String sessionId);
}