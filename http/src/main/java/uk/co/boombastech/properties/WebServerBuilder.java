package uk.co.boombastech.properties;

import com.google.inject.Module;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ContextHandler;
import uk.co.boombastech.routes.MutableRouteStore;
import uk.co.boombastech.web.WebServer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class WebServerBuilder {

	private int portNumber;
	private List<Module> modules = newArrayList();
	private List<ContextHandler> handlers = newArrayList();
	private String context = "/";

	public WebServerBuilder withPortNumber(int portNumber) {
		this.portNumber = portNumber;
		return this;
	}

	public WebServerBuilder withModule(Module module) {
		modules.add(module);
		return this;
	}

	public WebServerBuilder withHandler(ContextHandler handler) {
		handlers.add(handler);
		return this;
	}

	public WebServerBuilder withContextPath(String contextPath) {
		context = contextPath;
		return this;
	}

	public WebServer build() throws Exception {
		return new WebServer() {
			@Override
			public List<Handler> additionalHandlers(List<Handler> existingHandlers) {
				existingHandlers.addAll(handlers);
				return existingHandlers;
			}

			@Override
			public List<Module> modules(MutableRouteStore mutableRouteStore) {
				return modules;
			}

			@Override
			public int portNumber() {
				return portNumber;
			}

			@Override
			public String baseContextPath() {
				return context;
			}
		};
	}
}