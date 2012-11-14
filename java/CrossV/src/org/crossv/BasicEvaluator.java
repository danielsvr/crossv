package org.crossv;

public abstract class BasicEvaluator<E> extends ContextEvaluator<E, Object> {

	protected BasicEvaluator(Class<E> objCalss) {
		super(objCalss, Object.class);
	}

	
	public final java.lang.Iterable<Evaluation> evaluateInstance(E obj,
			Object context) throws IllegalObjectException {
		return evaluateInstance(obj);
	}

	public abstract Iterable<Evaluation> evaluateInstance(E obj)
			throws IllegalObjectException;
}
