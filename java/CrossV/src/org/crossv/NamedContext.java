package org.crossv;

import static org.crossv.primitives.Strings.isNullOrWhitespace;

import org.crossv.primitives.ArgumentNullException;

public final class NamedContext {
	private String name;

	public NamedContext(String name) {
		if (isNullOrWhitespace(name))
			throw new ArgumentNullException("name");
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
