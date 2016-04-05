package uk.co.boombastech.solr.search;

import org.apache.solr.client.solrj.SolrClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import uk.co.boombastech.solr.converters.SolrDocumentConverter;
import uk.co.boombastech.solr.search.facets.AvailableFacets;

import static com.google.common.collect.Lists.newArrayList;

public class SolrServiceImplTest {

	private SolrServiceImpl solrService;

	private AvailableFacets availableFacets;

	@Mock
	private SolrClient solrClient;
	@Mock
	private SolrDocumentConverter solrDocumentConverter;
	private SolrQueryFactory solrQueryFactory;

	@Before
	public void setup() throws Exception {
		availableFacets = new AvailableFacets(newArrayList());

		solrService = new SolrServiceImpl(solrClient, solrQueryFactory, solrDocumentConverter, availableFacets);
	}

	@Test
	public void shouldName() throws Exception {
		SearchCriteria searchCriteria = new SearchCriteria();
		SearchResult searchResult = solrService.search(searchCriteria);


	}
}