package uk.co.boombastech.solr.converters;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.util.Optional;

public interface SolrDocumentConverter<T> {
	SolrInputDocument convertTo(T object);
	Optional<T> convertFrom(SolrDocument solrDocument);
}