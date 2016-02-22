package uk.co.boombastech.http.routes;

import uk.co.boombastech.http.controllers.Controller;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class MutableRouteStore {
	private final List<Route> routes;

	public MutableRouteStore() {
		routes = newArrayList();
	}

	void addRoute(RouteBuilder route) {
		routes.add(route.build());
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public MRSBuilder addRoute(String url) {
		return new MRSBuilder(url, this);
	}

	public static class MRSBuilder {
		private final String url;
		private final MutableRouteStore mutableRouteStore;

		public MRSBuilder(String url, MutableRouteStore mutableRouteStore) {
			this.url = url;
			this.mutableRouteStore = mutableRouteStore;
		}

		public void withControllers(Class<? extends Controller>... controllers) {
			RouteBuilder routeBuilder = new RouteBuilder(url);

			for (Class<? extends Controller> controller : controllers) {
				routeBuilder.withController(controller);
			}

			mutableRouteStore.addRoute(routeBuilder);
		}
	}
}