package uk.co.boombastech.solr;

import org.apache.solr.common.SolrInputDocument;

public interface SolrDocumentConverter<T> {
	SolrInputDocument convertTo(T object);
}