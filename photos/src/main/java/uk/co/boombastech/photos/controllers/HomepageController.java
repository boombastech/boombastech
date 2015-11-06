package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Parameter;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.solr.search.Facet;
import uk.co.boombastech.solr.search.SearchCriteria;
import uk.co.boombastech.solr.search.SearchResult;
import uk.co.boombastech.solr.search.SolrService;

import javax.inject.Inject;

public class HomepageController implements Controller {

	private final SolrService<Photo> solrService;

	@Inject
	public HomepageController(SolrService<Photo> solrService) {
		this.solrService = solrService;
	}

	@Override
	public void execute(Request request, Response response) {

		for (Parameter parameter : request.getQueryParameters()) {
			request.getQueryParameter(parameter);
		}


		SearchResult query = solrService.search(new SearchCriteria<Photo>().withFacet(new Facet() {
			@Override
			public String name() {
				return "category";
			}
		}, "holiday"));
		response.withValue("test", query);
	}
}