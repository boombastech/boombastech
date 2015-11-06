package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SearchResult<T> {

	private final List<T> results;
	private final Map<String, Collection<String>> facets;

	public SearchResult(List<T> results, Multimap<String, String> facets) {
		this.results = results;
		this.facets = facets.asMap();
	}
}