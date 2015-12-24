package uk.co.boombastech.http.request;

public interface Response {
	void addCookie(Cookie cookie, String value);
	void withValue(String key, Object value);
	void setContentType(String contentType);
	void render();
}