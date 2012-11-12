package org.crossv;

public abstract class BasicEvaluator<E> extends Evaluator<E, Object> {

	public BasicEvaluator(Class<E> objCalss) {
		super(objCalss, Object.class);
	}

	public final java.lang.Iterable<EvaluationResult> evaluate(E obj,
			Object context) {
		return evaluateInstance(obj);
	}

	public abstract Iterable<EvaluationResult> evaluateInstance(E obj);
}
