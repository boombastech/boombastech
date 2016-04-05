package uk.co.boombastech.browsertest.elements;

import org.openqa.selenium.WebElement;
import uk.co.boombastech.browsertest.elements.values.Target;
import uk.co.boombastech.browsertest.elements.values.TargetCustomValue;
import uk.co.boombastech.browsertest.elements.values.TargetDefaultValues;

import java.util.Optional;

public class A extends Element {

	public A(WebElement webElement) {
		super(webElement);
	}

	@Override
	protected String getTag() {
		return "a";
	}

	public String getHref() {
		return webElement.getAttribute("href");
	}

	public Optional<String> getDownload() {
		return Optional.ofNullable(webElement.getAttribute("download"));
	}

	public Optional<String> getHrefLanguage() {
		return Optional.ofNullable(webElement.getAttribute("hreflang"));
	}

	public Optional<String> getMedia() {
		return Optional.ofNullable(webElement.getAttribute("media"));
	}

	public Optional<String> getRelationship() {
		return Optional.ofNullable(webElement.getAttribute("rel"));
	}

	public Optional<? extends Target> getTarget() {
		String target = webElement.getAttribute("target");
		if (target == null) {
			return Optional.empty();
		} else {
			try {
				return Optional.of(TargetDefaultValues.valueOf(target));
			} catch (IllegalArgumentException e) {
				return Optional.of(new TargetCustomValue(target));
			}
		}
	}

	public Optional<String> getType() {
		return Optional.ofNullable(webElement.getAttribute("type"));
	}
}