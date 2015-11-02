package uk.co.boombastech.web;

import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.injection.ActiveRouteProvider;
import uk.co.boombastech.injection.RequestProvider;
import uk.co.boombastech.injection.ResponseProvider;
import uk.co.boombastech.routes.ActiveRoute;
import uk.co.boombastech.routes.MyServlet;

public class BasicWebModule extends ServletModule {
	@Override
	protected void configureServlets() {
		serve("*").with(MyServlet.class);

		bind(Request.class).toProvider(RequestProvider.class).in(ServletScopes.REQUEST);
		bind(Response.class).toProvider(ResponseProvider.class).in(ServletScopes.REQUEST);
		bind(ActiveRoute.class).toProvider(ActiveRouteProvider.class).in(ServletScopes.REQUEST);
	}
}