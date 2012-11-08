package org.crossv;

public class Validator {

	public Validator(Evaluator<?>... evaluators) {
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj) {
		return new ValidationResult();
	}

	public <E> ValidationResult validate(Class<E> objClass, E obj,
			Object context) {
		return new ValidationResult();
	}

}
