package uk.co.boombastech.web;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.PathResource;
import org.eclipse.jetty.util.resource.Resource;
import uk.co.boombastech.routes.MutableRouteStore;

import javax.servlet.DispatcherType;
import java.io.File;
import java.util.EnumSet;
import java.util.List;

public abstract class WebServer extends GuiceServletContextListener {

	private final Injector injector;

	public WebServer() throws Exception {
		MutableRouteStore mutableRouteStore = new MutableRouteStore();

		List<Module> modules = Lists.newArrayList();
		modules.add(new BasicWebModule());
		modules.addAll(modules(mutableRouteStore));
		modules.add(new RouteStoreModule(mutableRouteStore));

		injector = Guice.createInjector(modules);

		Server server = new Server(portNumber());

		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/");
		servletContextHandler.addFilter(GuiceFilter.class, "*", EnumSet.allOf(DispatcherType.class));
		servletContextHandler.addEventListener(this);
		server.setHandler(servletContextHandler);

		String homePath = System.getProperty("user.home");

		ServletHolder holderHome = new ServletHolder("static-home", DefaultServlet.class);
		holderHome.setInitParameter("resourceBase", homePath);
		holderHome.setInitParameter("dirAllowed", "true");
		holderHome.setInitParameter("pathInfoOnly", "true");
		servletContextHandler.addServlet(holderHome, "/static/*");



		server.start();
		server.join();
	}

	public abstract List<Module> modules(MutableRouteStore mutableRouteStore);

	public abstract int portNumber();

	@Override
	protected Injector getInjector() {
		return injector;
	}
}