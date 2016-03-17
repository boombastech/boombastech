package uk.co.boombastech.photos.search;

import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.solr.search.SearchCriteria;
import uk.co.boombastech.solr.search.SearchResult;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.solr.search.facets.FacetView;

import javax.inject.Inject;

public class FacetListController implements Controller {

	private final SolrService<Photo> solrService;

	@Inject
	public FacetListController(SolrService<Photo> solrService) {
		this.solrService = solrService;
	}

	@Override
	public void execute(Request request, Response response) {
		SearchCriteria searchCriteria = new SearchCriteria<Photo>();
		SearchResult searchResult = solrService.search(searchCriteria);
		response.withValue(new FacetView(searchResult));
	}
}