package uk.co.boombastech.web;

import org.eclipse.jetty.server.Server;

public class WebServer {

	private final Server server;

	public WebServer(Server server) {
		this.server = server;
	}

	public void start() throws Exception {
		server.start();
		server.join();
	}
}