package org.crossv;

import org.crossv.primitives.Iterables;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = -9175234980914997413L;
	private Validation validation;

	public ValidationException() {
		this(Iterables.<EvaluationFault> empty());
	}

	public ValidationException(Iterable<EvaluationFault> evaluations) {
		this(new Validation(
				Iterables.<EvaluationFault, Evaluation> cast(Iterables
						.emptyIfNull(evaluations))));
	}

	public ValidationException(Validation validation) {
		this.validation = validation != null ? validation : new Validation(
				Iterables.<Evaluation> empty());
	}

	public Iterable<String> getEvaluationMessages() {
		return validation.getMessages();
	}
}
