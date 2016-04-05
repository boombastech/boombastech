package uk.co.boombastech.browsertest.pages;

import org.openqa.selenium.WebDriver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PageFactory {

	private final WebDriver webDriver;

	public PageFactory(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public <T extends PageFixture> T createPage(Class<T> pageFixture) {
		try {
			Constructor<T> constructor = pageFixture.getConstructor(WebDriver.class, PageFactory.class);
			return constructor.newInstance(webDriver, this);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}
}