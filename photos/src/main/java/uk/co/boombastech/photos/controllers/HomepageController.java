package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.solr.Repository;

import javax.inject.Inject;

public class HomepageController implements Controller {

	private final Repository<Photo> repository;

	@Inject
	public HomepageController(Repository<Photo> repository) {
		this.repository = repository;
	}

	@Override
	public void execute(Request request, Response response) {
		response.withValue("test", "<html><head><title>hello world</title></head><body><h1>" + this.toString() + "</h1></body></html>");
	}
}