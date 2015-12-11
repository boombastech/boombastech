package uk.co.boombastech.http;

import com.google.common.collect.Multimap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.ArrayListMultimap.create;

public class ServletRequestImpl implements Request {

	private final HttpServletRequest request;

	@Inject
	public ServletRequestImpl(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getUrl() {
		return request.getRequestURI();
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.valueOf(request.getMethod());
	}

	@Override
	public Parameters getQueryParameters() {
		Multimap<String, String> parameters = create();
		String[] queryParams;
		if (request.getQueryString() != null) {
			queryParams = request.getQueryString().split("&");
		} else {
			queryParams = new String[] {};
		}

		for (String queryParam : queryParams) {
			String[] split = queryParam.split("=");
			parameters.put(split[0], split[1]);

		}

		return new Parameters(parameters.asMap());
	}

	@Override
	public Collection<String> getQueryParameter(String parameter) {
		return getQueryParameters().getParameterValue(parameter);
	}

	@Override
	public Parameters getPostParameters() {
		Multimap<String, String> parameters = create();

		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String key : parameterMap.keySet()) {
			for (String value : parameterMap.get(key)) {
				parameters.put(key, value);
			}
		}

		return new Parameters(parameters.asMap());
	}

	@Override
	public Collection<String> getPostParameter(String parameter) {
		return getPostParameters().getParameterValue(parameter);
	}

	@Override
	public Optional<String> getHeader(Header header) {
		return Optional.ofNullable(request.getHeader(header.name()));
	}

	@Override
	public Optional<String> getCookie(Cookie cookie) {
		for (javax.servlet.http.Cookie servletCookie : request.getCookies()) {
			if (cookie.getName().equals(servletCookie.getName())) {
				return Optional.of(servletCookie.getValue());
			}
		}

		return Optional.empty();
	}
}