package uk.co.boombastech.injection;

import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.ServletRequestImpl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

public class RequestProvider implements Provider<Request> {

	private final Request request;

	@Inject
	public RequestProvider(HttpServletRequest request) {
		this.request = new ServletRequestImpl(request);
	}

	@Override
	public Request get() {
		return request;
	}
}