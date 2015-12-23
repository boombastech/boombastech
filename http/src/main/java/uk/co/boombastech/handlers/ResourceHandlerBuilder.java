package uk.co.boombastech.handlers;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class ResourceHandlerBuilder extends HandlerBuilder<ResourceHandlerBuilder, ContextHandler> {
	private String resourceBase = ".";
	private boolean showDirectory;

	public ResourceHandlerBuilder withResourceBase(String resourceBase) {
		this.resourceBase = resourceBase;
		return this;
	}

	public ResourceHandlerBuilder withShowDirectory(boolean showDirectory) {
		this.showDirectory = showDirectory;
		return this;
	}

	@Override
	public ContextHandler build() {
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(showDirectory);
		resourceHandler.setResourceBase(resourceBase);

		ContextHandler contextHandler = new ContextHandler(contextPath);
		contextHandler.setHandler(resourceHandler);

		return contextHandler;
	}

	public static ResourceHandlerBuilder resourceHandler() {
		return new ResourceHandlerBuilder();
	}
}