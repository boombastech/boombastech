package uk.co.boombastech.web;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import uk.co.boombastech.common.Builder;
import uk.co.boombastech.handlers.HandlerBuilder;

public class WebServerBuilder implements Builder<WebServer> {

	private int portNumber;
	private HandlerCollection handlerCollection = new HandlerCollection();

	public WebServerBuilder withPortNumber(int portNumber) {
		this.portNumber = portNumber;
		return this;
	}

	public WebServerBuilder withHandler(Handler handler) {
		handlerCollection.addHandler(handler);
		return this;
	}

	public WebServerBuilder withHandler(HandlerBuilder<?, ? extends Handler> handler) {
		handlerCollection.addHandler(handler.build());
		return this;
	}

	@Override
	public WebServer build() {
		Server server = new Server(portNumber);
		server.setHandler(handlerCollection);
		return new WebServer(server);
	}
}