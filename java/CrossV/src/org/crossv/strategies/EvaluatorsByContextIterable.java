package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterable implements Iterable<Evaluator> {

	private Iterable<EvaluatorProxy> evaluators;
	private EvaluatorListener evaluatorListener;

	public EvaluatorsByContextIterable(EvaluatorListener evaluatorListener, Iterable<EvaluatorProxy> evaluators) {
		this.evaluatorListener = evaluatorListener;
		this.evaluators = evaluators;
	}

	@Override
	public Iterator<Evaluator> iterator() {
		return new EvaluatorsByContextIterator(evaluatorListener, evaluators.iterator());
	}
}
