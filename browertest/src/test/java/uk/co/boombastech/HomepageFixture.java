package uk.co.boombastech;

import org.openqa.selenium.WebDriver;
import uk.co.boombastech.browsertest.elements.A;
import uk.co.boombastech.browsertest.pages.PageFactory;
import uk.co.boombastech.browsertest.pages.PageFixture;

public class HomepageFixture extends PageFixture {

	public HomepageFixture(WebDriver webDriver, PageFactory pageFactory) {
		super(webDriver, pageFactory);
	}

	public SportsLandingPage clickSportsNavButton() {
		A sportsNavButton = new A(null);
		sportsNavButton.click();
		return pageFactory.createPage(SportsLandingPage.class);
	}
}