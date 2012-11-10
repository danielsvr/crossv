package org.crossv.tests.helpers;

import java.util.List;

import org.crossv.primitives.Iterables;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

public class IsEmptyMatcher<E extends Iterable<?>> extends TypeSafeMatcher<E> {

	@Override
	public boolean matchesSafely(E obj) {
		Iterable<?> iterable = (Iterable<?>)obj;
		List<?> list = Iterables.toList(iterable);
		return list.isEmpty();
	}

	public void describeTo(Description description) {
		description.appendText("Empty iterable ");
	}
}