package uk.co.boombastech.web;

import com.google.inject.AbstractModule;
import uk.co.boombastech.routes.MutableRouteStore;

public class RouteStoreModule extends AbstractModule {

	private final MutableRouteStore routeStore;

	public RouteStoreModule(MutableRouteStore routeStore) {
		this.routeStore = routeStore;
	}

	@Override
	protected void configure() {
		bind(MutableRouteStore.class).toInstance(routeStore);
	}
}