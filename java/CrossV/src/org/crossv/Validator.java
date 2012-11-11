package org.crossv;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private Dictionary<Class<?>, List<Evaluator<?, ?>>> contextTable;

	public Validator(Evaluator<?, ?>... evaluators) {
		contextTable = new Hashtable<Class<?>, List<Evaluator<?, ?>>>();
		// TODO: populate context downstream and accumulate on Object only the
		// evaluators that are directly inheriting from it
		for (Evaluator<?, ?> eval : evaluators) {
			Class<?> clazz = eval.getContextClass();
			clazz = clazz != null ? clazz : Object.class;
			do {
				List<Evaluator<?, ?>> evals = contextTable.get(clazz);
				evals = evals != null ? evals
						: new ArrayList<Evaluator<?, ?>>();
				if (!evals.contains(eval))
					evals.add(eval);
				contextTable.put(clazz, evals);
				clazz = clazz.getSuperclass();
			} while (clazz != null);
		}
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, null);
	}

	@SuppressWarnings("unchecked")
	public <E> ValidationResult validate(Class<E> objClass, E obj,
			Object context) {
		context = context != null ? context : new Object();
		List<Evaluator<?, ?>> all = contextTable.get(context.getClass());
		List<EvaluationResult> allResults = new ArrayList<EvaluationResult>();
		for (Evaluator<?, ?> e : Iterables.emptyIfNull(all)) {
			if (e.getInstanceClass().equals(objClass)) {
				@SuppressWarnings({ "rawtypes" })
				Iterable result = ((Evaluator) e).Evaluate(obj, context);
				Iterables.addAllToList(allResults, result);
			}
		}
		return new ValidationResult(allResults);
	}

}
