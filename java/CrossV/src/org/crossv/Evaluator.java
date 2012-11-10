package org.crossv;

public abstract class Evaluator<E, EContext> {
	private final Class<E> objClass;
	private final Class<EContext> contextClass;

	public Evaluator(Class<E> objCalss, Class<EContext> contextClass) {
		this.objClass = objCalss;
		this.contextClass = contextClass;
	}

	public abstract Iterable<EvaluationResult> Evaluate(E obj, EContext context);

	public Class<E> getInstanceClass() {
		return objClass;
	}

	public Class<EContext> getContextClass() {
		return contextClass;
	}
}
