package uk.co.boombastech.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.controllers.HomepageController;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.injection.ActiveRouteProvider;
import uk.co.boombastech.injection.RequestProvider;
import uk.co.boombastech.injection.ResponseProvider;
import uk.co.boombastech.routes.ActiveRoute;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.routes.MyServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static uk.co.boombastech.routes.RouteBuilder.route;

public class WebServer extends GuiceServletContextListener {

	private final Injector injector;

	public WebServer() {
		injector = Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("*").with(MyServlet.class);

				bind(Request.class).toProvider(RequestProvider.class);
				bind(Response.class).toProvider(ResponseProvider.class);
				bind(ActiveRoute.class).toProvider(ActiveRouteProvider.class).in(RequestScoped.class);

				MutableRouteStore mutableRouteStore = new MutableRouteStore();
				mutableRouteStore.withRoute(route("/").withController(HomepageController.class));
				bind(MutableRouteStore.class).toInstance(mutableRouteStore);
			}
		});
	}

	@Override
	protected Injector getInjector() {
		return injector;
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		Server server = new Server(port);

		ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
		servletContextHandler.addFilter(GuiceFilter.class, "/", EnumSet.allOf(DispatcherType.class));
		servletContextHandler.addEventListener(new WebServer());

// You MUST add DefaultServlet or your server will always return 404s
		servletContextHandler.addServlet(DefaultServlet.class, "/");

// Start the server
		server.start();

// Wait until the server exits
		server.join();
	}
}