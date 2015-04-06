package org.crossv;

public class Facilities {

	public Trace trace;

	public Facilities(ValidatorOptions options) {
		trace = new SystemOutTrace();
	}
}
