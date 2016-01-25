package uk.co.boombastech.solr.search;

import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.ArrayListMultimap.create;
import static org.assertj.core.api.Assertions.assertThat;


public class SearchCriteriaPaginationBuilderTest {

    private SearchCriteriaPaginationBuilder builder;
    private Multimap<String, String> searchCriteriaMap = create();
    private String sortByField = "sortOrder";
    private int numberOfResults = 10;
    private int pageNumber = 1;

    @Test
    public void shouldHandleCaseWhereTotalNumberOfResultsIsLessThanResultsPerPage() throws Exception {
        builder = new SearchCriteriaPaginationBuilder();

        List<SearchCriteria> pagination = builder.from(new SearchCriteria(searchCriteriaMap, sortByField, numberOfResults, pageNumber))
                .withTotalResults(1)
                .build();

        assertThat(pagination).hasSize(1);

        SearchCriteria searchCriteria = pagination.get(0);
        assertThat(searchCriteria.getSearchCriteria()).isEqualTo(searchCriteriaMap);
        assertThat(searchCriteria.getSortByField().get()).isEqualTo(sortByField);
        assertThat(searchCriteria.getNumberOfResults()).isEqualTo(numberOfResults);
        assertThat(searchCriteria.getPageNumber()).isEqualTo(pageNumber);
    }

    @Test
    public void shouldHandleCaseWhereTotalNumberOfResultsIsMoreThanResultsPerPage() throws Exception {
        builder = new SearchCriteriaPaginationBuilder();

        List<SearchCriteria> pagination = builder.from(new SearchCriteria(searchCriteriaMap, sortByField, numberOfResults, pageNumber))
                .withTotalResults(73)
                .build();

        assertThat(pagination).hasSize(8);

        Iterator<SearchCriteria> iterator = pagination.iterator();
        int pageNumber = 1;
        while (iterator.hasNext()) {
            SearchCriteria searchCriteria = iterator.next();
            assertSearchCriteriaCorrect(searchCriteria, pageNumber);
            pageNumber++;
        }
    }

    private void assertSearchCriteriaCorrect(SearchCriteria searchCriteria, int pageNumber) {
        assertThat(searchCriteria.getSearchCriteria()).isEqualTo(searchCriteriaMap);
        assertThat(searchCriteria.getSortByField().get()).isEqualTo(sortByField);
        assertThat(searchCriteria.getNumberOfResults()).isEqualTo(numberOfResults);
        assertThat(searchCriteria.getPageNumber()).isEqualTo(pageNumber);
    }
}