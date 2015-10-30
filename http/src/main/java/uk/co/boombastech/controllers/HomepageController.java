package uk.co.boombastech.controllers;

import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;

public class HomepageController implements Controller {
	@Override
	public void execute(Request request, Response response) {
		response.withValue("test", "<html><head><title>hello world</title></head><body><h1>hey</h1></body></html>");
		System.out.println("homepagecontroller.");
//		response.setContentType("json");
	}
}
