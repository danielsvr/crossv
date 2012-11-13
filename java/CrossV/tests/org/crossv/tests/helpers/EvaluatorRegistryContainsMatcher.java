package org.crossv.tests.helpers;

import org.crossv.ContextEvaluator;
import org.crossv.EvaluatorRegistry;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

public class EvaluatorRegistryContainsMatcher extends
		TypeSafeMatcher<EvaluatorRegistry> {

	private final ContextEvaluator<?, ?> evaluator;

	public EvaluatorRegistryContainsMatcher(ContextEvaluator<?, ?> evaluator) {
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
