package org.crossv;

import org.crossv.primitives.Iterables;

public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = -9175234980914997413L;
	private Validation validation;

	public ValidationException() {
		this(Iterables.<Evaluation> empty());
	}

	public ValidationException(Iterable<Evaluation> evaluations) {
		this(new Validation(Iterables.emptyIfNull(evaluations)));
	}

	public ValidationException(Validation validation) {
		this.validation = validation != null ? validation : new Validation(
				Iterables.<Evaluation> empty());
	}
	
	public Iterable<String> getEvaluationMessages(){
		return validation.getMessages();
	}
}
