package uk.co.boombastech.utils;

import java.util.UUID;

public class UUIDUniqueStringGenerator implements UniqueStringGenerator {
	@Override
	public String getUniqueString() {
		return UUID.randomUUID().toString();
	}
}