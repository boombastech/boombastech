package uk.co.boombastech.http;

import java.util.Collection;
import java.util.Optional;

public interface Request {
	String getUrl();
	HttpMethod getMethod();
	Parameters getQueryParameters();
	Collection<String> getQueryParameter(Parameter parameter);
	Parameters getPostParameters();
	Collection<String> getPostParameter(Parameter parameter);
	Optional<String> getHeader(Header header);
	Optional<String> getCookie(Cookie cookie);
}