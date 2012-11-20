package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterator implements Iterator<Evaluator> {

	private Iterator<EvaluatorProxy> evaluators;
	private Dictionary<Integer, List<EvaluatorProxy>> evaluatorsByLevel;
	private int currentLevel;
	private boolean allEvaluatorsIterated;

	public EvaluatorsByContextIterator(Iterator<EvaluatorProxy> evaluators) {
		this.evaluators = evaluators;
		this.evaluatorsByLevel = new Hashtable<Integer, List<EvaluatorProxy>>();
		this.currentLevel = 1;
	}

	@Override
	public boolean hasNext() {
		return evaluators.hasNext() || !evaluatorsByLevel.isEmpty();
	}

	@Override
	public Evaluator next() {
		List<EvaluatorProxy> evals;
		if (!allEvaluatorsIterated) {
			do {
				EvaluatorProxy evaluator = evaluators.next();
				Class<?> clazz = evaluator.getContextClass();
				int i = -1;
				while (clazz != null) {
					i++;
					clazz = clazz.getSuperclass();
				}
				if (i == currentLevel) {
					return evaluator;
				} else {
					evals = getEvaluatorsByLevel(i);
					evals.add(evaluator);
				}
			} while (evaluators.hasNext());
			allEvaluatorsIterated = true;
		} else {
			do {
				currentLevel++;
				evals = getEvaluatorsByLevel(currentLevel);
				EvaluatorProxy eval = evals.get(0);
				evals.remove(0);
				return eval;
			} while (evals != null && evals.isEmpty());
		}
		return null;
	}

	private List<EvaluatorProxy> getEvaluatorsByLevel(int i) {
		List<EvaluatorProxy> evals = evaluatorsByLevel.get(i);
		evals = evals != null ? evals : new ArrayList<EvaluatorProxy>();
		evaluatorsByLevel.put(Integer.valueOf(i), evals);
		return evals;
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"Cannot reomve evaluators at this point.");
	}
}
