package uk.co.boombastech.routes;

import uk.co.boombastech.controllers.Controller;

import java.util.Iterator;
import java.util.List;

import static com.google.common.collect.Lists.asList;

public class Route implements Iterable<Class<? extends Controller>> {

	private String url;
	private List<Class<? extends Controller>> controllers;

	@SafeVarargs
	public Route(String url, Class<? extends Controller> controller, Class<? extends Controller>... controllers) {
		this.url = url;
		this.controllers = asList(controller, controllers);
	}

	public Route(String url, List<Class<? extends Controller>> controllers) {
		this.url = url;
		this.controllers = controllers;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public Iterator<Class<? extends Controller>> iterator() {
		return controllers.iterator();
	}
}