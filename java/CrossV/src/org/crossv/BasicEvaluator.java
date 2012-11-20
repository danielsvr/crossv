package org.crossv;

/**
 * An abstract superclass for easy implementation of a simple {@link Evaluator}
 * for an object in a type-safe manner.<br/>
 * 
 * {@link BasicEvaluator}&lt;E&gt; extends the abstract class
 * {@link ContextEvaluator}&lt;E, NoContext&gt;
 */
public abstract class BasicEvaluator<E> extends ContextEvaluator<E, NoContext> {

	/**
	 * Constructs a new {@link BasicEvaluator} object.
	 * 
	 * @param objCalss
	 *            the {@link Class} of the object that will be evaluated.<br/>
	 *            This {@link Class} serves as meta-data of the evaluator.
	 */
	protected BasicEvaluator(Class<E> objCalss) {
		super(objCalss, NoContext.class);
	}

	/**
	 * Evaluates an instance of the object {@link Class}, {@link Class} that was
	 * earlier provided in constructor.
	 * 
	 * @param obj
	 *            the instance of the object {@link Class} that will be
	 *            evaluated.
	 * @param context
	 *            an instance of the {@link NoContext} class
	 * @throws IllegalObjectException
	 *             if the provided instance is not the same {@link Class} as
	 *             provided in constructor
	 */
	public final Iterable<Evaluation> evaluateInstance(E obj, NoContext context)
			throws IllegalObjectException {
		return evaluateInstance(obj);
	}

	/**
	 * When overridden it evaluates an instance of the object {@link Class},
	 * {@link Class} that was earlier provided in constructor.
	 * 
	 * @param obj
	 *            the instance of the object {@link Class} that will be
	 *            evaluated.
	 * @throws IllegalObjectException
	 *             if the provided instance is not the same {@link Class} as
	 *             provided in constructor
	 */
	public abstract Iterable<Evaluation> evaluateInstance(E obj)
			throws IllegalObjectException;
}
