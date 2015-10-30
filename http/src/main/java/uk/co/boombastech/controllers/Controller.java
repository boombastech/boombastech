package uk.co.boombastech.controllers;

import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;

public interface Controller {

	void execute(Request request, Response response);
}