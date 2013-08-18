package org.crossv.strategies;

import static org.crossv.primitives.Iterables.ofClass;

import java.util.Iterator;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.Evaluator;
import org.crossv.ValidationException;

public class ExceptionBasedStrategicIteratorByContext extends
		StrategicIteratorByContext {

	public ExceptionBasedStrategicIteratorByContext(
			Iterator<? extends Evaluator> evaluators) {
		super(evaluators);
	}

	@Override
	public void evaluateMethodCalled(EvaluatorProxy proxy,
			Iterable<Evaluation> result) {
		super.evaluateMethodCalled(proxy, result);

		if (isIterationStopped()) {
			Iterable<Evaluation> evaluations = getEvaluations();
			Iterable<EvaluationFault> faults;
			
			faults = ofClass(EvaluationFault.class, evaluations);
			throw new ValidationException(faults);
		}
	}
}
