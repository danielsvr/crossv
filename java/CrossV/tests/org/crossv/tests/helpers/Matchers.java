package org.crossv.tests.helpers;

import org.hamcrest.Matcher;

public class Matchers {

	public static <E extends Iterable<?>> Matcher<E> isEmpty() {
		return new IsEmptyMatcher<E>();
	}

	public static <E extends Iterable<?>> Matcher<E> hasSize(int size) {
		return new HasSizeMatcher<E>(size);
	}
	

	public static <E> Matcher<Iterable<E>> hasAll(Iterable<E> objs) {
		return new HasElementsMatcher<E>(objs);
	}
}
