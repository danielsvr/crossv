package org.crossv;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private Dictionary<Class<?>, List<Evaluator<?, ?>>> objectContextEvaluatorsByEvaluatedClass;
	private Dictionary<Class<?>, Dictionary<Class<?>, List<Evaluator<?, ?>>>> contextTable;

	public Validator(Evaluator<?, ?>... evaluators) {
		contextTable = new Hashtable<Class<?>, Dictionary<Class<?>, List<Evaluator<?, ?>>>>();
		objectContextEvaluatorsByEvaluatedClass = new Hashtable<Class<?>, List<Evaluator<?, ?>>>();

		for (Evaluator<?, ?> eval : evaluators) {
			Class<?> contextClass = eval.getContextClass();
			contextClass = contextClass != null ? contextClass : Object.class;

			if (!contextClass.equals(Object.class)) {
				Dictionary<Class<?>, List<Evaluator<?, ?>>> entry = contextTable
						.get(contextClass);
				entry = entry != null ? entry
						: new Hashtable<Class<?>, List<Evaluator<?, ?>>>();
				contextTable.put(contextClass, entry);

				List<Evaluator<?, ?>> evals = entry
						.get(eval.getInstanceClass());
				evals = evals != null ? evals
						: new ArrayList<Evaluator<?, ?>>();
				entry.put(eval.getInstanceClass(), evals);

				if (!evals.contains(eval))
					evals.add(eval);

			}

			if (contextClass.equals(Object.class)
					|| contextClass.getSuperclass().equals(Object.class)) {
				List<Evaluator<?, ?>> evals = objectContextEvaluatorsByEvaluatedClass
						.get(eval.getInstanceClass());
				evals = evals != null ? evals
						: new ArrayList<Evaluator<?, ?>>();
				objectContextEvaluatorsByEvaluatedClass.put(
						eval.getInstanceClass(), evals);

				if (!evals.contains(eval))
					evals.add(eval);
			}
		}
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, null);
	}

	@SuppressWarnings("unchecked")
	public <E> ValidationResult validate(Class<E> objClass, E obj,
			Object context) {
		context = context != null ? context : new Object();
		Class<?> contextClass = context.getClass();
		List<EvaluationResult> allResults = new ArrayList<EvaluationResult>();

		if (!contextClass.equals(Object.class))
			do {
				Dictionary<Class<?>, List<Evaluator<?, ?>>> entry = contextTable
						.get(contextClass);
				List<Evaluator<?, ?>> all = entry != null ? entry.get(objClass)
						: null;
				for (Evaluator<?, ?> e : Iterables.emptyIfNull(all)) {
					@SuppressWarnings({ "rawtypes" })
					Iterable result = ((Evaluator) e).Evaluate(obj, context);
					Iterables.addAllToList(allResults, result);
				}
				contextClass = contextClass.getSuperclass();
			} while (contextClass != null);
		else {
			List<Evaluator<?, ?>> all = objectContextEvaluatorsByEvaluatedClass
					.get(objClass);
			for (Evaluator<?, ?> e : Iterables.emptyIfNull(all)) {
				@SuppressWarnings({ "rawtypes" })
				Iterable result = ((Evaluator) e).Evaluate(obj, context);
				Iterables.addAllToList(allResults, result);
			}
		}
		return new ValidationResult(allResults);
	}
}
