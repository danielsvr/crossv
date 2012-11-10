package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.*;
import static org.junit.Assert.*;

import org.crossv.EvaluationResult;
import org.crossv.Evaluator;
import org.crossv.ValidationResult;
import org.crossv.Validator;
import org.crossv.tests.subjects.FirstSubLevelContext1;
import org.crossv.tests.subjects.FirstSubLevelContext2;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.crossv.tests.subjects.TopLevelContext;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_With4NonStrictEvaluatorsForValidatedClass {

	private Validator validator;
	private TestableMonkeyEvaluator<TopLevelContext> evaluator1;
	private TestableMonkeyEvaluator<FirstSubLevelContext1> evaluator2;

	@Before
	public void setup() {
		evaluator1 = new TestableMonkeyEvaluator<TopLevelContext>(
				TopLevelContext.class);
		evaluator2 = new TestableMonkeyEvaluator<FirstSubLevelContext1>(
				FirstSubLevelContext1.class);
		Evaluator<Monkey, ?> eval3 = new TestableMonkeyEvaluator<FirstSubLevelContext2>(
				FirstSubLevelContext2.class);
		Evaluator<Monkey, ?> eval4 = new TestableMonkeyEvaluator<FirstSubLevelContext2>(
				FirstSubLevelContext2.class);

		validator = new Validator(evaluator1, evaluator2, eval3, eval4);
	}

	@Test
	public void validate_MonkeyObjectOnFirstSubLevelContext1_Returns2EvaluationResults() {
		Monkey monkey = new Monkey();
		Object context = new FirstSubLevelContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat("it returns 2 evaluation results", validation.getResults(), hasSize(2));
	}

	@Test
	public void validate_MonkeyObjectOnFirstSubLevelContext1_ReturnsResultsOfEvaluator1() {
		Monkey monkey = new Monkey();
		Object context = new FirstSubLevelContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat("returns results of evaluator1", validation.getResults(), hasAll(evaluator1.results));
	}

	@Test
	public void validate_MonkeyObjectOnFirstSubLevelContext1_ReturnsResultsOfEvaluator2() {
		Monkey monkey = new Monkey();
		Object context = new FirstSubLevelContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat("returns results of evaluator1", validation.getResults(), hasAll(evaluator2.results));
	}

}