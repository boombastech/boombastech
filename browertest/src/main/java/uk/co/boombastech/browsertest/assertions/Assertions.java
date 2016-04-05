package uk.co.boombastech.browsertest.assertions;

import uk.co.boombastech.browsertest.elements.Element;

public class Assertions extends org.assertj.core.api.Assertions {

	public static ElementAssert assertThat(Element element) {
		return new ElementAssert(element);
	}
}