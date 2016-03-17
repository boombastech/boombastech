package uk.co.boombastech.http.web;

import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.http.injection.ActiveRouteProvider;
import uk.co.boombastech.http.injection.RequestProvider;
import uk.co.boombastech.http.injection.ResponseProvider;
import uk.co.boombastech.http.routes.ActiveRoute;
import uk.co.boombastech.http.routes.ControllerServlet;
import uk.co.boombastech.json.JsonMarshaller;
import uk.co.boombastech.json.JsonMarshallerGson;

public class BasicWebModule extends ServletModule {
	@Override
	protected void configureServlets() {
		serve("/download").with(ZipServlet.class);
		serve("*").with(ControllerServlet.class);

		bind(JsonMarshaller.class).to(JsonMarshallerGson.class);
		bind(Request.class).toProvider(RequestProvider.class).in(ServletScopes.REQUEST);
		bind(Response.class).toProvider(ResponseProvider.class).in(ServletScopes.REQUEST);
		bind(ActiveRoute.class).toProvider(ActiveRouteProvider.class).in(ServletScopes.REQUEST);
	}
}