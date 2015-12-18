package uk.co.boombastech.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

import java.util.List;

public abstract class Listener extends GuiceServletContextListener {

	private final Injector injector;

	public Listener() {
		injector = Guice.createInjector(modules());
	}

	public abstract List<Module> modules();

	@Override
	protected Injector getInjector() {
		return injector;
	}
}