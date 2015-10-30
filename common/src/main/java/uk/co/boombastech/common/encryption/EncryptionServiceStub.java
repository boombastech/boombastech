package uk.co.boombastech.common.encryption;

import uk.co.boombastech.common.encryption.EncryptionService;

public class EncryptionServiceStub implements EncryptionService {
	@Override
	public String encrypt(String input) {
		return input;
	}
}