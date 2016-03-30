package uk.co.boombastech.photos.injection;

import com.google.common.collect.Lists;
import com.google.inject.TypeLiteral;
import uk.co.boombastech.photos.search.FacetListController;
import uk.co.boombastech.photos.controllers.DeleteAllController;
import uk.co.boombastech.photos.search.SearchController;
import uk.co.boombastech.photos.controllers.UpdateController;
import uk.co.boombastech.photos.importer.ImporterController;
import uk.co.boombastech.photos.importer.ImporterService;
import uk.co.boombastech.photos.importer.PhotoIndexer;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.search.PhotoFacet;
import uk.co.boombastech.http.routes.MutableRouteStore;
import uk.co.boombastech.solr.search.facets.AvailableFacets;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.http.injection.WebModule;

public class PhotosModule extends WebModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<SolrService<Photo>>() {}).toProvider(PhotoSolrServiceProvider.class);

		bind(ImporterService.class).asEagerSingleton();
		bind(PhotoIndexer.class);
		bind(AvailableFacets.class).toInstance(new AvailableFacets(Lists.newArrayList(PhotoFacet.values())));
	}

	@Override
	public void configureRoutes(MutableRouteStore mutableRouteStore) {
		mutableRouteStore.addRoute("/search").withControllers(SearchController.class);
		mutableRouteStore.addRoute("/update").withControllers(UpdateController.class);
		mutableRouteStore.addRoute("/import").withControllers(ImporterController.class);
		mutableRouteStore.addRoute("/facets").withControllers(FacetListController.class);
		mutableRouteStore.addRoute("/delete-all").withControllers(DeleteAllController.class);
	}
}