package uk.co.boombastech.properties;

public abstract class Property<T> {
	private PropertyName name;
	private T value;
	private Class<T> type;



	public Class<T> getType() {
		return type;
	}
}