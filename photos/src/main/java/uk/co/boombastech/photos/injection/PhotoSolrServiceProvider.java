package uk.co.boombastech.photos.injection;

import org.apache.solr.client.solrj.SolrClient;
import uk.co.boombastech.photos.importer.PhotoSolrDocumentConverter;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.solr.search.SolrQueryFactory;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.solr.search.SolrServiceImpl;
import uk.co.boombastech.solr.search.facets.AvailableFacets;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class PhotoSolrServiceProvider implements Provider<SolrService<Photo>> {

	private final SolrService solrService;

	@Inject
	public PhotoSolrServiceProvider(SolrClient solrClient, PhotoSolrDocumentConverter converter, AvailableFacets availableFacets, SolrQueryFactory solrQueryFactory) {
		solrService = new SolrServiceImpl(solrClient, solrQueryFactory, converter, availableFacets);
	}

	@Override
	public SolrService<Photo> get() {
		return solrService;
	}
}