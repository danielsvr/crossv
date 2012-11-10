package org.crossv.tests.subjects;

import org.crossv.EvaluationResult;
import org.crossv.Evaluator;
import org.crossv.primitives.Iterables;

public class TestableMonkeyEvaluator<EContext> extends Evaluator<Monkey, EContext> {

	public Iterable<EvaluationResult> results;

	public TestableMonkeyEvaluator(Class<EContext> contextClass) {
		super(Monkey.class, contextClass);
	}

	@Override
	public Iterable<EvaluationResult> Evaluate(Monkey obj, Object context) {
		return Iterables.empty();
	}

}
