package uk.co.boombastech.http.routes;

import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.utils.Builder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class RouteBuilder implements Builder<Route> {

	private final String url;
	private final List<Class<? extends Controller>> controllers;

	public RouteBuilder(String url) {
		this.url = url;
		controllers = newArrayList();
	}

	public RouteBuilder withController(Class<? extends Controller> controller) {
		controllers.add(controller);
		return this;
	}

	@Override
	public Route build() {
		return new Route(url, controllers);
	}

	public static RouteBuilder route(String url) {
		return new RouteBuilder(url);
	}
}