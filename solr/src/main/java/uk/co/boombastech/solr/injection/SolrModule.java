package uk.co.boombastech.solr.injection;

import com.google.inject.AbstractModule;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import uk.co.boombastech.solr.search.SolrQueryFactory;
import uk.co.boombastech.solr.search.SolrQueryFactoryImpl;

public class SolrModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SolrClient.class).toInstance(new HttpSolrClient("http://localhost:8983/solr/photos"));
		bind(SolrQueryFactory.class).to(SolrQueryFactoryImpl.class);
	}
}