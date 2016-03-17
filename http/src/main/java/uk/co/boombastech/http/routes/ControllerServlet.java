package uk.co.boombastech.http.routes;

import uk.co.boombastech.http.controllers.Controller;
import uk.co.boombastech.http.request.Request;
import uk.co.boombastech.http.request.Response;
import uk.co.boombastech.http.injection.ActiveRouteProvider;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Singleton
public class ControllerServlet extends HttpServlet {

	private final Provider<Request> requestProvider;
	private final Provider<Response> responseProvider;
	private final ActiveRouteProvider activeRouteProvider;

	@Inject
	public ControllerServlet(Provider<Request> requestProvider, Provider<Response> responseProvider, ActiveRouteProvider activeRouteProvider) {
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