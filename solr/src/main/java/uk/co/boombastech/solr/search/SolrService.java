package uk.co.boombastech.solr.search;

public interface SolrService<T> {

	SearchResult<T> search(SearchCriteria<T> searchCriteria);

	void update(T object);

	void commit();
}