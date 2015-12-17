package uk.co.boombastech.http;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;

public class Parameters implements Iterable<String> {

	private final Map<String, Collection<String>> parameters;

	public Parameters(Map<String, Collection<String>> parameters) {
		this.parameters = parameters;
	}

	public boolean hasParameterValue(Parameter key) {
		return parameters.containsKey(key);
	}

	public Collection<String> getParameterValue(String key) {
		return parameters.getOrDefault(key, emptyList());
	}

	@Override
	public Iterator<String> iterator() {
		return parameters.keySet().iterator();
	}
}