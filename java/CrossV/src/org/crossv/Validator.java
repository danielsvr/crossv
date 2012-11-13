package org.crossv;

import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private EvaluatorRegistry registry;

	public Validator(Evaluator... evaluators) {
		registry = new EvaluatorRegistry();
		for (Evaluator evaluator : evaluators)
			registry.register(evaluator);
	}

	public Validator(EvaluatorRegistry registry) {
		this.registry = registry;
	}

	public <E> Validation validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, NoContext.instance);
	}

	public <E> Validation validate(Class<E> objClass, E obj, Object ctx) {
		List<EvaluationResult> allResults = new ArrayList<EvaluationResult>();
		Iterable<Evaluator> allEvaluatorsOfE;

		ctx = ctx != null ? ctx : NoContext.instance;
		allEvaluatorsOfE = registry.get(objClass, ctx.getClass());

		for (Evaluator evaluator : allEvaluatorsOfE) {
			Iterable<EvaluationResult> result = evaluator.evaluate(obj, ctx);
			Iterables.addAllToList(allResults, result);
		}
		return new Validation(allResults);
	}
}
