package org.crossv;

import org.crossv.primitives.ArgumentNullException;

/**
 * An abstract superclass for easy implementation of a contextual
 * {@link Evaluator} for an object in a type-safe manner.<br/>
 * 
 * {@link ContextEvaluator}&lt;E, NoContext&gt; is an implementation of
 * {@link Evaluator}
 * 
 * @author yochanan.miykael
 */
public abstract class ContextEvaluator<E, EContext> extends
		TypeSafeEvaluator<E, EContext> {

	private final Class<E> objClass;
	private final Class<EContext> contextClass;

	/**
	 * Constructs a new {@link ContextEvaluator} object.
	 * 
	 * @param objCalss
	 *            the {@link Class} of the object that will be evaluated.<br/>
	 *            This {@link Class} serves as meta-data of the evaluator.
	 * @param contextClas
	 *            the {@link Class} of the context on which the evaluated object
	 *            will be evaluated.<br/>
	 *            This {@link Class} serves as meta-data of the evaluator.
	 */
	protected ContextEvaluator(Class<E> objCalss, Class<EContext> contextClass) {
		if (objCalss == null)
			throw new ArgumentNullException("objCalss");
		if (contextClass == null)
			throw new ArgumentNullException("contextClass");

		this.objClass = objCalss;
		this.contextClass = contextClass;
	}

	/**
	 * Gets the {@link Class} of the object that later will be evaluated.
	 * 
	 * @return the {@link Class} of the object that later will be evaluated.
	 * */
	public Class<E> getInstanceClass() {
		return objClass;
	}

	/**
	 * Gets the {@link Class} of the context that will be used later to evaluate
	 * an instance of the object {@link Class}.
	 * 
	 * @return the {@link Class} of the context that will be used later to
	 *         evaluate an instance of the object {@link Class}.
	 * */
	public Class<EContext> getContextClass() {
		return contextClass;
	}
}
