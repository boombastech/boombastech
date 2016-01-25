package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;
import uk.co.boombastech.utils.Builder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SearchCriteriaPaginationBuilder<T> implements Builder<List<SearchCriteria<T>>> {

    private Multimap<String, String> searchCriteriaMap;
    private String sortByField;
    private int numberOfResults;
    private long totalResults;

    public SearchCriteriaPaginationBuilder<T> from(SearchCriteria<T> searchCriteria) {
        searchCriteriaMap = searchCriteria.getSearchCriteria();
        searchCriteria.getSortByField().ifPresent(sortField -> sortByField = sortField);
        numberOfResults = searchCriteria.getNumberOfResults();
        return this;
    }

    public SearchCriteriaPaginationBuilder<T> withTotalResults(long totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    @Override
    public List<SearchCriteria<T>> build() {
        List<SearchCriteria<T>> searchCriterias = newArrayList();

        long numberOfPages = new BigDecimal(totalResults).divide(new BigDecimal(numberOfResults), RoundingMode.UP).longValue();

        for (int i = 1; i <= numberOfPages; i++) {
            searchCriterias.add(new SearchCriteria<>(searchCriteriaMap, sortByField, numberOfResults, i));
        }

        return searchCriterias;
    }
}