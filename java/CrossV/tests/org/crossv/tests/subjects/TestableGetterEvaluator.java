package org.crossv.tests.subjects;

import org.crossv.Evaluation;
import org.crossv.getters.GetterEvaluator;
import org.crossv.getters.descriptors.GetterDescriptor;
import org.crossv.primitives.Iterables;

public class TestableGetterEvaluator<E> extends GetterEvaluator<E> implements
		TestableEvaluator {

	private Iterable<Evaluation> results;
	private RuntimeException exception;

	public TestableGetterEvaluator(GetterDescriptor<E> getter) {
		super(getter);
	}

	@Override
	protected Iterable<Evaluation> evaluateGetter(E scopeInstance,
			Object getterValue) throws Exception {
		if (exception != null)
			throw exception;
		return results;
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
