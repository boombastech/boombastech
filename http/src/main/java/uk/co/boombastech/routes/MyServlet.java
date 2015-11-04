package uk.co.boombastech.routes;

import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;
import uk.co.boombastech.injection.ActiveRouteProvider;
import uk.co.boombastech.injection.RequestProvider;
import uk.co.boombastech.injection.ResponseProvider;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Singleton
public class MyServlet extends HttpServlet {

	private final Provider<Request> requestProvider;
	private final Provider<Response> responseProvider;
	private final ActiveRouteProvider activeRouteProvider;

	@Inject
	public MyServlet(Provider<Request> requestProvider, Provider<Response> responseProvider, ActiveRouteProvider activeRouteProvider) {
		this.requestProvider = requestProvider;
		this.responseProvider = responseProvider;
		this.activeRouteProvider = activeRouteProvider;
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		Request request = requestProvider.get();
		Response response = responseProvider.get();

		for (Controller controller : activeRouteProvider.get()) {
			controller.execute(request, response);
		}

		response.render();
	}
}