package uk.co.boombastech.utils;

import java.util.Optional;

public class NumberUtils {

	public static Optional<Integer> parseInteger(String potentialInteger) {
		try {
			return Optional.of(Integer.parseInt(potentialInteger));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}
}