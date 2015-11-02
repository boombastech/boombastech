package uk.co.boombastech.web;

import com.google.inject.AbstractModule;
import uk.co.boombastech.routes.MutableRouteStore;

public abstract class WebModule extends AbstractModule {

	protected final MutableRouteStore mutableRouteStore;

	public WebModule(MutableRouteStore mutableRouteStore) {
		this.mutableRouteStore = mutableRouteStore;
	}

	@Override
	protected abstract void configure();
}