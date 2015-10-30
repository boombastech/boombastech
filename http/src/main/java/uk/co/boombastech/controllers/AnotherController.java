package uk.co.boombastech.controllers;

import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;

public class AnotherController implements Controller {

	@Override
	public void execute(Request request, Response response) {
		response.withValue("test", "<html><head><title>hello world</title></head><body><h1>" + this.toString() + "</h1></body></html>");
	}
}
