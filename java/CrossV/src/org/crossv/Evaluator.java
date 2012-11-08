package org.crossv;

public interface Evaluator<E> {
	Iterable<EvaluationResult> Evaluate(Class<E> objClass, E obj);
}
