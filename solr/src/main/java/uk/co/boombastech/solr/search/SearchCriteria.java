package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;
import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.ArrayListMultimap.create;
import static com.google.common.collect.Lists.newArrayList;

public class SearchCriteria<T> {

	private static final int DEFAULT_NUMBER_OF_RESULTS = 10;
	private static final int DEFAULT_PAGE_NUMBER = 1;

	private final Multimap<String, String> searchCriteria;
	private String sortByField;
	private int numberOfResults = DEFAULT_NUMBER_OF_RESULTS;
	private int pageNumber = DEFAULT_PAGE_NUMBER;

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

	public String createUrlFromSearchCriteriaForPageNumber(int pageNumber) {
		List<String> queryParameters = newArrayList();

		if (!searchCriteria.isEmpty()) {
			for (String facet : searchCriteria.keySet()) {
				for (String value : searchCriteria.get(facet)) {
					queryParameters.add(facet + "=" + value);
				}
			}
		}

		getSortByField().ifPresent(sortField -> queryParameters.add("sort=" + sortField));

		if (numberOfResults != DEFAULT_NUMBER_OF_RESULTS) {
			queryParameters.add("size=" + numberOfResults);
		}

		queryParameters.add("page=" + pageNumber);

		if (!queryParameters.isEmpty()) {
			return "?" + StringUtils.join(queryParameters, "&");
		}

		return "";
	}
}