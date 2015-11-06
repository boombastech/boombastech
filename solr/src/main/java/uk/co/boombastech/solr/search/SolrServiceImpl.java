package uk.co.boombastech.solr.search;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import uk.co.boombastech.solr.SolrDocumentConverter;

import java.io.IOException;
import java.util.List;

public class SolrServiceImpl<T> implements SolrService<T> {

	private final SolrClient solrClient;
	private final SolrDocumentConverter<T> converter;
	private final Facets facets;

	public SolrServiceImpl(SolrClient solrClient, SolrDocumentConverter<T> converter, Facets facets) {
		this.solrClient = solrClient;
		this.converter = converter;
		this.facets = facets;
	}

	@Override
	public SearchResult<T> search(SearchCriteria<T> searchCriteria) {
		SolrQuery solrQuery = new SolrQuery();

		for (Facet facet : searchCriteria.getSearchCriteria().keySet()) {
			for (String value : searchCriteria.getSearchCriteria().get(facet)) {
				solrQuery.set(facet.name(), value);
			}
		}

		solrQuery.setQuery("*:*");
		solrQuery.setFacet(true);

		for (Facet facet : facets) {
			solrQuery.addFacetField(facet.name());
		}

		solrQuery.addFacetField("category", "album");

		try {
			QueryResponse response = solrClient.query(solrQuery);
			List<T> results = Lists.newArrayList();

			for (SolrDocument solrDocument : response.getResults()) {
				results.add(converter.convertFrom(solrDocument));
			}

			Multimap<String, String> facets = ArrayListMultimap.create();
			for (FacetField facetField : response.getFacetFields()) {
				String name = facetField.getName();
				for (FacetField.Count count : facetField.getValues()) {
					String name1 = count.getName();
					long count1 = count.getCount();

					facets.put(name, name1 + ":" + count1);
				}
			}


			return new SearchResult<T>(results, facets);
		} catch (SolrServerException | IOException e) {
			return null;
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
}