package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.photos.PhotoCreator;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.solr.search.SolrService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class UpdateController implements Controller {

	private final SolrService<Photo> solrService;
	private final PhotoCreator photoCreator;

	@Inject
	public UpdateController(SolrService<Photo> solrService, PhotoCreator photoCreator) {
		this.solrService = solrService;
		this.photoCreator = photoCreator;
	}

	@Override
	public void execute(Request request, Response response) {
		List<Map> content = request.getContent();

		for (Map map : content) {
			solrService.update(photoCreator.fromMap(map));
		}

		solrService.commit();
	}
}