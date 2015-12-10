package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Parameter;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.solr.search.SearchCriteria;
import uk.co.boombastech.solr.search.SearchResult;
import uk.co.boombastech.solr.search.SolrService;

import javax.inject.Inject;
import java.util.Collection;

public class HomepageController implements Controller {

	private final SolrService<Photo> solrService;

	@Inject
	public HomepageController(SolrService<Photo> solrService) {
		this.solrService = solrService;
	}

	@Override
	public void execute(Request request, Response response) {
		SearchCriteria<Photo> searchCriteria = new SearchCriteria<>();

		for (Parameter parameter : request.getQueryParameters()) {
			Collection<String> queryParameter = request.getQueryParameter(parameter);
			for (String value : queryParameter) {
				searchCriteria.withFacet(parameter.name(), value);
			}
		}
		SearchResult query = solrService.search(searchCriteria);

		response.withValue("test", query);
	}
}