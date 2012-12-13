package org.crossv.strategies;

import org.crossv.Evaluator;

public class ExceptionBasedStrategicIterableByContext extends StrategicIterable {
	public ExceptionBasedStrategicIterableByContext(Iterable<? extends Evaluator> evaluators) {
		super(evaluators);
	}

	@Override
	public StrategicIterator iterator() {
		return new ExceptionBasedStrategicIteratorByContext(evaluators.iterator());
	}
}
