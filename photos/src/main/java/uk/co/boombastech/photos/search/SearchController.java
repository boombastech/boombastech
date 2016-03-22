package uk.co.boombastech.photos.search;

import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.solr.search.*;
import uk.co.boombastech.solr.search.facets.Facets;
import uk.co.boombastech.utils.NumberUtils;

import javax.inject.Inject;
import javax.swing.text.NumberFormatter;
import java.util.Optional;

public class SearchController implements Controller {

	private static final String SORT = "sort";
	private static final String SIZE = "size";
	private static final String PAGE = "page";
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
		searchCriteria.setNumberOfResults(2);

		for (String parameter : request.getQueryParameters()) {
			if (facets.contains(parameter)) {
				request.getQueryParameter(parameter).stream().forEach(value -> searchCriteria.withFacet(parameter, value));
			}
		}

		if (!request.getQueryParameter(SORT).isEmpty()) {
			searchCriteria.setSortByField(request.getQueryParameter(SORT).iterator().next());
		}

		if (!request.getQueryParameter(SIZE).isEmpty()) {
			Optional<Integer> integerOptional = NumberUtils.parseInteger(request.getQueryParameter(SIZE).iterator().next());
			integerOptional.ifPresent(integer -> searchCriteria.setNumberOfResults(integer));
		}

		if (!request.getQueryParameter(PAGE).isEmpty()) {
			Optional<Integer> integerOptional = NumberUtils.parseInteger(request.getQueryParameter(PAGE).iterator().next());
			integerOptional.ifPresent(integer -> searchCriteria.setPageNumber(integer));
		}

		SearchResult searchResult = solrService.search(searchCriteria);

		response.withValue(searchResult);
	}
}