package org.crossv.tests.subjects;

import org.crossv.ContextEvaluator;
import org.crossv.Evaluation;
import org.crossv.primitives.Iterables;

public class TestableMonkeyEvaluator<EContext> extends
		ContextEvaluator<Monkey, EContext> implements TestableEvaluator {

	public Evaluation[] results;

	public TestableMonkeyEvaluator(Class<EContext> contextClass) {
		super(Monkey.class, contextClass);
	}

	@Override
	public Iterable<Evaluation> evaluateInstance(Monkey obj,
			EContext context) {
		return Iterables.toIterable(results);
	}

	@Override
	public void returns(Evaluation... results) {
		this.results = results;
	}
}
