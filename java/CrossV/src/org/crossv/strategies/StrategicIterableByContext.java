package org.crossv.strategies;

import org.crossv.Evaluator;

public class StrategicIterableByContext extends StrategicIterable {

	public StrategicIterableByContext(Iterable<? extends Evaluator> evaluators) {
		super(evaluators);
	}

	@Override
	public StrategicIterator iterator() {
		return new StrategicIteratorByContext(evaluators.iterator());
	}
	
}
