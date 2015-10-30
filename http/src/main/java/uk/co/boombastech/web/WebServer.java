package uk.co.boombastech.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.controllers.AnotherController;
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

				bind(Request.class).toProvider(RequestProvider.class).in(ServletScopes.REQUEST);
				bind(Response.class).toProvider(ResponseProvider.class).in(ServletScopes.REQUEST);
				bind(ActiveRoute.class).toProvider(ActiveRouteProvider.class).in(ServletScopes.REQUEST);

				MutableRouteStore mutableRouteStore = new MutableRouteStore();
				mutableRouteStore.withRoute(route("/").withController(HomepageController.class));
				mutableRouteStore.withRoute(route("/more").withController(AnotherController.class));
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
		servletContextHandler.addFilter(GuiceFilter.class, "*", EnumSet.allOf(DispatcherType.class));
		servletContextHandler.addEventListener(new WebServer());

		servletContextHandler.addServlet(DefaultServlet.class, "/");
		server.start();
		server.join();
	}
}