package org.crossv.strategies;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluator;

public class EvaluatorsByContextIterator implements Iterator<Evaluator> {

	private Iterable<Evaluator> evaluators;
	private List<Evaluator> arragedEvaluators;

	public EvaluatorsByContextIterator(Iterable<Evaluator> evaluators,
			Class<?> contextClass) {

		this.evaluators = evaluators;
		this.arragedEvaluators = new ArrayList<Evaluator>();

		List<Class<?>> allContexts = new ArrayList<Class<?>>();
		for (Evaluator evaluator : evaluators)
			allContexts.add(evaluator.getContextClass());

		Dictionary<Class<?>, Integer> lvls = new Hashtable<Class<?>, Integer>();
		for (Evaluator evaluator : evaluators) {
			Class<?> context;
			context = evaluator.getContextClass();
			Integer l = lvls.get(context);
			l = l != null ? l : new Integer(0);
			for (Evaluator evl : evaluators) {
				Class<?> otherContext;
				otherContext = evl.getContextClass();
				if (context != otherContext
						&& context.isAssignableFrom(otherContext)) {
					l = new Integer(l.intValue() + 1);
				}
			}
			lvls.put(context, l);
		}
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Evaluator next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		throw new IllegalStateException(
				"Cannot reomve evaluators at this point.");
	}
}
