package org.crossv.tests.helpers;

import org.crossv.BasicEvaluator;
import org.crossv.Evaluation;
import org.crossv.EvaluatorRegistry;
import org.crossv.Validator;
import org.crossv.primitives.Iterables;
import org.crossv.strategies.ValidationByCotextStrategy;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.crossv.tests.subjects.TestableMouseEvaluator;

public class TestObjectFactory {

	public static Monkey createMonkey() {
		return new Monkey();
	}

	public static Validator createValidatorWithAEvaluatorForMouseClass() {
		BasicEvaluator<Mouse> evaluator = new BasicEvaluator<Mouse>(Mouse.class) {
			@Override
			public Iterable<Evaluation> evaluateInstance(Mouse obj) {
				return Iterables.empty();
			}
		};
		return new Validator(evaluator);
	}

	public static <E> TestableMonkeyEvaluator<E> createMonkeyEvaluator(
			Class<E> clazz, String ruleName) {
		TestableMonkeyEvaluator<E> eval = createMonkeyEvaluator(clazz);
		eval.withRsults(Evaluation.success(ruleName));
		return eval;
	}

	public static <E> TestableMonkeyEvaluator<E> createMonkeyEvaluator(
			Class<E> clazz) {
		TestableMonkeyEvaluator<E> eval = new TestableMonkeyEvaluator<E>(clazz);
		return eval;
	}

	public static <E> TestableMouseEvaluator<E> createMouseEvaluator(
			Class<E> clazz, String ruleName) {
		TestableMouseEvaluator<E> eval = new TestableMouseEvaluator<E>(clazz);
		eval.result = Evaluation.success(ruleName);
		return eval;
	}

	public static Validator createValidator(EvaluatorRegistry registry) {
		return new Validator(registry);
	}

	public static EvaluatorRegistry createEvaluatorRegistry() {
		return new EvaluatorRegistry();
	}

	public static ValidationByCotextStrategy createValidationByCotextStrategy() {
		return new ValidationByCotextStrategy();
	}

}
