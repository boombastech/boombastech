package uk.co.boombastech.properties;

public abstract class HandlerConfig {

	private String contextPath = "/";

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
}