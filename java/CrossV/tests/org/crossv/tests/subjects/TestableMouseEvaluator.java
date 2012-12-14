package org.crossv.tests.subjects;

import org.crossv.ContextEvaluator;
import org.crossv.Evaluation;
import org.crossv.primitives.Iterables;

public class TestableMouseEvaluator<E> extends ContextEvaluator<Mouse, E> implements TestableEvaluator  {

	private RuntimeException exception;
	public Iterable<Evaluation> results;

	public TestableMouseEvaluator(Class<E> contextClass) {
		super(Mouse.class, contextClass);
	}

	@Override
	public Iterable<Evaluation> evaluateInstance(Mouse obj, E context) {
		if (exception != null)
			throw exception;
		return Iterables.emptyIfNull(results);
	}

	@Override
	public void returns(Evaluation... results) {
		returns(Iterables.toIterable(results));
	}

	@Override
	public void returns(Iterable<Evaluation> results) {
		this.results = results;
	}

	@Override
	public void isThrowing(RuntimeException exception) {
		this.exception = exception;
	}
}
