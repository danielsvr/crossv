package org.crossv;

import org.crossv.primitives.ArgumentNullException;

public abstract class ContextEvaluator<E, EContext> implements Evaluator {
	
	private final Class<E> objClass;
	private final Class<EContext> contextClass;

	protected ContextEvaluator(Class<E> objCalss, Class<EContext> contextClass) {
		if (objCalss == null)
			throw new ArgumentNullException("objCalss");
		if (contextClass == null)
			throw new ArgumentNullException("contextClass");

		this.objClass = objCalss;
		this.contextClass = contextClass;
	}

	public abstract Iterable<Evaluation> evaluateInstance(E obj,
			EContext context) throws IllegalObjectException;

	@SuppressWarnings("unchecked")
	public final Iterable<Evaluation> evaluate(Object obj, Object context)
			throws IllegalObjectException {
		return evaluateInstance((E) obj, (EContext) context);
	}

	public Class<E> getInstanceClass() {
		return objClass;
	}

	public Class<EContext> getContextClass() {
		return contextClass;
	}
}
