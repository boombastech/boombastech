package uk.co.boombastech.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import uk.co.boombastech.properties.HandlerConfig;
import uk.co.boombastech.properties.ServletContextHandlerConfig;
import uk.co.boombastech.properties.WebServerConfig;

public class WebServer {

	public WebServer(WebServerConfig webServerConfig) throws Exception {
		Server server = new Server(webServerConfig.getPortNumber());

		ServletContextHandlerFactory servletContextHandlerFactory = new ServletContextHandlerFactory();

		HandlerCollection handlerCollection = new HandlerCollection();

		for (HandlerConfig handlerConfig : webServerConfig.getHandlerConfigList()) {
			if (handlerConfig instanceof ServletContextHandlerConfig) {
				handlerCollection.addHandler(servletContextHandlerFactory.create((ServletContextHandlerConfig) handlerConfig));
			}
		}

		server.setHandler(handlerCollection);
		server.start();
		server.join();
	}
}