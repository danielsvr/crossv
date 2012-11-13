package org.crossv;

public abstract class BasicEvaluator<E> extends ContextEvaluator<E, Object> {

	public BasicEvaluator(Class<E> objCalss) {
		super(objCalss, Object.class);
	}

	
	public final java.lang.Iterable<EvaluationResult> evaluateInstance(E obj,
			Object context) throws IllegalObjectException {
		return evaluateInstance(obj);
	}

	public abstract Iterable<EvaluationResult> evaluateInstance(E obj)
			throws IllegalObjectException;
}
