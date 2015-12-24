package uk.co.boombastech.photos.controllers;

import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.solr.search.*;

import javax.inject.Inject;

public class SearchController implements Controller {

	private static final String SORT = "sort";
	private final SolrService<Photo> solrService;
	private final Facets facets;

	@Inject
	public SearchController(SolrService<Photo> solrService, Facets facets) {
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

		if (!request.getQueryParameter(SORT).isEmpty()) {
			searchCriteria.setSortByField(request.getQueryParameter("sort").iterator().next());
		}

		SearchResult query = solrService.search(searchCriteria);

		response.withValue("test", query);
	}
}