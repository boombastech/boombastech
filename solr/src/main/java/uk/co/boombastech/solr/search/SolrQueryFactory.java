package uk.co.boombastech.solr.search;

import org.apache.solr.client.solrj.SolrQuery;

public interface SolrQueryFactory {
	<T> SolrQuery create(SearchCriteria<T> searchCriteria);
}