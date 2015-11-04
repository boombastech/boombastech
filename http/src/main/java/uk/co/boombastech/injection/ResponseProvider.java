package uk.co.boombastech.injection;

import com.google.gson.Gson;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.http.ServletResponseImpl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletResponse;

public class ResponseProvider implements Provider<Response> {

	private final Response response;
	private final Gson gson;

	@Inject
	public ResponseProvider(HttpServletResponse response, Gson gson) {
		this.response = new ServletResponseImpl(response, gson);
		this.gson = gson;
	}

	@Override
	public Response get() {
		return response;
	}
}