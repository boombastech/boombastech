package uk.co.boombastech.browsertest.elements.values;

public class TargetCustomValue implements Target {

	private final String value;

	public TargetCustomValue(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}
}