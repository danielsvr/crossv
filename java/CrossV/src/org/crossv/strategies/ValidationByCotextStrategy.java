package org.crossv.strategies;

import static org.crossv.primitives.Iterables.any;
import static org.crossv.primitives.Iterables.empty;

import org.crossv.Evaluator;
import org.crossv.primitives.ArgumentNullException;

public final class ValidationByCotextStrategy extends ValidationStrategy {
	@Override
	public Iterable<? extends Evaluator> apply(Iterable<? extends Evaluator> evaluators) {
		if (evaluators == null)
			throw new ArgumentNullException("evaluators");

		if (!any(evaluators))
			return empty();

		return new StrategicIterableByContext(evaluators);
	}
}
