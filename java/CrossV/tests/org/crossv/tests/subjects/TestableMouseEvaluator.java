package org.crossv.tests.subjects;

import org.crossv.ContextEvaluator;
import org.crossv.Evaluation;
import org.crossv.IllegalObjectException;
import org.crossv.primitives.Iterables;

public class TestableMouseEvaluator<E> extends ContextEvaluator<Mouse, E> {

	public Evaluation result;

	public TestableMouseEvaluator(Class<E> contextClass) {
		super(Mouse.class, contextClass);
	}

	@Override
	public Iterable<Evaluation> evaluateInstance(Mouse obj, E context)
			throws IllegalObjectException {
		return result != null ? Iterables.<Evaluation> toIterable(result)
				: Iterables.<Evaluation> empty();
	}
}
