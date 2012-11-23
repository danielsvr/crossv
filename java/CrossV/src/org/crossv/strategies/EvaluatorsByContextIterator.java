package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.crossv.Evaluator;
import org.crossv.primitives.IteratorAdapter;
import org.crossv.primitives.IteratorCancelationSource;
import org.crossv.primitives.NeverCancel;

public class EvaluatorsByContextIterator extends IteratorAdapter<Evaluator> {

	private Iterator<EvaluatorProxy> mainIterator;
	private Iterator<EvaluatorProxy> remainsIterator;
	private Dictionary<Integer, List<EvaluatorProxy>> evaluatorsByLevel;
	private int currentDepth;
	private IteratorCancelationSource cancelationSource;
	private final Iterable<EvaluatorProxy> evaluators;

	public EvaluatorsByContextIterator(Iterable<EvaluatorProxy> evaluators) {
		this(evaluators, NeverCancel.instance);
	}

	public EvaluatorsByContextIterator(Iterable<EvaluatorProxy> evaluators,
			IteratorCancelationSource cancelationSource) {
		this.evaluators = evaluators;
		this.mainIterator = evaluators.iterator();
		this.cancelationSource = cancelationSource;
		this.evaluatorsByLevel = new Hashtable<Integer, List<EvaluatorProxy>>();
		this.currentDepth = 1;
	}

	@Override
	public boolean hasNext() {
		if (cancelationSource.isCanceled())
			return false;

		if (!mainIterator.hasNext())
			ensureRemainsIterator();

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
		ensureRemainsIterator();
		if (remainsIterator == null)
			throw new NoSuchElementException(
					"There are no more elements to iterate.");
		return remainsIterator.next();
	}

	public void ensureRemainsIterator() {
		List<EvaluatorProxy> evaluators;
		if (remainsIterator != null && remainsIterator.hasNext())
			return;

		currentDepth++;
		evaluators = getEvaluatorsByLevel(currentDepth);
		if (evaluators != null)
			remainsIterator = evaluators.iterator();
	}

	private List<EvaluatorProxy> getEvaluatorsByLevel(int i) {
		List<EvaluatorProxy> evals = evaluatorsByLevel.get(i);
		evals = evals != null ? evals : new ArrayList<EvaluatorProxy>();
		evaluatorsByLevel.put(i, evals);
		return evals;
	}

	@Override
	public void reset() {
		mainIterator = evaluators.iterator();
		remainsIterator = null;
		evaluatorsByLevel = new Hashtable<Integer, List<EvaluatorProxy>>();
		currentDepth = 1;
	}
}
