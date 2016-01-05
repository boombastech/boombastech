package uk.co.boombastech.http.request;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class ServletResponseImpl implements Response {

	private final HttpServletResponse response;
	private final Gson gson;
	private final List<Object> responseObjects;

	public ServletResponseImpl(HttpServletResponse response, Gson gson) {
		this.response = response;
		this.gson = gson;
		responseObjects = newArrayList();
	}

	@Override
	public void addCookie(Cookie cookie, String value) {
		javax.servlet.http.Cookie servletCookie = new javax.servlet.http.Cookie(cookie.getName(), value);
		servletCookie.setMaxAge(cookie.getDefaultMaxAge());
		response.addCookie(servletCookie);
	}

	@Override
	public void withValue(Object value) {
		responseObjects.add(value);
	}

	@Override
	public void setContentType(String contentType) {
		response.setContentType(contentType);
	}

	@Override
	public void render() {
		response.setContentType("application/json");
		try {
			String responseString;
			if (responseObjects.size() == 1) {
				responseString = gson.toJson(responseObjects.get(0));
			} else {
				responseString = gson.toJson(responseObjects);
			}
			response.getOutputStream().print(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}