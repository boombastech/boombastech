package uk.co.boombastech.photos.injection;

import com.google.common.collect.Lists;
import com.google.inject.TypeLiteral;
import uk.co.boombastech.photos.models.Photo;
import uk.co.boombastech.photos.controllers.DeleteAllController;
import uk.co.boombastech.photos.controllers.HomepageController;
import uk.co.boombastech.photos.importer.ImporterController;
import uk.co.boombastech.photos.importer.ImporterService;
import uk.co.boombastech.photos.importer.PhotoIndexer;
import uk.co.boombastech.photos.search.PhotoFacets;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.routes.RouteBuilder;
import uk.co.boombastech.solr.search.Facets;
import uk.co.boombastech.solr.search.SolrService;
import uk.co.boombastech.web.WebModule;

public class PhotosModule extends WebModule {

	public PhotosModule(MutableRouteStore mutableRouteStore) {
		super(mutableRouteStore);
	}

	@Override
	protected void configure() {
		mutableRouteStore.withRoute(RouteBuilder.route("/").withController(HomepageController.class));
		mutableRouteStore.withRoute(RouteBuilder.route("/import").withController(ImporterController.class));
		mutableRouteStore.withRoute(RouteBuilder.route("/delete-all").withController(DeleteAllController.class));

		bind(new TypeLiteral<SolrService<Photo>>() {}).toProvider(PhotoSolrServiceProvider.class);

		bind(ImporterService.class).asEagerSingleton();
		bind(PhotoIndexer.class);
		bind(Facets.class).toInstance(new Facets(Lists.newArrayList(PhotoFacets.values())));
	}
}