package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SearchResult<T> {

	private final List<T> results;
	private final Map<String, Collection<FacetValue>> facets;
	private final long numberOfResults;
	private final Pagination pagination;

	public SearchResult(List<T> results, Multimap<String, FacetValue> facets, long numberOfResults, Pagination pagination) {
		this.results = results;
		this.facets = facets.asMap();
		this.numberOfResults = numberOfResults;
		this.pagination = pagination;
	}

	public List<T> getResults() {
		return results;
	}

	public Map<String, Collection<FacetValue>> getFacets() {
		return facets;
	}

	public long getNumberOfResults() {
		return numberOfResults;
	}

	public Pagination getPagination() {
		return pagination;
	}
}