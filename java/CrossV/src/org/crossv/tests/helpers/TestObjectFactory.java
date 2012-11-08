package org.crossv.tests.helpers;

import java.util.Collections;

import org.crossv.EvaluationResult;
import org.crossv.Evaluator;
import org.crossv.Validator;
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
		Evaluator<Mouse> evaluator = CreateEvaluatorFor(Mouse.class);
		return new Validator(evaluator);
	}

	private static Evaluator<Mouse> CreateEvaluatorFor(Class<Mouse> mouseClass) {
		return new Evaluator<Mouse>() {
			@Override
			public Iterable<EvaluationResult> Evaluate(Class<Mouse> objClass,
					Mouse obj) {
				return Collections.emptyList();
			}
		};
	}

}
