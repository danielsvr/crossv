package org.crossv.evaluators.getters;

import java.lang.reflect.Array;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Enumeration;

import org.crossv.Evaluation;
import org.crossv.primitives.Iterables;

public class LengthGreaterThan<E> extends GetterEvaluator<E> {
	private int minLength;

	public LengthGreaterThan(Class<E> objClass, String getterName, int minLength) {
		super(objClass, getterName);
		this.minLength = minLength;
	}

	@Override
	protected Iterable<Evaluation> evaluateGetter(E scopeInstance,
			Object getterValue) throws Exception {
		String message;
		Class<?> getterClass;
		boolean fail = false;
		boolean success = false;
		Integer length = null;

		getterClass = getGetterClass();
		if (getterClass.equals(String.class)) {
			String value = (String) getterValue;
			length = value.length();
		} else if (getterClass.isArray()) {
			length = Array.getLength(getterValue);
		} else if (Collection.class.isAssignableFrom(getterClass)) {
			Collection<?> collection = (Collection<?>) getterValue;
			length = collection.size();
		} else if (Enumeration.class.isAssignableFrom(getterClass)) {
			Enumeration<?> collection = (Enumeration<?>) getterValue;
			length = Iterables.count(collection);
		} else if (Iterable.class.isAssignableFrom(getterClass)) {
			Iterable<?> collection = (Iterable<?>) getterValue;
			length = Iterables.count(collection);
		}

		if (length != null) {
			fail = length.intValue() <= minLength;
			success = !fail;
		}

		if (success) {
			message = "Evaluation success. Length is greater that expeced.";
			return Evaluation.success(message);
		} else if (fail) {
			message = "Length is less or equal that expeced.";
			return Evaluation.fault(message);
		}
		message = "Could not apply lenght evaluation on {0}";
		message = MessageFormat.format(message, getterClass.getSimpleName());
		return Evaluation.warning(message);
	}
}
