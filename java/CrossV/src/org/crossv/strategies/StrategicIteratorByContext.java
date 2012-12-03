package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluator;

public class StrategicIteratorByContext extends StrategicIterator {

	private Iterator<EvaluatorProxy> remainsIterator;
	private Dictionary<Integer, List<EvaluatorProxy>> evaluatorsByLevel;

	public StrategicIteratorByContext(Iterator<? extends Evaluator> evaluators) {
		super(evaluators);
		remainsIterator = null;
		evaluatorsByLevel = new Hashtable<Integer, List<EvaluatorProxy>>();
	}

	@Override
	protected boolean hasNextEvaluator() {
		Iterator<? extends Evaluator> mainIterator;
		mainIterator = getMainIterator();

		return mainIterator.hasNext()
				|| (remainsIterator != null && remainsIterator.hasNext());
	}

	@Override
	protected EvaluatorProxy getNextEvaluator() {
		Iterator<? extends Evaluator> mainIterator;
		Evaluator evaluator;
		EvaluatorProxy proxy;

		mainIterator = getMainIterator();

		List<EvaluatorProxy> evaluatorsByLevel;
		while (mainIterator.hasNext()) {
			evaluator = mainIterator.next();
			proxy = createProxy(evaluator);

			int contextDepth = proxy.getContextDepth();
			if (contextDepth == getIterationDepth())
				return proxy;
			else {
				evaluatorsByLevel = getEvaluatorsByLevel(contextDepth);
				if (!evaluatorsByLevel.contains(proxy))
					evaluatorsByLevel.add(proxy);
			}
		}

		if (remainsIterator == null)
			return null;
		return remainsIterator.next();
	}

	@Override
	protected void ensureIterationDepth() {
		List<EvaluatorProxy> evaluators;
		if (remainsIterator != null && remainsIterator.hasNext())
			return;

		int currentDepth = getIterationDepth();
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
}
