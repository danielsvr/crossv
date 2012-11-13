package org.crossv;

import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private EvaluatorRegistry registry;

	public Validator(ContextEvaluator<?, ?>... evaluators) {
		registry = new EvaluatorRegistry();
		for (ContextEvaluator<?, ?> evaluator : evaluators)
			registry.register(evaluator);
	}

	public Validator(EvaluatorRegistry registry) {
		this.registry = registry;
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, null);
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj,
			Object context) {
		List<EvaluationResult> allResults = new ArrayList<EvaluationResult>();
		Iterable<Evaluator> all;

		all = registry.get(objClass, context != null ? context.getClass()
				: null);

		for (Evaluator e : all) {
			Iterable<EvaluationResult> result = e.evaluate(obj, context);
			Iterables.addAllToList(allResults, result);
		}
		return new ValidationResult(allResults);
	}
}
