package uk.co.boombastech.properties;

import java.util.EventListener;

public class ServletContextHandlerConfig extends HandlerConfig {

	private EventListener listener;

	public EventListener getListener() {
		return listener;
	}

	public void setListener(EventListener listener) {
		this.listener = listener;
	}
}