package uk.co.boombastech.web;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.DispatcherType;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import uk.co.boombastech.properties.ServletContextHandlerConfig;

import java.util.EnumSet;

public class ServletContextHandlerFactory {

	public ContextHandler create(ServletContextHandlerConfig config) {
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath(config.getContextPath());
		servletContextHandler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
		servletContextHandler.addEventListener(config.getListener());
		servletContextHandler.addServlet(DefaultServlet.class, "/");
		return servletContextHandler;
	}
}