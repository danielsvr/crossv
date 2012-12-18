package org.crossv.getters.descriptors;

import org.crossv.Evaluation;
import org.crossv.getters.GetterEvaluator;

public class LengthGreaterThan<E> extends GetterEvaluator<E> {
	private int minLength;

	public LengthGreaterThan(GetterDescriptor<E> getter, int minLength) {
		super(getter);
		this.minLength = minLength;
	}

	@Override
	protected Iterable<Evaluation> evaluateGetter(E scopeInstance, Object value)
			throws Exception {
		String message;
		int length = (Integer) value;

		if (length > minLength) {
			message = "Evaluation success. Length is greater that expeced.";
			return Evaluation.success(message);
		}

		message = "Length is less or equal that expeced.";
		return Evaluation.fault(message);
	}
}
