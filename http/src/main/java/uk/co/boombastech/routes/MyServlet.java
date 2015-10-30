package uk.co.boombastech.routes;

import com.google.inject.Injector;
import uk.co.boombastech.controllers.Controller;
import uk.co.boombastech.http.Request;
import uk.co.boombastech.http.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Singleton
public class MyServlet extends HttpServlet {

	private final Injector injector;

	@Inject
	public MyServlet(Injector injector) {
		this.injector = injector;
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
		Request request = injector.getInstance(Request.class);
		Response response = injector.getInstance(Response.class);

		for (Controller controller : injector.getInstance(ActiveRoute.class)) {
			controller.execute(request, response);
		}
	}
}