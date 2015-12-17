package uk.co.boombastech.handlers;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class HandlerBuilder {
	private String contextPath = "/";
	private String resourceBase = ".";
	private boolean showDirectory;

	public HandlerBuilder withContextPath(String contextPath) {
		this.contextPath = contextPath;
		return this;
	}

	public HandlerBuilder withResourceBase(String resourceBase) {
		this.resourceBase = resourceBase;
		return this;
	}

	public HandlerBuilder withShowDirectory(boolean showDirectory) {
		this.showDirectory = showDirectory;
		return this;
	}

	public ContextHandler build() {
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(showDirectory);
		resourceHandler.setResourceBase(resourceBase);

		ContextHandler contextHandler = new ContextHandler(contextPath);
		contextHandler.setHandler(resourceHandler);

		return contextHandler;
	}
}