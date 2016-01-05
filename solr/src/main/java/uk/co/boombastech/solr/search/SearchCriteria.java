package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;

import java.util.Optional;

import static com.google.common.collect.ArrayListMultimap.create;

public class SearchCriteria<T> {

	private final Multimap<String, String> searchCriteria;
	private String sortByField;
	private int numberOfResults = 10; // default to 10 results
	private int pageNumber = 1; // default to first page

	public SearchCriteria() {
		searchCriteria = create();
	}

	public SearchCriteria<T> withFacet(String facet, String value) {
		searchCriteria.put(facet, value);
		return this;
	}

	public Multimap<String, String> getSearchCriteria() {
		return searchCriteria;
	}

	public Optional<String> getSortByField() {
		return Optional.ofNullable(sortByField);
	}

	public void setSortByField(String sortByField) {
		this.sortByField = sortByField;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}