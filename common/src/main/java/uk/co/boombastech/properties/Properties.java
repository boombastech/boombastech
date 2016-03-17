package uk.co.boombastech.properties;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class Properties {

	private Map<PropertyName, Property> properties;

	public Properties() {
		properties = newHashMap();

	}

	public <T> T getProperty(PropertyName propertyName) {
		Property property = properties.get(propertyName);
		Class<T> type = property.getType();
		return type.cast(property);
	}
}