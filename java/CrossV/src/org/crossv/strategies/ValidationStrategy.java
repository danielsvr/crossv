package org.crossv.strategies;

import org.crossv.Evaluator;
import org.crossv.primitives.Function;
import org.crossv.primitives.Iterables;

public abstract class ValidationStrategy {

	public static ValidationStrategy DEFAULT = new ValidationByContextStrategy();

	public final Iterable<Evaluator> apply(Iterable<Evaluator> evaluators) {
		Iterable<Evaluator> selection = Iterables.select(evaluators,
				new Function<Evaluator, Evaluator>() {
					@Override
					public Evaluator eval(Evaluator value) {
						return new EvaluatorProxy(value);
					}
				});
		return applyStrategyOn(Iterables
				.<Evaluator, EvaluatorProxy> cast(selection));
	}

	protected abstract Iterable<Evaluator> applyStrategyOn(
			Iterable<EvaluatorProxy> evaluators);
}
