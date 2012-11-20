package org.crossv;

/**
 * An evaluator that will evaluate an object in a context and will provide
 * meta-data for a easer manipulation.
 */
public interface Evaluator {

	/**
	 * When implemented, evaluates an instance of the object {@link Class} on
	 * specific instance of the context class.
	 * 
	 * @param obj
	 *            the instance of the object {@link Class} that will be
	 *            evaluated.
	 * @param context
	 *            an instance of the context {@link Class} on which the object
	 *            will be evaluated.
	 * @throws IllegalObjectException
	 *             if the provided instance of the object is not the same
	 *             {@link Class} as configured or if the provided context is not
	 *             the same {@link Class} as configured. 
	 */
	Iterable<Evaluation> evaluate(Object obj, Object context)
			throws IllegalObjectException;

	/**
	 * When implemented, gets the {@link Class} of the object that later will be
	 * evaluated.
	 * 
	 * @return the {@link Class} of the object that later will be evaluated.
	 * */
	Class<?> getInstanceClass();

	/**
	 * When implemented, gets the {@link Class} of the context that will be used
	 * later to evaluate an instance of the object {@link Class}.
	 * 
	 * @return the {@link Class} of the context that will be used later to
	 *         evaluate an instance of the object {@link Class}.
	 * */
	Class<?> getContextClass();
}
