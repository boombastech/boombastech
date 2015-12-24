package uk.co.boombastech.http.injection;

import com.google.inject.Module;
import uk.co.boombastech.utils.Builder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ListenerBuilder implements Builder<Listener> {

	private List<Module> modules = newArrayList();

	public ListenerBuilder withModule(Module module) {
		modules.add(module);
		return this;
	}

	@Override
	public Listener build() {
		try {
			return new Listener(modules);
		} catch (Exception e) {
			return null;
		}
	}

	public static ListenerBuilder listener() {
		return new ListenerBuilder();
	}
}