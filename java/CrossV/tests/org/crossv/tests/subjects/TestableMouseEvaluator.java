package org.crossv.tests.subjects;

import org.crossv.ContextEvaluator;
import org.crossv.EvaluationResult;
import org.crossv.IllegalObjectException;
import org.crossv.primitives.Iterables;

public class TestableMouseEvaluator<E> extends ContextEvaluator<Mouse, E> {

	public EvaluationResult result;

	public TestableMouseEvaluator(Class<E> contextClass) {
		super(Mouse.class, contextClass);
	}

	@Override
	public Iterable<EvaluationResult> evaluateInstance(Mouse obj, E context)
			throws IllegalObjectException {
		return result != null 
				? Iterables.<EvaluationResult> toIterable(result)
				: Iterables.<EvaluationResult> empty();
	}

}
