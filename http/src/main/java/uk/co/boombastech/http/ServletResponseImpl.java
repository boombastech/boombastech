package uk.co.boombastech.http;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletResponseImpl implements Response {

	private final HttpServletResponse response;

	@Inject
	public ServletResponseImpl(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void addCookie(Cookie cookie, String value) {
		javax.servlet.http.Cookie servletCookie = new javax.servlet.http.Cookie(cookie.getName(), value);
		servletCookie.setMaxAge(cookie.getDefaultMaxAge());
		response.addCookie(servletCookie);
	}

	@Override
	public void withValue(String key, Object value) {
		try {
			response.getOutputStream().print(value.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setContentType(String contentType) {
		response.setContentType(contentType);
	}
}