package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterable implements Iterable<Evaluator> {

	private Iterable<Evaluator> evaluators;
	private final Class<?> contextClass;

	public EvaluatorsByContextIterable(Iterable<Evaluator> evaluators, Class<?> contextClass) {
		this.evaluators = evaluators;
		this.contextClass = contextClass;
	}

	@Override
	public Iterator<Evaluator> iterator() {
		return new EvaluatorsByContextIterator(evaluators, contextClass);
	}
}
