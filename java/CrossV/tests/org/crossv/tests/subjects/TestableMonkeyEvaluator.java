package org.crossv.tests.subjects;

import org.crossv.ContextEvaluator;
import org.crossv.Evaluation;
import org.crossv.primitives.Iterables;

public class TestableMonkeyEvaluator<EContext> extends
		ContextEvaluator<Monkey, EContext> implements TestableEvaluator {

	public Iterable<Evaluation> results;
	private RuntimeException exception;

	public TestableMonkeyEvaluator(Class<EContext> contextClass) {
		super(Monkey.class, contextClass);
	}

	@Override
	public Iterable<Evaluation> evaluateInstance(Monkey obj, EContext context) {
		if (exception != null)
			throw exception;
		return Iterables.emptyIfNull(results);
	}

	@Override
	public void returns(Iterable<Evaluation> results) {
		this.results = results;
	}

	@Override
	public void isThrowing(RuntimeException exception) {
		this.exception = exception;
	}

	@Override
	public void returns(Evaluation... results) {
		returns(Iterables.toIterable(results));
	}
}
