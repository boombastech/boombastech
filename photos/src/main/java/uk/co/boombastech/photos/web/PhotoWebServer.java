package uk.co.boombastech.photos.web;

import com.google.inject.Module;
import org.eclipse.jetty.server.Handler;
import uk.co.boombastech.handlers.HandlerBuilder;
import uk.co.boombastech.photos.injection.PhotosModule;
import uk.co.boombastech.properties.WebServerBuilder;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.solr.injection.SolrModule;
import uk.co.boombastech.web.WebServer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class PhotoWebServer extends WebServer {

	public PhotoWebServer() throws Exception {}

	@Override
	public List<Module> modules(MutableRouteStore mutableRouteStore) {
		return newArrayList(new SolrModule(), new PhotosModule());
	}

	@Override
	public int portNumber() {
		return 8080;
	}

	@Override
	public String baseContextPath() {
		return "/api";
	}

	@Override
	public List<Handler> additionalHandlers(List<Handler> handlers) {
		handlers.add(new HandlerBuilder().withContextPath("/").withResourceBase("./photos/src/main/web/html/").build());
		handlers.add(new HandlerBuilder().withContextPath("/photos").withResourceBase("C:/").withShowDirectory(true).build());
		handlers.add(new HandlerBuilder().withContextPath("/static/css").withResourceBase("./photos/src/main/web/css/").build());
		handlers.add(new HandlerBuilder().withContextPath("/static/js").withResourceBase("./photos/src/main/web/javascript/").build());

		return handlers;
	}

	public static void main(String[] args) throws Exception {
		new WebServerBuilder()
				.withPortNumber(8080)
				.withModule(new SolrModule())
				.withModule(new PhotosModule())
				.withContextPath("/api")
				.withHandler(new HandlerBuilder().withContextPath("/").withResourceBase("./photos/src/main/web/html/").build())
				.withHandler(new HandlerBuilder().withContextPath("/photos").withResourceBase("C:/").withShowDirectory(true).build())
				.withHandler(new HandlerBuilder().withContextPath("/static/css").withResourceBase("./photos/src/main/web/css/").build())
				.withHandler(new HandlerBuilder().withContextPath("/static/js").withResourceBase("./photos/src/main/web/javascript/").build())
				.build();
//		new PhotoWebServer();
	}
}