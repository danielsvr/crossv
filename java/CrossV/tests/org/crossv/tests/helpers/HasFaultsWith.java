package org.crossv.tests.helpers;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

public class HasFaultsWith extends TypeSafeMatcher<Iterable<Evaluation>> {
	private final Exception cause;
	private final MatchingCause matchingCause = new MatchingCause();

	public HasFaultsWith(Exception cause) {
		this.cause = cause;
	}

	@Override
	public boolean matchesSafely(Iterable<Evaluation> obj) {
		return Iterables.any(obj, matchingCause);
	}

	public void describeTo(Description description) {
		description.appendText("contains evaluations faults with exception ")
				.appendValue(cause);
	}
	
	private class MatchingCause implements Predicate<Evaluation> {
		@Override
		public boolean eval(Evaluation value) {
			return value instanceof EvaluationFault
					&& ((EvaluationFault) value).getCause().equals(cause);
		}
	}
}