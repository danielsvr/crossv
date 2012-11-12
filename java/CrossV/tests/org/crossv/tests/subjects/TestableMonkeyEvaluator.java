package org.crossv.tests.subjects;

import org.crossv.EvaluationResult;
import org.crossv.Evaluator;
import org.crossv.primitives.Iterables;

public class TestableMonkeyEvaluator<EContext> extends
		Evaluator<Monkey, EContext> {

	public EvaluationResult result;

	public TestableMonkeyEvaluator(Class<EContext> contextClass) {
		super(Monkey.class, contextClass);
	}

	@Override
	public Iterable<EvaluationResult> evaluate(Monkey obj, Object context) {
		return result != null 
				? Iterables.<EvaluationResult> toIterable(result)
				: Iterables.<EvaluationResult> empty();
	}
}
