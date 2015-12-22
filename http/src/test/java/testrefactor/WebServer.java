package testrefactor;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import uk.co.boombastech.properties.WebServerConfig;

public class WebServer {

	public WebServer(WebServerConfig webServerConfig) {
		Server server = new Server(webServerConfig.getPortNumber());

		try {
			HandlerCollection handlerCollection = new HandlerCollection();
			server.setHandler(handlerCollection);

			server.join();
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}