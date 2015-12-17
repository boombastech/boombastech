package uk.co.boombastech.web;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.routes.MutableRouteStore;

import java.util.EnumSet;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.emptyList;

public abstract class WebServer extends GuiceServletContextListener {

	private final Injector injector;

	public WebServer() throws Exception {
		MutableRouteStore mutableRouteStore = new MutableRouteStore();

		List<Module> modules = newArrayList();
		modules.add(new BasicWebModule());
		modules.addAll(modules(mutableRouteStore));
		modules.add(new RouteStoreModule(mutableRouteStore));

		injector = Guice.createInjector(modules);

		Server server = new Server(portNumber());

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath(baseContextPath());
		servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
		servletContextHandler.addEventListener(this);
		servletContextHandler.addServlet(DefaultServlet.class, "/");

		HandlerCollection handlerCollection = new HandlerCollection();
		handlerCollection.addHandler(servletContextHandler);
		additionalHandlers(newArrayList()).stream().forEach(handler -> handlerCollection.addHandler(handler));

		server.setHandler(handlerCollection);
		server.start();
		server.join();
	}

	public abstract List<Handler> additionalHandlers(List<Handler> handlers);

	public String baseContextPath() {
		return "/";
	}

	public abstract List<Module> modules(MutableRouteStore mutableRouteStore);

	public abstract int portNumber();

	@Override
	protected Injector getInjector() {
		return injector;
	}
}