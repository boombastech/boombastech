package uk.co.boombastech.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;

import javax.inject.Inject;
import java.io.IOException;

public class GenericRepository<T> implements Repository<T> {

	private final SolrClient solrClient;
	private final SolrDocumentConverter<T> solrDocumentConverter;

	@Inject
	public GenericRepository(SolrClient solrClient, SolrDocumentConverter<T> solrDocumentConverter) {
		this.solrClient = solrClient;
		this.solrDocumentConverter = solrDocumentConverter;
	}

	@Override
	public void create(T object) {
		SolrInputDocument solrInputDocument = solrDocumentConverter.convertTo(object);
		try {
			solrClient.add(solrInputDocument);
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
	}
}