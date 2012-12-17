package org.crossv.tests.helpers;

import org.crossv.BasicEvaluator;
import org.crossv.BasicEvaluatorRegistry;
import org.crossv.Evaluation;
import org.crossv.NoContext;
import org.crossv.Validator;
import org.crossv.evaluators.getters.GetterEvaluator;
import org.crossv.evaluators.getters.LengthGreaterThan;
import org.crossv.primitives.Iterables;
import org.crossv.strategies.ExceptionBasedValidationByCotextStrategy;
import org.crossv.strategies.ValidationByCotextStrategy;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.TestableGetterEvaluator;
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
		eval.returns(Evaluation.success(ruleName));
		return eval;
	}

	public static <E> TestableMonkeyEvaluator<E> createMonkeyEvaluator(
			Class<E> clazz) {
		TestableMonkeyEvaluator<E> eval = new TestableMonkeyEvaluator<E>(clazz);
		return eval;
	}

	public static TestableMonkeyEvaluator<NoContext> createMonkeyEvaluator() {
		TestableMonkeyEvaluator<NoContext> eval = new TestableMonkeyEvaluator<NoContext>(
				NoContext.class);
		return eval;
	}

	public static <E> TestableMouseEvaluator<E> createMouseEvaluator(
			Class<E> clazz, String ruleName) {
		TestableMouseEvaluator<E> eval = createMouseEvaluator(clazz);
		eval.results = Evaluation.success(ruleName);
		return eval;
	}

	public static Validator createValidator(BasicEvaluatorRegistry registry) {
		return new Validator(registry);
	}

	public static BasicEvaluatorRegistry createEvaluatorRegistry() {
		return new BasicEvaluatorRegistry();
	}

	public static ValidationByCotextStrategy createValidationByCotextStrategy() {
		return new ValidationByCotextStrategy();
	}

	public static <E> TestableMouseEvaluator<E> createMouseEvaluator(
			Class<E> clazz) {
		return new TestableMouseEvaluator<E>(clazz);
	}

	public static ExceptionBasedValidationByCotextStrategy createExceptionBasedValidationByCotextStrategy() {
		return new ExceptionBasedValidationByCotextStrategy();
	}

	public static <E> GetterEvaluator<E> createTestableGetterEvaluator(
			Class<E> objClass, String getterName) {
		return new TestableGetterEvaluator<E>(objClass, getterName);
	}

	public static <E> LengthGreaterThan<E> createGetterLengthGtEvaluator(
			Class<E> objClass, String getterName, int length) {
		return new LengthGreaterThan<E>(objClass, getterName, length);
	}

}
