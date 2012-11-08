package org.crossv.tests.helpers;

import org.hamcrest.*;

public class Matchers {

	public static <E extends Iterable<?>> Matcher<E> isEmpty() {
		return new IsEmptyMatcher<E>();
	}

	public static <E extends Iterable<?>> Matcher<E> isSize(int size) {
		return new IsSizeMatcher<E>(size);
	}
}
