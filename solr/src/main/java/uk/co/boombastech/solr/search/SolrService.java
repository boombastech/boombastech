package uk.co.boombastech.solr.search;

public interface SolrService<T extends Document> {

	SearchResult<T> search(SearchCriteria<T> searchCriteria);

	void update(T object);

	void commit();
}