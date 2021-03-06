package uk.co.boombastech.http.injection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import uk.co.boombastech.http.injection.WebModule;
import uk.co.boombastech.http.routes.MutableRouteStore;
import uk.co.boombastech.http.web.BasicWebModule;
import uk.co.boombastech.http.web.RouteStoreModule;

import java.util.List;

public class Listener extends GuiceServletContextListener {

	private final Injector injector;

	public Listener(List<Module> modules) throws Exception {
		MutableRouteStore mutableRouteStore = new MutableRouteStore();

		for (Module module : modules) {
			if (module instanceof WebModule) {
				((WebModule) module).configureRoutes(mutableRouteStore);
			}
		}

		modules.add(new BasicWebModule());
		modules.add(new RouteStoreModule(mutableRouteStore));

		injector = Guice.createInjector(modules);
	}


	@Override
	protected Injector getInjector() {
		return injector;
	}
}