package org.crossv.tests.helpers;

import org.crossv.primitives.Action;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ThrowingException extends TypeSafeMatcher<Action> {

	private Class<? extends Throwable> expected;

	public ThrowingException(Class<? extends Throwable> expected) {
		this.expected = expected;
	}

	@Override
	public void describeTo(Description arg0) {
		arg0.appendText("excaption of type ").appendValue(expected.getName());
	}

	@Override
	public boolean matchesSafely(Action obj) {
		try {
			obj.run();
		} catch (Throwable e) {
			if (expected.isAssignableFrom(e.getClass()))
				return true;
		}
		return false;
	}
}
