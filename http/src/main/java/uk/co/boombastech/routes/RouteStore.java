package uk.co.boombastech.routes;

import uk.co.boombastech.http.Request;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RouteStore {

	private final List<Route> routes;

	@Inject
	public RouteStore(MutableRouteStore mutableRouteStore) {
		this.routes = mutableRouteStore.getRoutes();
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public Route getRouteFor(Request request) {
		for (Route route : routes) {
			if (route.getUrl().equalsIgnoreCase(request.getUrl())) {
				return route;
			}
		}

		// TODO: change to error route
		return null;
	}
}