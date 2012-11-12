package org.crossv;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private Dictionary<Class<?>, List<Evaluator<?, ?>>> contextTable;

	public Validator(Evaluator<?, ?>... evaluators) {
		contextTable = new Hashtable<Class<?>, List<Evaluator<?, ?>>>();
		for (Evaluator<?, ?> eval : evaluators) {
			Class<?> clazz = eval.getContextClass();
			clazz = clazz != null ? clazz : Object.class;
			List<Evaluator<?, ?>> evals = contextTable.get(clazz);
			evals = evals != null ? evals : new ArrayList<Evaluator<?, ?>>();
			if (!evals.contains(eval))
				evals.add(eval);
			contextTable.put(clazz, evals);
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
				List<Evaluator<?, ?>> all = contextTable.get(contextClass);
				for (Evaluator<?, ?> e : Iterables.emptyIfNull(all)) {
					if (e.getInstanceClass().equals(objClass)) {
						@SuppressWarnings({ "rawtypes" })
						Iterable result = ((Evaluator) e)
								.Evaluate(obj, context);
						Iterables.addAllToList(allResults, result);
					}
				}
				contextClass = contextClass.getSuperclass();
			} while (contextClass != null);
		else {
			Enumeration<Class<?>> keys = contextTable.keys();
			while (keys.hasMoreElements()) {
				Class<?> clazz = keys.nextElement();
				if (clazz.equals(Object.class)
						|| clazz.getSuperclass().equals(Object.class)) {
					List<Evaluator<?, ?>> all = contextTable.get(clazz);
					for (Evaluator<?, ?> e : Iterables.emptyIfNull(all)) {
						if (e.getInstanceClass().equals(objClass)) {
							@SuppressWarnings({ "rawtypes" })
							Iterable result = ((Evaluator) e).Evaluate(obj,
									context);
							Iterables.addAllToList(allResults, result);
						}
					}
				}
			}
		}
		return new ValidationResult(allResults);
	}
}
