package uk.co.boombastech.solr.search;

import java.util.List;

public class Pagination<T> {
    private final List<SearchCriteria<T>> pagination;


    public Pagination(SearchCriteria<T> currentPage, long totalNumberResults) {
        pagination = new SearchCriteriaPaginationBuilder<T>()
                .from(currentPage)
                .withTotalResults(totalNumberResults)
                .build();
    }
}