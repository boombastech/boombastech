package uk.co.boombastech.photos.injection;

import com.google.common.collect.Lists;
import com.google.inject.TypeLiteral;
import uk.co.boombastech.config.ApplicationConfig;
import uk.co.boombastech.photos.config.PhotoWebConfig;
import uk.co.boombastech.photos.controllers.DeleteAllController;
import uk.co.boombastech.photos.controllers.SearchController;
import uk.co.boombastech.photos.controllers.UpdateController;
import uk.co.boombastech.photos.importer.ImporterController;
import uk.co.boombastech.photos.importer.ImporterService;
import uk.co.boombastech.photos.importer.PhotoIndexer;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.search.PhotoFacets;
import uk.co.boombastech.http.routes.MutableRouteStore;
import uk.co.boombastech.solr.search.Facets;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.http.injection.WebModule;

import static uk.co.boombastech.http.routes.RouteBuilder.route;

public class PhotosModule extends WebModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<SolrService<Photo>>() {}).toProvider(PhotoSolrServiceProvider.class);

		bind(ImporterService.class).asEagerSingleton();
		bind(PhotoIndexer.class);
		bind(Facets.class).toInstance(new Facets(Lists.newArrayList(PhotoFacets.values())));
	}

	@Override
	public void configureRoutes(MutableRouteStore mutableRouteStore) {
		mutableRouteStore.addRoute("/search").withControllers(SearchController.class);
		mutableRouteStore.addRoute("/update").withControllers(UpdateController.class);
		mutableRouteStore.addRoute("/import").withControllers(ImporterController.class);
		mutableRouteStore.addRoute("/delete-all").withControllers(DeleteAllController.class);
	}
}