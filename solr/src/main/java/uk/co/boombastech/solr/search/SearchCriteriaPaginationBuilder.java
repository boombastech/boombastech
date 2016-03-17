package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;
import uk.co.boombastech.utils.Builder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class SearchCriteriaPaginationBuilder<T> implements Builder<Map<Integer, Page<T>>> {

    private Multimap<String, String> searchCriteriaMap;
    private String sortByField;
    private int numberOfResults;
    private long totalResults;
    private int currentPageNumber;

    public SearchCriteriaPaginationBuilder<T> from(SearchCriteria<T> searchCriteria) {
        searchCriteriaMap = searchCriteria.getSearchCriteria();
        searchCriteria.getSortByField().ifPresent(sortField -> sortByField = sortField);
        currentPageNumber = searchCriteria.getPageNumber();
        numberOfResults = searchCriteria.getNumberOfResults();
        return this;
    }

    public SearchCriteriaPaginationBuilder<T> withTotalResults(long totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    @Override
    public Map<Integer, Page<T>> build() {
        Map<Integer, Page<T>> searchCriteria = newHashMap();
        long numberOfPages = new BigDecimal(totalResults).divide(new BigDecimal(numberOfResults), RoundingMode.UP).longValue();

        for (int i = 1; i <= numberOfPages; i++) {
                searchCriteria.put(i, new Page(i, new SearchCriteria<>(searchCriteriaMap, sortByField, numberOfResults, i), i==currentPageNumber));
        }

        return searchCriteria;
    }
}