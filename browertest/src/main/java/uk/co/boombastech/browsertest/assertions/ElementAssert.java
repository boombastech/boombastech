package uk.co.boombastech.browsertest.assertions;

import org.assertj.core.api.AbstractAssert;
import uk.co.boombastech.browsertest.elements.Element;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.join;

public class ElementAssert extends AbstractAssert<ElementAssert, Element> {

	protected ElementAssert(Element actual) {
		super(actual, ElementAssert.class);
	}

	public ElementAssert hasId(String expectedId) {
		isNotNull();

		Optional<String> elementId = actual.getId();
		if (elementId.isPresent()) {
			if (!expectedId.equals(elementId.get())) {
				failWithMessage("Expected element to have id of '%s', but was '%s'.", expectedId, elementId.get());
			}
		} else {
			failWithMessage("Expected element to have id of '%s', but was it didn't have an id.", expectedId);
		}

		return this;
	}

	public ElementAssert hasClass(String expectedClass) {
		isNotNull();

		List<String> classes = actual.getClasses();
		if (classes.isEmpty()) {
			failWithMessage("Expected element to have class '%s', but it didn't have any classes");
		} else if (!classes.contains(expectedClass)) {
			String actualClasses = join(classes, ", ");
			failWithMessage("Expected element to have class of '%s', but instead it had '%s'.", expectedClass, actualClasses);
		}

		return this;
	}
}