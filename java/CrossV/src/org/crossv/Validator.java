package org.crossv;

import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private EvaluatorRegistry registry;

	public Validator(Evaluator<?, ?>... evaluators) {
		registry = new EvaluatorRegistry();
		for (Evaluator<?, ?> evaluator : evaluators)
			registry.register(evaluator);
	}

	public Validator(EvaluatorRegistry registry) {
		this.registry = registry;
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, null);
	}

	@SuppressWarnings("unchecked")
	public <E> ValidationResult validate(Class<E> objClass, E obj,
			Object context) {
		List<EvaluationResult> allResults = new ArrayList<EvaluationResult>();
		Iterable<Evaluator<E, ?>> all;

		all = registry.get(objClass, context != null ? context.getClass()
				: null);

		for (Evaluator<E, ?> e : all) {
			@SuppressWarnings({ "rawtypes" })
			Iterable result = ((Evaluator) e).evaluate(obj, context);
			Iterables.addAllToList(allResults, result);
		}
		return new ValidationResult(allResults);
	}
}
