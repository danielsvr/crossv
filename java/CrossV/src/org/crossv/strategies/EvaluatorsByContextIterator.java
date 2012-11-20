package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterator implements Iterator<Evaluator> {

	private Iterator<EvaluatorProxy> evaluators;
	private Dictionary<Integer, List<Evaluator>> evaluatorsByLevel;
	private int currentLevel;
	private EvaluatorListener evaluatorListener;

	public EvaluatorsByContextIterator(EvaluatorListener evaluatorListener,
			Iterator<EvaluatorProxy> evaluators) {

		this.evaluatorListener = evaluatorListener;
		this.evaluators = evaluators;
		this.evaluatorsByLevel = new Hashtable<Integer, List<Evaluator>>();
		this.currentLevel = 1;
	}

	@Override
	public boolean hasNext() {
		return evaluators.hasNext();
	}

	@Override
	public Evaluator next() {
		do {
			Evaluator evaluator = evaluators.next();
			Class<?> clazz = evaluator.getContextClass();
			int i = -1;
			while (clazz != null) {
				i++;
				clazz = clazz.getSuperclass();
			}
			if (i == currentLevel) {
				EvaluatorProxy proxy = new EvaluatorProxy(evaluator);
				proxy.addListener(evaluatorListener);
				return proxy;
			} else {
				List<Evaluator> evals = getEvaluatorsByLevel(i);
				evals.add(evaluator);
			}
		} while (evaluators.hasNext());
		return null;
	}

	private List<Evaluator> getEvaluatorsByLevel(int i) {
		List<Evaluator> evals = evaluatorsByLevel.get(i);
		evals = evals != null ? evals : new ArrayList<Evaluator>();
		evaluatorsByLevel.put(Integer.valueOf(i), evals);
		return evals;
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"Cannot reomve evaluators at this point.");
	}
}
