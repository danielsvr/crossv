package org.crossv.tests.helpers;

import org.crossv.EvaluationResult;
import org.crossv.Evaluator;
import org.crossv.Validator;
import org.crossv.primitives.Iterables;
import org.crossv.tests.subjects.AnyContext;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.Mouse;

public class TestObjectFactory {

	public static Monkey CreateMonkey() {
		return new Monkey();
	}

	public static Object CreateAnyContext() {
		return new AnyContext();
	}

	public static Validator CreateValidator() {
		return new Validator();
	}

	public static Validator CreateValidatorWithAEvaluatorForMouseClass() {
		Evaluator<Mouse, Object> evaluator = new Evaluator<Mouse, Object>(
				Mouse.class, Object.class) {

			@Override
			public Iterable<EvaluationResult> Evaluate(Mouse obj, Object context) {
				return Iterables.empty();
			}
		};
		return new Validator(evaluator);
	}

}
