package org.crossv.tests.helpers;

import org.crossv.primitives.Iterables;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class IsEmptyMatcher<E extends Iterable<?>> extends TypeSafeMatcher<E> {

	@Override
	public boolean matchesSafely(E obj) {
		Iterable<?> iterable = (Iterable<?>) obj;
		return !Iterables.any(iterable);
	}

	public void describeTo(Description description) {
		description.appendText("Empty iterable ");
	}

}