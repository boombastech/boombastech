package uk.co.boombastech.solr.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.PivotField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.solr.converters.SolrDocumentConverter;
import uk.co.boombastech.solr.search.facets.AvailableFacets;
import uk.co.boombastech.solr.search.facets.Facet;
import uk.co.boombastech.solr.search.facets.FacetValue;
import uk.co.boombastech.solr.search.facets.FacetValueBuilder;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

public class SolrServiceImpl<T extends Document> implements SolrService<T> {

	private final SolrClient solrClient;
	private final SolrQueryFactory solrQueryFactory;
	private final SolrDocumentConverter<T> converter;

	@Inject
	public SolrServiceImpl(SolrClient solrClient, SolrQueryFactory solrQueryFactory, SolrDocumentConverter<T> converter, AvailableFacets availableFacets) {
		this.solrClient = solrClient;
		this.solrQueryFactory = solrQueryFactory;
		this.converter = converter;
	}

	@Override
	public SearchResult<T> search(SearchCriteria<T> searchCriteria) {
		SolrQuery solrQuery = solrQueryFactory.create(searchCriteria);

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
			facetValues.addAll(facetPivot.getValue().stream().map(this::createFacetValue).collect(Collectors.toList()));

			facets.add(new Facet(facetName, facetValues));
		}

		return new SearchResult<>(results, facets, response.getResults().getNumFound(), searchCriteria);
	}

	private FacetValue createFacetValue(PivotField pivotField) {
		String name = String.valueOf(pivotField.getValue());
		FacetValueBuilder facetValueBuilder = new FacetValueBuilder()
				.withFacetOptionName(name)
				.withFacetOptionCount(pivotField.getCount());

		if (pivotField.getPivot() != null) {
			pivotField.getPivot().stream().forEach(pivotFieldChild -> facetValueBuilder.withPivotFacetValue(createFacetValue(pivotFieldChild)));
		}

		return facetValueBuilder.build();
	}
}