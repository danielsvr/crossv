package org.crossv.tests.subjects;

import org.crossv.EvaluationResult;
import org.crossv.ContextEvaluator;
import org.crossv.primitives.Iterables;

public class TestableMonkeyEvaluator<EContext> extends
		ContextEvaluator<Monkey, EContext> {

	public EvaluationResult result;

	public TestableMonkeyEvaluator(Class<EContext> contextClass) {
		super(Monkey.class, contextClass);
	}

	@Override
	public Iterable<EvaluationResult> evaluateInstance(Monkey obj, Object context) {
		return result != null 
				? Iterables.<EvaluationResult> toIterable(result)
				: Iterables.<EvaluationResult> empty();
	}
}
