package uk.co.boombastech.photos.injection;

import com.google.common.collect.Lists;
import org.apache.solr.client.solrj.SolrClient;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.photos.importer.PhotoSolrDocumentConverter;
import uk.co.boombastech.photos.search.Facet;
import uk.co.boombastech.solr.search.Facets;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.solr.search.SolrServiceImpl;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class PhotoSolrService implements Provider<SolrService<Photo>> {

	private final SolrServiceImpl solrService;

	@Inject
	public PhotoSolrService(SolrClient solrClient, PhotoSolrDocumentConverter converter) {
		Facets facets = new Facets(Lists.newArrayList(Facet.values()));
		this.solrService = new SolrServiceImpl(solrClient, converter, facets);
	}

	@Override
	public SolrService<Photo> get() {
		return solrService;
	}
}