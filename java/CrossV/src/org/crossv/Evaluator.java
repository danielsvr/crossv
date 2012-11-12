package org.crossv;

import org.crossv.primitives.ArgumentNullException;

public abstract class Evaluator<E, EContext> {
	private final Class<E> objClass;
	private final Class<EContext> contextClass;

	public Evaluator(Class<E> objCalss, Class<EContext> contextClass) {
		if (objCalss == null)
			throw new ArgumentNullException("objCalss");
		if (contextClass == null)
			throw new ArgumentNullException("contextClass");

		this.objClass = objCalss;
		this.contextClass = contextClass;
	}

	public abstract Iterable<EvaluationResult> evaluate(E obj, EContext context);

	public Class<E> getInstanceClass() {
		return objClass;
	}

	public Class<EContext> getContextClass() {
		return contextClass;
	}
}
