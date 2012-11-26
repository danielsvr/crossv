package org.crossv.strategies;

import org.crossv.Evaluator;
import org.crossv.primitives.ArgumentNullException;

public abstract class StrategicIterable implements Iterable<Evaluator> {

	protected final Iterable<Evaluator> evaluators;

	protected StrategicIterable(Iterable<Evaluator> evaluators) {
		if (evaluators == null)
			throw new ArgumentNullException("evaluators");
		this.evaluators = evaluators;
	}

	public abstract StrategicIterator iterator();
}