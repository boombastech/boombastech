package uk.co.boombastech.solr.search;

import uk.co.boombastech.solr.search.facets.Facet;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class SearchResult<T extends Document> {

	private final Map<String, T> results;
	private final List<Facet> facets;
	private final long numberOfResults;
	private final Pagination<T> pagination;

	public SearchResult(List<T> results, List<Facet> facets, long numberOfResults, SearchCriteria<T> searchCriteria) {
		this.facets = facets;
		this.results = newHashMap();

		for (T result : results) {
			this.results.put(result.getId(), result);
		}

		this.numberOfResults = numberOfResults;
		this.pagination = new Pagination<T>(searchCriteria, numberOfResults);
	}

	public Map<String, T> getResults() {
		return results;
	}

	public long getNumberOfResults() {
		return numberOfResults;
	}

	public Pagination<T> getPagination() {
		return pagination;
	}
}