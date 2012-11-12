package org.crossv.tests.helpers;

import org.crossv.Evaluator;
import org.crossv.EvaluatorRegistry;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

public class EvaluatorRegistryContainsMatcher extends
		TypeSafeMatcher<EvaluatorRegistry> {

	private final Evaluator<?, ?> evaluator;

	public EvaluatorRegistryContainsMatcher(Evaluator<?, ?> evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("registry does not contain evaluator");
	}

	@Override
	public boolean matchesSafely(EvaluatorRegistry registry) {
		return registry.contains(evaluator);
	}
}
