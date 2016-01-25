package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;
import org.apache.solr.common.StringUtils;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ArrayListMultimap.create;
import static com.google.common.collect.Lists.newArrayList;

public class SearchCriteria<T> implements Cloneable {

	private static final int DEFAULT_NUMBER_OF_RESULTS = 10;
	private static final int DEFAULT_PAGE_NUMBER = 1;

	private final Multimap<String, String> searchCriteria;
	private String sortByField;
	private int numberOfResults = DEFAULT_NUMBER_OF_RESULTS;
	private int pageNumber = DEFAULT_PAGE_NUMBER;

	public SearchCriteria() {
		searchCriteria = create();
	}

	public SearchCriteria(Multimap<String, String> searchCriteria, String sortByField, int numberOfResults, int pageNumber) {
		this.searchCriteria = searchCriteria;
		this.sortByField = sortByField;
		this.numberOfResults = numberOfResults;
		this.pageNumber = pageNumber;
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