package uk.co.boombastech.properties;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ResourceHandlerConfig extends HandlerConfig {

	private String resourceBase = ".";
	private boolean showDirectory;
	private List<String> welcomeFiles = newArrayList();

	public String getResourceBase() {
		return resourceBase;
	}

	public void setResourceBase(String resourceBase) {
		this.resourceBase = resourceBase;
	}

	public boolean isShowDirectory() {
		return showDirectory;
	}

	public void setShowDirectory(boolean showDirectory) {
		this.showDirectory = showDirectory;
	}

	public List<String> getWelcomeFiles() {
		return welcomeFiles;
	}

	public void setWelcomeFiles(List<String> welcomeFiles) {
		this.welcomeFiles = welcomeFiles;
	}
}