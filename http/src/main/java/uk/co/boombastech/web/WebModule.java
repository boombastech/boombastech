package uk.co.boombastech.web;

import com.google.inject.AbstractModule;
import uk.co.boombastech.routes.MutableRouteStore;

public abstract class WebModule extends AbstractModule {

	@Override
	protected void configure() {
		configure(new MutableRouteStore());
	}

	protected abstract void configure(MutableRouteStore mutableRouteStore);
}