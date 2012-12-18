package org.crossv.getters;

import static org.crossv.primitives.Iterables.emptyIfNull;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.ArgumentNullException;

public abstract class GetterEvaluator<E> implements Evaluator {
	private Class<?> contextClass;
	private GetterDescriptor<E> getter;

	public GetterEvaluator(GetterDescriptor<E> getter) {
		if (getter == null)
			throw new ArgumentNullException("getter");
		this.getter = getter;
	}

	protected String getGetterName() {
		return getter.getName();
	}

	protected abstract Iterable<Evaluation> evaluateGetter(E scopeInstance,
			Object getterValue) throws Exception;

	@Override
	public final Iterable<Evaluation> evaluate(Object obj, Object context) {
		try {
			Object value;
			E castedObject = getter.castScopeObject(obj);
			value = getter.getValue(castedObject);
			return emptyIfNull(evaluateGetter(castedObject, value));
		} catch (Exception e) {
			return Evaluation.fault(e);
		}
	}

	public final Iterable<Evaluation> evaluate(E obj) {
		return evaluate(obj, null);
	}

	@Override
	public Class<E> getInstanceClass() {
		return getter.getScopeClass();
	}

	@Override
	public Class<?> getContextClass() {
		return contextClass;
	}

	public void setContextClass(Class<?> contextClass) {
		this.contextClass = contextClass;
	}
}
