package org.crossv.tests.subjects;

public class ObjectToString {
	private String toString;

	public ObjectToString(String toString) {
		this.toString = toString;
	}

	@Override
	public String toString() {
		return toString;
	}
}
