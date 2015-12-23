package uk.co.boombastech.photos.injection;

import com.google.common.collect.Lists;
import com.google.inject.TypeLiteral;
import uk.co.boombastech.photos.controllers.DeleteAllController;
import uk.co.boombastech.photos.controllers.HomepageController;
import uk.co.boombastech.photos.importer.ImporterController;
import uk.co.boombastech.photos.importer.ImporterService;
import uk.co.boombastech.photos.importer.PhotoIndexer;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.search.PhotoFacets;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.solr.search.Facets;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.web.WebModule;

import static uk.co.boombastech.routes.RouteBuilder.route;

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
		mutableRouteStore.withRoute(route("/rest").withController(HomepageController.class));
		mutableRouteStore.withRoute(route("/import").withController(ImporterController.class));
		mutableRouteStore.withRoute(route("/delete-all").withController(DeleteAllController.class));
	}
}