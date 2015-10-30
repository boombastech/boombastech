package uk.co.boombastech.routes;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class MutableRouteStore {
	private final List<Route> routes;

	public MutableRouteStore() {
		routes = newArrayList();
	}

	public void withRoute(Route route) {
		routes.add(route);
	}

	public void withRoute(RouteBuilder route) {
		routes.add(route.build());
	}

	public List<Route> getRoutes() {
		return routes;
	}
}