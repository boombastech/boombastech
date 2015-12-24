package uk.co.boombastech.cache;

public interface Cache<S,T> {
	T get(S key);
	void add(S key, T value);
	boolean contains(S key);
	void remove(S key);
}