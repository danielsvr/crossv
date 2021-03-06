package org.crossv.tests.helpers;

import org.crossv.primitives.Iterables;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class HasSizeMatcher<E extends Iterable<?>> extends TypeSafeMatcher<E> {
	private final Number size;
	private int listSize;

	public HasSizeMatcher(Number size) {
		this.size = size;
	}

	@Override
	public boolean matchesSafely(E obj) {
		Iterable<?> iterable = (Iterable<?>) obj;
		listSize = Iterables.count(iterable);
		return size.equals(listSize);
	}

	public void describeTo(Description description) {
		description.appendText("has size ").appendValue(size)
				.appendText(". evaluation result").appendValue(listSize);
	}
}