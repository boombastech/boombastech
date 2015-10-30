package uk.co.boombastech.routes;

import uk.co.boombastech.controllers.Controller;

import java.util.Iterator;
import java.util.List;

public class ActiveRoute implements Iterable<Controller> {

	private final List<Controller> controllers;

	public ActiveRoute(List<Controller> controllers) {
		this.controllers = controllers;
	}

	@Override
	public Iterator<Controller> iterator() {
		return controllers.iterator();
	}
}