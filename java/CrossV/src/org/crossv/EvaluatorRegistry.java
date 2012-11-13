package org.crossv;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.crossv.primitives.Iterables;

public class EvaluatorRegistry {
	private List<Evaluator> allEvaluators;
	private Dictionary<Class<?>, List<Evaluator>> objectContextEvaluatorsByEvaluatedClass;
	private Dictionary<Class<?>, Dictionary<Class<?>, List<Evaluator>>> contextTable;

	public EvaluatorRegistry() {
		allEvaluators = new ArrayList<Evaluator>();
		contextTable = new Hashtable<Class<?>, Dictionary<Class<?>, List<Evaluator>>>();
		objectContextEvaluatorsByEvaluatedClass = new Hashtable<Class<?>, List<Evaluator>>();
	}

	public void register(Evaluator evaluator) {
		Class<?> contextClass = evaluator.getContextClass();
		contextClass = contextClass != null ? contextClass : NoContext.class;

		if (!contextClass.equals(Object.class)) {
			Dictionary<Class<?>, List<Evaluator>> entry = contextTable
					.get(contextClass);
			entry = entry != null ? entry
					: new Hashtable<Class<?>, List<Evaluator>>();
			contextTable.put(contextClass, entry);

			List<Evaluator> evals = entry.get(evaluator.getInstanceClass());
			evals = evals != null ? evals : new ArrayList<Evaluator>();
			entry.put(evaluator.getInstanceClass(), evals);

			if (!evals.contains(evaluator))
				evals.add(evaluator);
		}

		if (contextClass.equals(NoContext.class)
				|| contextClass.equals(Object.class)
				|| contextClass.getSuperclass().equals(Object.class)) {
			List<Evaluator> evals = objectContextEvaluatorsByEvaluatedClass
					.get(evaluator.getInstanceClass());
			evals = evals != null ? evals : new ArrayList<Evaluator>();
			objectContextEvaluatorsByEvaluatedClass.put(
					evaluator.getInstanceClass(), evals);

			if (!evals.contains(evaluator))
				evals.add(evaluator);
		}

		allEvaluators.add(evaluator);
	}

	public <E, EContext> void register(ContextEvaluator<E, EContext> evaluator) {
		register((Evaluator) evaluator);
	}

	public boolean contains(ContextEvaluator<?, ?> evaluator) {
		return allEvaluators.contains(evaluator);
	}

	public <E, EContext> Iterable<Evaluator> get(Class<E> objClass,
			Class<EContext> contextClass) {

		Class<?> ctx = contextClass != null ? contextClass : NoContext.class;
		List<Evaluator> result = new ArrayList<Evaluator>();

		if (!ctx.equals(NoContext.class))
			do {
				Dictionary<Class<?>, List<Evaluator>> entry = contextTable
						.get(ctx);
				List<Evaluator> all = entry != null ? entry.get(objClass)
						: null;
				Iterables.addAllToList(result, all);
				ctx = ctx.getSuperclass();
			} while (ctx != null);
		else {
			result = objectContextEvaluatorsByEvaluatedClass.get(objClass);
		}

		return Iterables.emptyIfNull(result);
	}
}
