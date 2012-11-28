package org.crossv.strategies;

import org.crossv.Evaluator;
import org.crossv.primitives.ArgumentNullException;

public abstract class StrategicIterable implements Iterable<Evaluator> {

	protected final Iterable<? extends Evaluator> evaluators;

	protected StrategicIterable(Iterable<? extends Evaluator> evaluators) {
		if (evaluators == null)
			throw new ArgumentNullException("evaluators");
		this.evaluators = evaluators;
	}

	public abstract StrategicIterator iterator();
}