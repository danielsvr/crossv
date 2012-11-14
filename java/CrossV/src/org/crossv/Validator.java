package org.crossv;

import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.Iterables;

public class Validator {

	private EvaluatorRegistry registry;

	public Validator(Evaluator evaluator) {
		this(evaluator, (Evaluator[]) null);
	}

	public Validator(Evaluator evaluator1, Evaluator... evaluators) {
		registry = new EvaluatorRegistry();
		registry.register(evaluator1);
		
		if (evaluators != null)
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
		List<Evaluation> allResults = new ArrayList<Evaluation>();
		Iterable<Evaluator> allEvaluatorsOfE;

		ctx = ctx != null ? ctx : NoContext.instance;
		allEvaluatorsOfE = registry.get(objClass, ctx.getClass());

		for (Evaluator evaluator : allEvaluatorsOfE) {
			Iterable<Evaluation> result = evaluator.evaluate(obj, ctx);
			Iterables.addAllToList(allResults, result);
		}
		return new Validation(allResults);
	}
}
