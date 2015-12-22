package uk.co.boombastech.properties;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class WebServerConfig {

	private int portNumber;
	private List<HandlerConfig> handlerConfigList;

	public WebServerConfig() {
		handlerConfigList = newArrayList();
	}

	public List<HandlerConfig> getHandlerConfigList() {
		return handlerConfigList;
	}

	public void addHandlerConfig(HandlerConfig handlerConfig) {
		handlerConfigList.add(handlerConfig);
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}


}