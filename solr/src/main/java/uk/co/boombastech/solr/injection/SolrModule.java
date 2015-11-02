package uk.co.boombastech.solr.injection;

import com.google.inject.AbstractModule;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SolrClient.class).toInstance(new HttpSolrClient("http://localhost:8983/solr/ia"));
	}
}