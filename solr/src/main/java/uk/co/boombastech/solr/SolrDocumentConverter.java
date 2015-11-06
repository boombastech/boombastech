package uk.co.boombastech.solr;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

public interface SolrDocumentConverter<T> {
	SolrInputDocument convertTo(T object);
	T convertFrom(SolrDocument solrDocument);
}