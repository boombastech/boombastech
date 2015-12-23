package uk.co.boombastech.photos.web;

import uk.co.boombastech.photos.injection.PhotosModule;
import uk.co.boombastech.solr.injection.SolrModule;
import uk.co.boombastech.web.WebServer;
import uk.co.boombastech.web.WebServerBuilder;

import static uk.co.boombastech.handlers.ResourceHandlerBuilder.resourceHandler;
import static uk.co.boombastech.handlers.ServletContextHandlerBuilder.servletContextHandler;
import static uk.co.boombastech.web.ListenerBuilder.listener;

public class PhotoWebApp {
	public static void main(String[] args) throws Exception {
		WebServer webServer = new WebServerBuilder()
				.withPortNumber(8080)
				.withHandler(servletContextHandler()
						.withContextPath("/api")
						.withEventListener(listener()
								.withModule(new SolrModule())
								.withModule(new PhotosModule())))
				.withHandler(resourceHandler()
						.withContextPath("/")
						.withResourceBase("./photos/src/main/web"))
				.withHandler(resourceHandler()
						.withContextPath("/photos")
						.withShowDirectory(true)
						.withResourceBase("C:/"))
				.build();

		webServer.start();
	}
}