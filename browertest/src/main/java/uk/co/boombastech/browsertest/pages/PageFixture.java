package uk.co.boombastech.browsertest.pages;

import org.openqa.selenium.WebDriver;

public abstract class PageFixture {

	private final WebDriver webDriver;
	protected final PageFactory pageFactory;

	public PageFixture(WebDriver webDriver, PageFactory pageFactory) {
		this.webDriver = webDriver;
		this.pageFactory = pageFactory;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}

	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}
}