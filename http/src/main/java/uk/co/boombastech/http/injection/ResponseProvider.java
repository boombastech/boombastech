package uk.co.boombastech.http.injection;

import com.google.gson.Gson;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.http.request.ServletResponseImpl;
import uk.co.boombastech.json.JsonMarshaller;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletResponse;

public class ResponseProvider implements Provider<Response> {

	private final Response response;

	@Inject
	public ResponseProvider(HttpServletResponse response, JsonMarshaller jsonMarshaller) {
		this.response = new ServletResponseImpl(response, jsonMarshaller);
	}

	@Override
	public Response get() {
		return response;
	}
}