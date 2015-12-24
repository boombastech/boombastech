package uk.co.boombastech.http.injection;

import com.google.inject.AbstractModule;
import uk.co.boombastech.http.routes.MutableRouteStore;

public abstract class WebModule extends AbstractModule {

	public abstract void configureRoutes(MutableRouteStore mutableRouteStore);
}