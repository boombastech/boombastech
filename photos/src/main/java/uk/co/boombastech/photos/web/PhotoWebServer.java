package uk.co.boombastech.photos.web;

import uk.co.boombastech.properties.ServletContextHandlerConfig;
import uk.co.boombastech.properties.WebServerConfig;
import uk.co.boombastech.web.BoombastechListener;
import uk.co.boombastech.web.WebServer;

public class PhotoWebServer {

	public static void main(String[] args) throws Exception {
		ServletContextHandlerConfig handlerConfig = new ServletContextHandlerConfig();
		handlerConfig.setListener(new PhotoBoombastechListener());
		handlerConfig.setContextPath("/api");

		WebServerConfig webServerConfig = new WebServerConfig();
		webServerConfig.setPortNumber(8080);
		webServerConfig.addHandlerConfig(handlerConfig);

		new WebServer(webServerConfig);
	}
}
