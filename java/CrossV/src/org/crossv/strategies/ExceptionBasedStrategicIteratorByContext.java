package org.crossv.strategies;

import java.util.Iterator;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.ValidationException;

public class ExceptionBasedStrategicIteratorByContext extends StrategicIteratorByContext {

	public ExceptionBasedStrategicIteratorByContext(Iterator<? extends Evaluator> evaluators) {
		super(evaluators);
	}

	@Override
	public void evaluateMethodCalled(EvaluatorProxy proxy,
			Iterable<Evaluation> result) {
		super.evaluateMethodCalled(proxy, result);

		if (isIterationStopped())
			throw new ValidationException(getEvaluations());
	}
}
