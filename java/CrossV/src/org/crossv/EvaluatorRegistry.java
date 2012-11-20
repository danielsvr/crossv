package org.crossv;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.crossv.primitives.Iterables;

public final class EvaluatorRegistry {
	private List<Evaluator> allEvaluators;
	private Dictionary<Class<?>, List<Evaluator>> noContextEvaluatorsByEvaluatedClass;
	private Dictionary<Class<?>, Dictionary<Class<?>, List<Evaluator>>> contextTable;

	public EvaluatorRegistry() {
		this(null);
	}

	public EvaluatorRegistry(Evaluator evaluator) {
		this(evaluator, (Evaluator[]) null);
	}

	public EvaluatorRegistry(Evaluator evaluator1, Evaluator... evaluators) {
		allEvaluators = new ArrayList<Evaluator>();
		contextTable = new Hashtable<Class<?>, Dictionary<Class<?>, List<Evaluator>>>();
		noContextEvaluatorsByEvaluatedClass = new Hashtable<Class<?>, List<Evaluator>>();

		if (evaluator1 != null)
			register(evaluator1);

		if (evaluators == null)
			return;
		for (Evaluator evaluator : evaluators)
			register(evaluator);
	}

	public void register(Evaluator evaluator) {
		Class<?> contextClass = evaluator.getContextClass();
		contextClass = contextClass != null ? contextClass : NoContext.class;
		List<Evaluator> evals;
		Class<?> instanceClass = evaluator.getInstanceClass();

		if (!contextClass.equals(NoContext.class)) {
			Dictionary<Class<?>, List<Evaluator>> entry;

			entry = contextTable.get(contextClass);
			entry = entry != null ? entry
					: new Hashtable<Class<?>, List<Evaluator>>();

			contextTable.put(contextClass, entry);

			evals = entry.get(instanceClass);
			evals = evals != null ? evals : new ArrayList<Evaluator>();

			entry.put(instanceClass, evals);

			if (!evals.contains(evaluator))
				evals.add(evaluator);
		}

		if (contextClass.equals(NoContext.class)
				|| contextClass.equals(Object.class)
				|| contextClass.getSuperclass().equals(Object.class)) {

			evals = noContextEvaluatorsByEvaluatedClass.get(instanceClass);
			evals = evals != null ? evals : new ArrayList<Evaluator>();
			noContextEvaluatorsByEvaluatedClass.put(instanceClass, evals);

			if (!evals.contains(evaluator))
				evals.add(evaluator);
		}

		allEvaluators.add(evaluator);
	}

	public <E, EContext> void register(ContextEvaluator<E, EContext> evaluator) {
		register((Evaluator) evaluator);
	}

	public boolean contains(Evaluator evaluator) {
		return allEvaluators.contains(evaluator);
	}

	public <E, EContext> Iterable<Evaluator> get(Class<E> objClass,
			Class<EContext> contextClass) {

		List<Evaluator> result = new ArrayList<Evaluator>();
		Class<?> actualContextClass;
		Dictionary<Class<?>, List<Evaluator>> entry;
		List<Evaluator> all;

		actualContextClass = contextClass != null ? contextClass
				: NoContext.class;

		if (!actualContextClass.equals(NoContext.class))
			do {
				entry = contextTable.get(actualContextClass);
				all = entry != null ? entry.get(objClass) : null;

				Iterables.addAllToList(result, all);

				actualContextClass = actualContextClass.getSuperclass();
			} while (actualContextClass != null);
		else {
			result = noContextEvaluatorsByEvaluatedClass.get(objClass);
		}

		return Iterables.emptyIfNull(result);
	}
}
