package uk.co.boombastech.http.handlers;

import uk.co.boombastech.utils.Builder;

public abstract class HandlerBuilder<T extends HandlerBuilder, V> implements Builder<V> {
	protected String contextPath = "/";

	public T withContextPath(String contextPath) {
		this.contextPath = contextPath;
		return (T) this;
	}
}