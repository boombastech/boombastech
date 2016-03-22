package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;
import uk.co.boombastech.solr.search.facets.FacetValue;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class SearchResult<T extends Document> {

	private final Map<String, T> results;
	private final Map<String, Collection<FacetValue>> facets;
	private final long numberOfResults;
	private final Pagination<T> pagination;

	public SearchResult(List<T> results, Multimap<String, FacetValue> facets, long numberOfResults, SearchCriteria<T> searchCriteria) {
		this.results = newHashMap();

		for (T result : results) {
			this.results.put(result.getId(), result);
		}

		this.facets = facets.asMap();
		this.numberOfResults = numberOfResults;
		this.pagination = new Pagination<T>(searchCriteria, numberOfResults);
	}

	public Map<String, T> getResults() {
		return results;
	}

	public Map<String, Collection<FacetValue>> getFacets() {
		return facets;
	}

	public long getNumberOfResults() {
		return numberOfResults;
	}

	public Pagination<T> getPagination() {
		return pagination;
	}
}