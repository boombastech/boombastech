package uk.co.boombastech;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import uk.co.boombastech.browsertest.pages.PageFactory;
import uk.co.boombastech.browsertest.pages.PageFixture;

public class IdealBrowserTest {

	@Test
	public void shouldLookLikeThis() throws Exception {
		WebDriver webDriver = null;

		// PageNameFixture homepage =  pageFactory.createPage(PageNameFixture.class, webdriver);
		// NavigationBarFixture navBar = homepage.getNavigationBar();
		// AboutUsPageFixture aboutUsPage = navBar.clickAboutUsPage();

		PageFixture page = new PageFactory(webDriver).createPage(PageFixture.class);
		String currentUrl = page.getCurrentUrl();
	}
}