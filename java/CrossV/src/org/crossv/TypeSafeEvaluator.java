package org.crossv;

import org.crossv.primitives.ArgumentNullException;

public abstract class TypeSafeEvaluator<E, EContext> implements Evaluator {

	public final Iterable<Evaluation> evaluate(Object obj, Object context) {
		EContext actualContext;
		E actualObj = null;
		Class<E> objClass;
		Class<EContext> contextClass;

		try {
			objClass = getInstanceClass();
			contextClass = getContextClass();
			if (context == null)
				return Evaluation.fault(new ArgumentNullException("context"));

			if (obj != null)
				actualObj = objClass.cast(obj);
			actualContext = contextClass.cast(context);

			return evaluateInstance(actualObj, actualContext);
		} catch (Exception e) {
			return Evaluation.fault(e);
		}
	}

	public abstract Iterable<Evaluation> evaluateInstance(E obj,
			EContext context) throws Exception;

	public abstract Class<E> getInstanceClass();

	public abstract Class<EContext> getContextClass();
}