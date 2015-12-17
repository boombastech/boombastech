package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;

public class HtmlController implements Controller {

	@Override
	public void execute(Request request, Response response) {
		response.withValue("html", "<h1>Hey</h1>");
	}
}