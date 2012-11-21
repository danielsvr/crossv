package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterator implements Iterator<Evaluator> {

	private Iterator<EvaluatorProxy> mainIterator;
	private Iterator<EvaluatorProxy> remainsIterator;
	private Dictionary<Integer, List<EvaluatorProxy>> evaluatorsByLevel;
	private int currentDepth;
	private IteratorCancelationSource cancelationSource;

	public EvaluatorsByContextIterator(Iterator<EvaluatorProxy> evaluators,
			IteratorCancelationSource cancelationSource) {
		this.mainIterator = evaluators;
		this.cancelationSource = cancelationSource;
		this.evaluatorsByLevel = new Hashtable<Integer, List<EvaluatorProxy>>();
		this.currentDepth = 1;
	}

	@Override
	public boolean hasNext() {
		if (cancelationSource.isCanceled())
			return false;

		return mainIterator.hasNext()
				|| (remainsIterator != null && remainsIterator.hasNext());
	}

	@Override
	public Evaluator next() {
		if (cancelationSource.isCanceled())
			throw new NoSuchElementException("Iteration canceled.");

		List<EvaluatorProxy> evaluatorsByLevel;
		while (mainIterator.hasNext()) {
			EvaluatorProxy evaluator = mainIterator.next();

			int contextDepth = evaluator.getContextDepth();
			if (contextDepth == currentDepth)
				return evaluator;
			else {
				evaluatorsByLevel = getEvaluatorsByLevel(contextDepth);
				if (!evaluatorsByLevel.contains(evaluator))
					evaluatorsByLevel.add(evaluator);
			}
		}
		if (remainsIterator == null || !remainsIterator.hasNext()) {
			currentDepth++;
			evaluatorsByLevel = getEvaluatorsByLevel(currentDepth);
			if (evaluatorsByLevel != null)
				remainsIterator = evaluatorsByLevel.iterator();
		}
		return remainsIterator.next();
	}

	private List<EvaluatorProxy> getEvaluatorsByLevel(int i) {
		List<EvaluatorProxy> evals = evaluatorsByLevel.get(i);
		evals = evals != null ? evals : new ArrayList<EvaluatorProxy>();
		evaluatorsByLevel.put(i, evals);
		return evals;
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"Cannot reomve evaluators at this point.");
	}
}
