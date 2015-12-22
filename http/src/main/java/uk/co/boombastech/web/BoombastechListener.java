package uk.co.boombastech.web;

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
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.properties.ServletContextHandlerConfig;
import uk.co.boombastech.routes.MutableRouteStore;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class BoombastechListener extends GuiceServletContextListener {

	private final Injector injector;

	public BoombastechListener() throws Exception {
		MutableRouteStore mutableRouteStore = new MutableRouteStore();

		List<Module> modules = newArrayList();
		modules.add(new BasicWebModule());
		modules.addAll(modules(mutableRouteStore));
		modules.add(new RouteStoreModule(mutableRouteStore));

		injector = Guice.createInjector(modules);
	}

	public abstract List<Handler> additionalHandlers(List<Handler> handlers);

	public abstract List<Module> modules(MutableRouteStore mutableRouteStore);

	@Override
	protected Injector getInjector() {
		return injector;
	}
}