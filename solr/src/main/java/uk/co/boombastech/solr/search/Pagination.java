package uk.co.boombastech.solr.search;

import java.util.Map;

public class Pagination<T> {
    private final Page previous;
    private final Page next;
    private final Map<Integer, Page<T>> pagination;


    public Pagination(SearchCriteria<T> currentPage, long totalNumberResults) {
        pagination = new SearchCriteriaPaginationBuilder<T>()
                .from(currentPage)
                .withTotalResults(totalNumberResults)
                .build();

        int currentPageNumber = currentPage.getPageNumber();

        if (currentPageNumber > 1) {
            previous = pagination.get(currentPageNumber-1);
        } else {
            previous = null;
        }
        if (currentPageNumber < pagination.size()) {
            next = pagination.get(currentPageNumber+1);
        } else {
            next = null;
        }
    }

    public Page getPrevious() {
        return previous;
    }

    public Page getNext() {
        return next;
    }

    public Map<Integer, Page<T>> getPagination() {
        return pagination;
    }
}