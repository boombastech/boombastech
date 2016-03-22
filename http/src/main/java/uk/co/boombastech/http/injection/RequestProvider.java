package uk.co.boombastech.http.injection;

import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.ServletRequestImpl;
import uk.co.boombastech.http.json.JsonMarshaller;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

public class RequestProvider implements Provider<Request> {

	private final Request request;

	@Inject
	public RequestProvider(HttpServletRequest request, JsonMarshaller jsonMarshaller) {
		this.request = new ServletRequestImpl(request, jsonMarshaller);
	}

	@Override
	public Request get() {
		return request;
	}
}