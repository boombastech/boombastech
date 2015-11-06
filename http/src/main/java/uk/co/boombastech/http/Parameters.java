package uk.co.boombastech.http;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static java.util.Collections.emptyList;

public class Parameters implements Iterable<Parameter> {

	private final Map<Parameter, Collection<String>> parameters;

	public Parameters(Map<Parameter, Collection<String>> parameters) {
		this.parameters = parameters;
	}

	public boolean hasParameterValue(Parameter key) {
		return parameters.containsKey(key);
	}

	public Collection<String> getParameterValue(Parameter key) {
		return parameters.getOrDefault(key, emptyList());
	}

	@Override
	public Iterator<Parameter> iterator() {
		return parameters.keySet().iterator();
	}
}