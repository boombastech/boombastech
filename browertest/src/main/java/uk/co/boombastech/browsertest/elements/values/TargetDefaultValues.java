package uk.co.boombastech.browsertest.elements.values;

public enum TargetDefaultValues implements Target {
	blank,
	self,
	parent,
	top;

	@Override
	public String getValue() {
		return "_" + name();
	}
}