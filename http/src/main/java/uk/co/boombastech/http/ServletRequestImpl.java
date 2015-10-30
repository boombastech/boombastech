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
		Multimap<Parameter, String> parameters = create();
		String[] queryParams = request.getQueryString().split("&");

		for (String queryParam : queryParams) {
			String[] split = queryParam.split("=");
			if (Parameter.hasValue(split[0])) {
				parameters.put(Parameter.valueOf(split[0]), split[1]);
			}
		}

		return new Parameters(parameters.asMap());
	}

	@Override
	public Collection<String> getQueryParameter(Parameter parameter) {
		return getQueryParameters().getParameterValue(parameter);
	}

	@Override
	public Parameters getPostParameters() {
		Multimap<Parameter, String> parameters = create();

		Map<String, String[]> parameterMap = request.getParameterMap();
		for (String key : parameterMap.keySet()) {
			if (Parameter.hasValue(key)) {
				for (String value : parameterMap.get(key)) {
					parameters.put(Parameter.valueOf(key), value);
				}
			}
		}

		return new Parameters(parameters.asMap());
	}

	@Override
	public Collection<String> getPostParameter(Parameter parameter) {
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