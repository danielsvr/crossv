package org.crossv.tests.helpers;

import org.crossv.primitives.Iterables;
import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;
import org.junit.internal.matchers.TypeSafeMatcher;

public class HasElementsMatcher<E> extends TypeSafeMatcher<Iterable<E>>
		implements SelfDescribing {
	private Iterable<E> objs;
	private boolean areAllContained;

	public HasElementsMatcher(Iterable<E> objs) {
		this.objs = objs;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("has all elements. evaludation result = ")
				.appendValue(areAllContained);
	}

	@Override
	public boolean matchesSafely(Iterable<E> obj) {
		areAllContained = Iterables.containsAll(obj, objs);
		return areAllContained;
	}
}
