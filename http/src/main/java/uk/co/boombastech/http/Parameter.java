package uk.co.boombastech.http;

public enum Parameter {
	;

	public static boolean hasValue(String key) {
		for (Parameter parameter : Parameter.values()) {
			if (parameter.name().equals(key)) {
				return true;
			}
		}

		return false;
	}
}