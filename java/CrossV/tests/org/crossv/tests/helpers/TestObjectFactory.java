package org.crossv.tests.helpers;

import org.crossv.BasicEvaluator;
import org.crossv.EvaluationResult;
import org.crossv.ContextEvaluator;
import org.crossv.Validator;
import org.crossv.primitives.Iterables;
import org.crossv.tests.subjects.AnyContext;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.crossv.tests.subjects.TestableMouseEvaluator;

public class TestObjectFactory {

	public static Monkey createMonkey() {
		return new Monkey();
	}

	public static Object createAnyContext() {
		return new AnyContext();
	}

	public static Validator createValidator() {
		return new Validator();
	}

	public static Validator createValidatorWithAEvaluatorForMouseClass() {
		BasicEvaluator<Mouse> evaluator = new BasicEvaluator<Mouse>(Mouse.class) {
			@Override
			public Iterable<EvaluationResult> evaluateInstance(Mouse obj) {
				return Iterables.empty();
			}
		};
		return new Validator(evaluator);
	}

	public static <E> TestableMonkeyEvaluator<E> createMonkeyEvaluator(
			Class<E> clazz, String ruleName) {
		TestableMonkeyEvaluator<E> eval = new TestableMonkeyEvaluator<E>(clazz);
		eval.result = new EvaluationResult(ruleName);
		return eval;
	}

	public static <E> TestableMouseEvaluator<E> createMouseEvaluator(
			Class<E> clazz, String ruleName) {
		TestableMouseEvaluator<E> eval = new TestableMouseEvaluator<E>(clazz);
		eval.result = new EvaluationResult(ruleName);
		return eval;
	}

	public static Validator createValidator(
			ContextEvaluator<?, ?>... evaluators) {
		return new Validator(evaluators);
	}
}
