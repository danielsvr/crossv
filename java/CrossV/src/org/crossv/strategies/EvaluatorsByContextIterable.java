package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterable implements Iterable<Evaluator> {

	private Iterable<EvaluatorProxy> evaluators;

	public EvaluatorsByContextIterable(Iterable<EvaluatorProxy> evaluators) {
		this.evaluators = evaluators;
	}

	@Override
	public Iterator<Evaluator> iterator() {
		return new EvaluatorsByContextIterator(evaluators.iterator());
	}
}
