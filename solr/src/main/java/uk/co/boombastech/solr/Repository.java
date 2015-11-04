package uk.co.boombastech.solr;

public interface Repository<T> {

	void create(T object);

	void commit();
}