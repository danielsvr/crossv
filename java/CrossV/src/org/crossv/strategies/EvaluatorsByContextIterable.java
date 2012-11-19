package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterable implements Iterable<Evaluator> {

	private Iterable<Evaluator> evaluators;

	public EvaluatorsByContextIterable(Iterable<Evaluator> evaluators) {
		this.evaluators = evaluators;
	}

	@Override
	public Iterator<Evaluator> iterator() {
		return evaluators.iterator();
	}
}
