package uk.co.boombastech.http;

public enum CookieEnum implements Cookie {
	userSession(-1);
;

	private static final int FLASH = 0;
	private static final int SESSION = -1;

	private final int maxAge;

	CookieEnum(int maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public int getDefaultMaxAge() {
		return maxAge;
	}
}