package uk.co.boombastech.solr.search;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.solr.converters.SolrDocumentConverter;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class SolrServiceImpl<T> implements SolrService<T> {

	private static final String OR = " OR ";
	private static final String AND = " AND ";
	private static final String QUERY_ALL = "*:*";
	private static final String COLON = ":";
	private static final String OPENING_BRACKET = "(";
	private static final String CLOSING_BRACKET = ")";

	private final SolrClient solrClient;
	private final SolrDocumentConverter<T> converter;
	private final Facets facets;

	@Inject
	public SolrServiceImpl(SolrClient solrClient, SolrDocumentConverter<T> converter, Facets facets) {
		this.solrClient = solrClient;
		this.converter = converter;
		this.facets = facets;
	}

	@Override
	public SearchResult<T> search(SearchCriteria<T> searchCriteria) {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(createQueryString(searchCriteria));
		facets.forEach(facet -> solrQuery.addFacetField(facet.name()));

		searchCriteria.getSortByField().ifPresent(sortField -> solrQuery.addSort(sortField, SolrQuery.ORDER.asc));

		try {
			return createSearchResults(solrClient.query(solrQuery));
		} catch (SolrServerException | IOException e) {

			return new SearchResult<>(Collections.emptyList(), ArrayListMultimap.create(), 0);
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

	private SearchResult<T> createSearchResults(QueryResponse response) {
		List<T> results = newArrayList();

		for (SolrDocument solrDocument : response.getResults()) {
			converter.convertFrom(solrDocument).ifPresent(convertedDocument -> results.add(convertedDocument));
		}

		Multimap<String, FacetValue> facets = ArrayListMultimap.create();
		for (FacetField facetField : response.getFacetFields()) {
			String name = facetField.getName();
			for (Count facetFieldCount : facetField.getValues()) {
				String facetOptionName = facetFieldCount.getName();
				long facetOptionCount = facetFieldCount.getCount();

				facets.put(name, new FacetValue(facetOptionName, facetOptionCount));
			}
		}

		return new SearchResult<>(results, facets, response.getResults().getNumFound());
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