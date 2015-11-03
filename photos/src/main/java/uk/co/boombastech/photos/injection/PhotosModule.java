package uk.co.boombastech.photos.injection;

import com.google.inject.TypeLiteral;
import uk.co.boombastech.photos.Photo;
import uk.co.boombastech.photos.controllers.HomepageController;
import uk.co.boombastech.photos.importer.ImporterController;
import uk.co.boombastech.photos.importer.ImporterService;
import uk.co.boombastech.photos.importer.PhotoSolrDocumentConverter;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.routes.RouteBuilder;
import uk.co.boombastech.solr.GenericRepository;
import uk.co.boombastech.solr.Repository;
import uk.co.boombastech.solr.SolrDocumentConverter;
import uk.co.boombastech.web.WebModule;

public class PhotosModule extends WebModule {

	public PhotosModule(MutableRouteStore mutableRouteStore) {
		super(mutableRouteStore);
	}

	@Override
	protected void configure() {
		mutableRouteStore.withRoute(RouteBuilder.route("/").withController(HomepageController.class));
		mutableRouteStore.withRoute(RouteBuilder.route("/import").withController(ImporterController.class));

		bind(new TypeLiteral<Repository<Photo>>(){}).to(new TypeLiteral<GenericRepository<Photo>>() {
		});
		bind(new TypeLiteral<SolrDocumentConverter<Photo>>(){}).to(new TypeLiteral<PhotoSolrDocumentConverter>() {
		});

		bind(ImporterService.class).asEagerSingleton();
	}
}