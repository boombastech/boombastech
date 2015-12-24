package uk.co.boombastech.cache;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class LocalCache<S, T> implements Cache<S, T> {

	private final Map<S, T> cache;

	public LocalCache() {
		cache = newHashMap();
	}

	@Override
	public T get(S key) {
		return cache.get(key);
	}

	@Override
	public void add(S key, T value) {
		cache.put(key, value);
	}

	@Override
	public boolean contains(S key) {
		return cache.containsKey(key);
	}

	@Override
	public void remove(S key) {
		cache.remove(key);
	}
}