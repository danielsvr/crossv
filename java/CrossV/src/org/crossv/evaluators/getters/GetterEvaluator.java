package org.crossv.evaluators.getters;

import static org.crossv.primitives.Iterables.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.crossv.BasicEvaluator;
import org.crossv.Evaluation;
import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.Strings;

public abstract class GetterEvaluator<E> extends BasicEvaluator<E> {
	private final String getterName;
	private Class<?> getterClass;

	public GetterEvaluator(Class<E> objClass, String getterName) {
		super(objClass);
		if (Strings.isNullOrWhitespace(getterName))
			throw new ArgumentNullException("getterName");
		this.getterName = getterName;
	}

	protected Object getValue(E obj) throws ReflectiveOperationException {
		Object result;
		Class<E> instanceClass;
		String message;
		List<String> names;
		Method method;
		Field field;
		boolean isMethod;

		isMethod = false;
		instanceClass = getInstanceClass();
		names = new ArrayList<String>();
		names.add("get" + getterName);
		names.add("is" + getterName);
		names.add(getterName);
		for (String name : names) {
			try {
				method = instanceClass.getMethod(name);
				getterClass = method.getReturnType();
				isMethod = true;
				return invoke(obj, method);

			} catch (NoSuchMethodException e) {
			}
		}

		try {
			field = instanceClass.getField(getterName);
			result = field.get(getInstanceClass());
			getterClass = field.getType();
			return getterClass.cast(result);
		} catch (NoSuchFieldException e) {
		}

		if (isMethod) {
			message = "There is no public, parameterless method that "
					+ "maches get\"{0}\" or \"{0}\".";
			message = MessageFormat.format(message, getterName);
			throw new MalformedMemberException(message);
		}

		message = "There is no public method or field that "
				+ "maches \"get{0}\" or \"{0}\".";
		message = MessageFormat.format(message, getterName);
		throw new NoSuchMemberException(message);

	}

	private Object invoke(E obj, Method method)
			throws ReflectiveOperationException {
		Object result;
		if (obj == null)
			return null;
		result = method.invoke(obj);
		return getterClass.cast(result);
	}

	protected String getGetterName() {
		return getterName;
	}

	@Override
	public final Iterable<Evaluation> evaluateInstance(E obj) throws Exception {
		Object value;
		value = getValue(obj);
		return emptyIfNull(evaluateGetter(obj, value));
	}

	protected abstract Iterable<Evaluation> evaluateGetter(E scopeInstance,
			Object getterValue) throws Exception;

	protected Class<?> getGetterClass() {
		return getterClass;
	}
}
