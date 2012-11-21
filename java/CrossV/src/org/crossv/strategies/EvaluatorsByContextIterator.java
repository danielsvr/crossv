package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterator implements Iterator<Evaluator> {

	private Iterator<EvaluatorProxy> evaluators;
	private Iterator<EvaluatorProxy> tempEvaluators;
	private Dictionary<Integer, List<EvaluatorProxy>> evaluatorsByLevel;
	private int currentDepth;

	public EvaluatorsByContextIterator(Iterator<EvaluatorProxy> evaluators) {
		this.evaluators = evaluators;
		this.evaluatorsByLevel = new Hashtable<Integer, List<EvaluatorProxy>>();
		this.currentDepth = 1;
	}

	@Override
	public boolean hasNext() {
		return evaluators.hasNext()
				|| (tempEvaluators != null && tempEvaluators.hasNext());
	}

	@Override
	public Evaluator next() {
		if (currentDepth == 1) {
			EvaluatorProxy eval = getEval();
			if (eval != null)
				return eval;
		}
		return getEval2();
	}

	private Evaluator getEval2() {
		if (evaluators.hasNext())
			return getEval();
		if (tempEvaluators == null || !tempEvaluators.hasNext()) {
			currentDepth++;
			List<EvaluatorProxy> evaluatorsByLevel;
			evaluatorsByLevel = getEvaluatorsByLevel(currentDepth);
			if (evaluatorsByLevel != null)
				tempEvaluators = evaluatorsByLevel.iterator();
		}
		return tempEvaluators.next();
	}

	public EvaluatorProxy getEval() {
		List<EvaluatorProxy> evals;
	 while (evaluators.hasNext()){
			EvaluatorProxy evaluator = evaluators.next();

			int contextDepth = evaluator.getContextDepth();
			if (contextDepth == currentDepth) {
				return evaluator;
			} else {
				evals = getEvaluatorsByLevel(contextDepth);
				if (!evals.contains(evaluator))
					evals.add(evaluator);
			}
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
