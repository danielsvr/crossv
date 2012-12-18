package org.crossv;

/**
 * An abstract superclass for easy implementation of a simple {@link Evaluator}
 * for an object in a type-safe manner.<br/>
 * 
 * {@link BasicEvaluator}&lt;E&gt; extends the abstract class
 * {@link ContextEvaluator}&lt;E, NoContext&gt;
 * 
 * @author yochanan.miykael
 */
public abstract class BasicEvaluator<E> extends TypeSafeEvaluator<E, NoContext> {

	private Class<E> objCalss;

	/**
	 * Constructs a new {@link BasicEvaluator} object.
	 * 
	 * @param objCalss
	 *            the {@link Class} of the object that will be evaluated.<br/>
	 *            This {@link Class} serves as meta-data of the evaluator.
	 */
	protected BasicEvaluator(Class<E> objCalss) {
		this.objCalss = objCalss;
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
	 */
	@Override
	public final Iterable<Evaluation> evaluateInstance(E obj, NoContext context)
			throws Exception {
		return evaluateInstance(obj);
	}

	public final Iterable<Evaluation> evaluate(E obj) {
		return evaluate(obj, NoContext.instance);
	}

	/**
	 * When overridden it evaluates an instance of the object {@link Class},
	 * {@link Class} that was earlier provided in constructor.
	 * 
	 * @param obj
	 *            the instance of the object {@link Class} that will be
	 *            evaluated.
	 */
	public abstract Iterable<Evaluation> evaluateInstance(E obj)
			throws Exception;

	@Override
	public Class<NoContext> getContextClass() {
		return NoContext.class;
	}

	@Override
	public Class<E> getInstanceClass() {
		return objCalss;
	}
}
