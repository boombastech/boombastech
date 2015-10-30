package uk.co.boombastech.http;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContextImpl implements Context {

	private final HttpServletRequest request;
	private final HttpServletResponse response;

	@Inject
	public ContextImpl(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}