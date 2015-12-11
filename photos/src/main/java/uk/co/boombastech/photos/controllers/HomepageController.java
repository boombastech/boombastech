package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Parameter;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.solr.search.*;

import javax.inject.Inject;
import java.util.Collection;

public class HomepageController implements Controller {

	private final SolrService<Photo> solrService;
	private final Facets facets;

	@Inject
	public HomepageController(SolrService<Photo> solrService, Facets facets) {
		this.solrService = solrService;
		this.facets = facets;
	}

	@Override
	public void execute(Request request, Response response) {
		SearchCriteria<Photo> searchCriteria = new SearchCriteria<>();

		for (String parameter : request.getQueryParameters()) {
			if (facets.contains(parameter)) {
				for (String value : request.getQueryParameter(parameter)) {
					searchCriteria.withFacet(parameter, value);
				}
			}
		}
		SearchResult query = solrService.search(searchCriteria);

		response.withValue("test", query);
	}
}