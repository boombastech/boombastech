package uk.co.boombastech.injection;

import com.google.common.collect.Lists;
import com.google.inject.Injector;
import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.routes.ActiveRoute;
import uk.co.boombastech.routes.Route;
import uk.co.boombastech.routes.RouteStore;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

public class ActiveRouteProvider implements Provider<ActiveRoute> {

	private final Request request;
	private final RouteStore routeStore;
	private final Injector injector;

	@Inject
	public ActiveRouteProvider(Request request, RouteStore routeStore, Injector injector) {
		this.request = request;
		this.routeStore = routeStore;
		this.injector = injector;
	}

	@Override
	public ActiveRoute get() {
		Route route = routeStore.getRouteFor(request);
		List<Controller> controllers = Lists.newArrayList();

		for (Class<? extends Controller> controllerClass : route) {
			controllers.add(injector.getInstance(controllerClass));
		}

		return new ActiveRoute(controllers);
	}
}