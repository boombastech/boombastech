package uk.co.boombastech.http.request;

import uk.co.boombastech.http.json.JsonMarshaller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ServletResponseImpl implements Response {

	private final HttpServletResponse response;
	private final JsonMarshaller jsonMarshaller;
	private final List<Object> responseObjects;

	public ServletResponseImpl(HttpServletResponse response, JsonMarshaller jsonMarshaller) {
		this.response = response;
		this.jsonMarshaller = jsonMarshaller;
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
				responseString = jsonMarshaller.toJson(responseObjects.get(0));
			} else {
				responseString = jsonMarshaller.toJson(responseObjects);
			}
			response.getOutputStream().print(responseString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}