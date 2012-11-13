package org.crossv;

@SuppressWarnings("rawtypes")
public interface Evaluator {
	Iterable<EvaluationResult> evaluate(Object obj, Object context) throws IllegalObjectException;

	Class getInstanceClass();

	Class getContextClass();
}
