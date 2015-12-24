package uk.co.boombastech.http.controllers;

import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;

public interface Controller {

	void execute(Request request, Response response);
}