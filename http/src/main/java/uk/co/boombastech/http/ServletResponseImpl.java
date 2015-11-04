package uk.co.boombastech.http;

import com.google.common.collect.Maps;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ServletResponseImpl implements Response {

	private final HttpServletResponse response;
	private final Gson gson;
	private final Map<String, Object> responseObjects;

	public ServletResponseImpl(HttpServletResponse response, Gson gson) {
		this.response = response;
		this.gson = gson;
		this.responseObjects = newHashMap();

		response.setContentType("application/json");
	}

	@Override
	public void addCookie(Cookie cookie, String value) {
		javax.servlet.http.Cookie servletCookie = new javax.servlet.http.Cookie(cookie.getName(), value);
		servletCookie.setMaxAge(cookie.getDefaultMaxAge());
		response.addCookie(servletCookie);
	}

	@Override
	public void withValue(String key, Object value) {
		responseObjects.put(key, value);
	}

	@Override
	public void setContentType(String contentType) {
		response.setContentType(contentType);
	}

	@Override
	public void render() {
		String json = gson.toJson(responseObjects);
		try {
			response.getOutputStream().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}