package uk.co.boombastech.routes;

import uk.co.boombastech.controllers.Controller;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class RouteBuilder {

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

	public Route build() {
		return new Route(url, controllers);
	}

	public static RouteBuilder route(String url) {
		return new RouteBuilder(url);
	}
}