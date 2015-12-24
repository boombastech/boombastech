package uk.co.boombastech.http.injection;

import com.google.common.collect.Lists;
import com.google.inject.Injector;
import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.routes.ActiveRoute;
import uk.co.boombastech.http.routes.Route;
import uk.co.boombastech.http.routes.RouteStore;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.List;

public class ActiveRouteProvider implements Provider<ActiveRoute> {

	private final RouteStore routeStore;
	private final Injector injector;
	private final Provider<Request> requestProvider;

	@Inject
	public ActiveRouteProvider(Provider<Request> requestProvider, RouteStore routeStore, Injector injector) {
		this.requestProvider = requestProvider;
		this.routeStore = routeStore;
		this.injector = injector;
	}

	@Override
	public ActiveRoute get() {
		Route route = routeStore.getRouteFor(requestProvider.get());
		List<Controller> controllers = Lists.newArrayList();

		for (Class<? extends Controller> controllerClass : route) {
			controllers.add(injector.getInstance(controllerClass));
		}

		return new ActiveRoute(controllers);
	}
}