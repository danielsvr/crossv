package org.crossv;

public interface Evaluator {
	Iterable<EvaluationResult> evaluate(Object obj, Object context);

	@SuppressWarnings("rawtypes")
	Class getInstanceClass();

	@SuppressWarnings("rawtypes")
	Class getContextClass() ;
}
