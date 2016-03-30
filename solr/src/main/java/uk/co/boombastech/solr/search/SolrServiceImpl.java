package uk.co.boombastech.solr.search;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.solr.converters.SolrDocumentConverter;
import uk.co.boombastech.solr.search.facets.Facet;
import uk.co.boombastech.solr.search.facets.FacetValue;
import uk.co.boombastech.solr.search.facets.FacetValueBuilder;
import uk.co.boombastech.solr.search.facets.AvailableFacets;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

public class SolrServiceImpl<T extends Document> implements SolrService<T> {

	private static final String OR = " OR ";
	private static final String AND = " AND ";
	private static final String QUERY_ALL = "*:*";
	private static final String COLON = ":";
	private static final String OPENING_BRACKET = "(";
	private static final String CLOSING_BRACKET = ")";

	private final SolrClient solrClient;
	private final SolrDocumentConverter<T> converter;
	private final AvailableFacets availableFacets;

	@Inject
	public SolrServiceImpl(SolrClient solrClient, SolrDocumentConverter<T> converter, AvailableFacets availableFacets) {
		this.solrClient = solrClient;
		this.converter = converter;
		this.availableFacets = availableFacets;
	}

	@Override
	public SearchResult<T> search(SearchCriteria<T> searchCriteria) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(createQueryString(searchCriteria));
		availableFacets.getFacets().forEach(solrQuery::addFacetField);
		solrQuery.setFacetMinCount(1);

		searchCriteria.getSortByField().ifPresent(sortField -> solrQuery.addSort(sortField, ORDER.asc));

		solrQuery.setRows(searchCriteria.getNumberOfResults());
		solrQuery.setStart(searchCriteria.getNumberOfResults() * (searchCriteria.getPageNumber() -1));

		availableFacets.getPivots().forEach(solrQuery::addFacetPivotField);

		try {
			return createSearchResults(solrClient.query(solrQuery), searchCriteria);
		} catch (SolrServerException | IOException e) {
			return new SearchResult<>(emptyList(), emptyList(), 0, searchCriteria);
		}
	}

	@Override
	public void update(T object) {
		SolrInputDocument solrInputFields = converter.convertTo(object);
		try {
			solrClient.add(solrInputFields);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commit() {
		try {
			solrClient.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}

	private SearchResult<T> createSearchResults(QueryResponse response, SearchCriteria<T> searchCriteria) {
		List<T> results = newArrayList();

		for (SolrDocument solrDocument : response.getResults()) {
			converter.convertFrom(solrDocument).ifPresent(convertedDocument -> results.add(convertedDocument));
		}

		List<Facet> facets = newArrayList();

		for (FacetField facetField : response.getFacetFields()) {
			List<FacetValue> facetValues = newArrayList();
			String name = facetField.getName();
			for (Count facetFieldCount : facetField.getValues()) {
				FacetValue facetValue = new FacetValueBuilder()
						.withFacetOptionName(facetFieldCount.getName())
						.withFacetOptionCount(facetFieldCount.getCount())
						.withSelected(searchCriteria.getSearchCriteria().containsKey(name) && searchCriteria.getSearchCriteria().get(name).contains(facetFieldCount.getName()))
						.build();
				facetValues.add(facetValue);
			}
			facets.add(new Facet(name, facetValues));
		}

		for (Map.Entry<String, List<PivotField>> facetPivot : response.getFacetPivot()) {
			String facetName = facetPivot.getKey();
			List<FacetValue> facetValues = newArrayList();
			for (PivotField pivotField : facetPivot.getValue()) {
				facetValues.add(createFacetValue(pivotField));
			}

			facets.add(new Facet(facetName, facetValues));
		}

		SearchResult<T> tSearchResult = new SearchResult<>(results, facets, response.getResults().getNumFound(), searchCriteria);
		return tSearchResult;
	}

	private FacetValue createFacetValue(PivotField pivotField) {
		FacetValueBuilder facetValueBuilder = new FacetValueBuilder()
				.withFacetOptionName(String.valueOf(pivotField.getValue()))
				.withFacetOptionCount(pivotField.getCount());

		if (pivotField.getPivot() != null) {
			pivotField.getPivot().stream().forEach(pivotFieldChild -> facetValueBuilder.withPivotFacetValue(createFacetValue(pivotFieldChild)));
		}

		return facetValueBuilder.build();
	}

	private String createQueryString(SearchCriteria<T> searchCriteria) {
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