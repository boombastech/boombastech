package uk.co.boombastech.web;

import com.google.inject.AbstractModule;
import uk.co.boombastech.routes.MutableRouteStore;

public abstract class WebModule extends AbstractModule {

	public abstract void configureRoutes(MutableRouteStore mutableRouteStore);
}