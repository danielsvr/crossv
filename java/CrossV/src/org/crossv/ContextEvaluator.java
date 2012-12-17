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
public abstract class ContextEvaluator<E, EContext> implements Evaluator {

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
	 * Evaluates an instance of the object {@link Class} on specific instance of
	 * the context class. The object {@link Class} and context {@link Class} was
	 * earlier provided in constructor.
	 * 
	 * @param obj
	 *            the instance of the object {@link Class} that will be
	 *            evaluated.
	 * @param context
	 *            an instance of the context {@link Class} on which the object
	 *            will be evaluated.
	 */
	public abstract Iterable<Evaluation> evaluateInstance(E obj,
			EContext context) throws Exception;

	/**
	 * Evaluates an instance of the object {@link Class} on specific instance of
	 * the context class. The object {@link Class} and context {@link Class} was
	 * earlier provided in constructor.
	 * 
	 * @param obj
	 *            the instance of the object {@link Class} that will be
	 *            evaluated.
	 * @param context
	 *            an instance of the context {@link Class} on which the object
	 *            will be evaluated.
	 */
	public final Iterable<Evaluation> evaluate(Object obj, Object context) {
		EContext actualContext;
		E actualObj = null;

		try {
			if (context == null)
				return Evaluation.fault(new ArgumentNullException("context"));

			if (obj != null)
				actualObj = objClass.cast(obj);
			actualContext = contextClass.cast(context);

			return evaluateInstance(actualObj, actualContext);
		} catch (Throwable e) {
			return Evaluation.fault(e);
		}
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
