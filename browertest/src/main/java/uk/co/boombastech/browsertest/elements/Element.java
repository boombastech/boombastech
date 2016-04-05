package uk.co.boombastech.browsertest.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import uk.co.boombastech.browsertest.elements.values.Direction;
import uk.co.boombastech.utils.NumberUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public abstract class Element {

	protected final WebElement webElement;

	public Element(WebElement webElement) {
		this.webElement = webElement;
	}

	protected abstract String getTag();

	protected By by() {
		return By.tagName(getTag());
	}

	public void click() {
		webElement.click();
	}

	public List<String> getClasses() {
		String[] cssClasses = webElement.getAttribute("class").split(" ");

		List<String> classes = newArrayList();
		for (String cssClass : cssClasses) {
			classes.add(cssClass.trim());
		}

		return classes;
	}

	public Optional<String> getId() {
		return Optional.ofNullable(webElement.getAttribute("id").trim());
	}

	public Optional<String> getAccessKey() {
		return Optional.ofNullable(webElement.getAttribute("accesskey"));
	}

	public boolean isContentEditable() {
		return Boolean.parseBoolean(webElement.getAttribute("contenteditable"));
	}

	public Optional<String> getContextMenu() {
		return Optional.ofNullable(webElement.getAttribute("contextmenu"));
	}

	public Optional<String> getData(String suffix) {
		return Optional.ofNullable(webElement.getAttribute("data-" + suffix));
	}

	public Direction getDirection() {
		return Direction.valueOf(webElement.getAttribute("dir"));
	}

	public boolean isDraggable() {
		return Boolean.parseBoolean(webElement.getAttribute("draggable"));
	}

	public boolean isHidden() {
		return Boolean.parseBoolean(webElement.getAttribute("hidden"));
	}

	public Optional<String> getLanguage() {
		return Optional.ofNullable(webElement.getAttribute("lang"));
	}

	public boolean isSpellcheck() {
		return Boolean.parseBoolean(webElement.getAttribute("spellcheck"));
	}

	public Map<String, String> getStyles() {
		Map<String, String> styles = newHashMap();
		String style = webElement.getAttribute("style");
		for (String keyValuePair : style.split(";")) {
			String[] split = keyValuePair.split(":");
			if (split.length == 2) {
				styles.put(split[0], split[1]);
			}
		}

		return styles;
	}

	public Optional<Integer> getTabIndex() {
		return NumberUtils.parseInteger(webElement.getAttribute("tabindex"));
	}

	public Optional<String> getTitle() {
		return Optional.ofNullable(webElement.getAttribute("title"));
	}
}