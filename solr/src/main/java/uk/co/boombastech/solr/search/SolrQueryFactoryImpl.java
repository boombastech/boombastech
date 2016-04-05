package uk.co.boombastech.solr.search;

import org.apache.solr.client.solrj.SolrQuery;
import uk.co.boombastech.solr.search.facets.AvailableFacets;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Singleton
public class SolrQueryFactoryImpl implements SolrQueryFactory {

	private static final String OR = " OR ";
	private static final String AND = " AND ";
	private static final String QUERY_ALL = "*:*";
	private static final String COLON = ":";
	private static final String OPENING_BRACKET = "(";
	private static final String CLOSING_BRACKET = ")";

	private final AvailableFacets availableFacets;

	@Inject
	public SolrQueryFactoryImpl(AvailableFacets availableFacets) {
		this.availableFacets = availableFacets;
	}

	@Override
	public <T> SolrQuery create(SearchCriteria<T> searchCriteria) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(createQueryString(searchCriteria));
		availableFacets.getFacets().forEach(solrQuery::addFacetField);
		solrQuery.setFacetMinCount(1);

		searchCriteria.getSortByField().ifPresent(sortField -> solrQuery.addSort(sortField, SolrQuery.ORDER.asc));

		solrQuery.setRows(searchCriteria.getNumberOfResults());
		solrQuery.setStart(searchCriteria.getNumberOfResults() * (searchCriteria.getPageNumber() - 1));

		availableFacets.getPivots().forEach(solrQuery::addFacetPivotField);

		return solrQuery;
	}

	private <T> String createQueryString(SearchCriteria<T> searchCriteria) {
		List<String> queries = newArrayList();

		for (String facetName : searchCriteria.getSearchCriteria().keySet()) {
			Collection<String> facetValues = searchCriteria.getSearchCriteria().get(facetName);
			String query = facetName + COLON + OPENING_BRACKET + String.join(OR, facetValues) + CLOSING_BRACKET;
			queries.add(query);
		}

		if (queries.isEmpty()) {
			return QUERY_ALL;
		} else {
			return String.join(AND, queries);
		}
	}
}