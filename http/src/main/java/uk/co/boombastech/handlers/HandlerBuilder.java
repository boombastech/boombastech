package uk.co.boombastech.handlers;

import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;
import uk.co.boombastech.properties.ResourceHandlerConfig;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class HandlerBuilder {
	private String contextPath = "/";
	private String resourceBase = ".";
	private boolean showDirectory;
	private List<String> welcomeFiles = newArrayList();

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

	public HandlerBuilder withWelcomeFile(String welcomeFile) {
		welcomeFiles.add(welcomeFile);
		return this;
	}

	public ContextHandler build() {
		ResourceHandler resourceHandler = new ResourceHandler();
		resourceHandler.setDirectoriesListed(showDirectory);
		resourceHandler.setResourceBase(resourceBase);

		if (!welcomeFiles.isEmpty()) {
			String[] welcomeFilesArray = new String[welcomeFiles.size()];
			resourceHandler.setWelcomeFiles(welcomeFiles.toArray(welcomeFilesArray));
		}

		ContextHandler contextHandler = new ContextHandler(contextPath);
		contextHandler.setHandler(resourceHandler);

		return contextHandler;
	}

	public ResourceHandlerConfig buildHandlerConfig() {
		ResourceHandlerConfig handlerConfig = new ResourceHandlerConfig();
		handlerConfig.setContextPath(contextPath);
		handlerConfig.setResourceBase(resourceBase);
		handlerConfig.setShowDirectory(showDirectory);
		handlerConfig.setWelcomeFiles(welcomeFiles);
		return handlerConfig;
	}
}