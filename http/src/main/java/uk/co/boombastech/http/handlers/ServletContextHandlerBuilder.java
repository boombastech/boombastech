package uk.co.boombastech.http.handlers;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.http.injection.ListenerBuilder;

import java.util.EnumSet;
import java.util.EventListener;

public class ServletContextHandlerBuilder extends HandlerBuilder<ServletContextHandlerBuilder, ServletContextHandler> {
	private EventListener eventListener;

	public ServletContextHandlerBuilder withEventListener(EventListener eventListener) {
		this.eventListener = eventListener;
		return this;
	}

	public ServletContextHandlerBuilder withEventListener(ListenerBuilder listenerBuilder) {
		eventListener = listenerBuilder.build();
		return this;
	}

	@Override
	public ServletContextHandler build() {
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath(contextPath);
		servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
		servletContextHandler.addEventListener(eventListener);
		servletContextHandler.addServlet(DefaultServlet.class, "/");

		return servletContextHandler;
	}

	public static ServletContextHandlerBuilder servletContextHandler() {
		return new ServletContextHandlerBuilder();
	}
}