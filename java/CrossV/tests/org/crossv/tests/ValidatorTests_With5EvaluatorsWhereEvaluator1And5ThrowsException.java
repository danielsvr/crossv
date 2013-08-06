package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.doesntHaveFaultsWith;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.crossv.tests.helpers.Matchers.have;
import static org.crossv.tests.helpers.Matchers.haveFaultsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluation;
import org.crossv.BasicEvaluatorRegistry;
import org.crossv.EvaluationSuccess;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedContext1;
import org.crossv.tests.subjects.ExtendedContext2;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.SuperContext1;
import org.crossv.tests.subjects.SuperContext2;
import org.crossv.tests.subjects.TestableEvaluator;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_With5EvaluatorsWhereEvaluator1And5ThrowsException {

	static RuntimeException myFirstBadException = new RuntimeException("my first bad.");
	static RuntimeException mySecondBadException = new RuntimeException("my second bad.");
	static Evaluation superContext2Success = new EvaluationSuccess("Success");
	static Evaluation extendedContext1Success = new EvaluationSuccess("Success");
	static Evaluation independentContext1Success = new EvaluationSuccess("Success");

	Validator validator;
	Validation validation;

	@Before
	public void setup() {
		BasicEvaluatorRegistry registry;
		registry = new BasicEvaluatorRegistry();
		TestableEvaluator evaluator;
		evaluator = new TestableMonkeyEvaluator<SuperContext1>(
				SuperContext1.class);
		evaluator.isThrowing(myFirstBadException);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtendedContext1>(
				ExtendedContext1.class);
		evaluator.returns(extendedContext1Success);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<SuperContext2>(
				SuperContext2.class);
		evaluator.returns(superContext2Success);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		evaluator.returns(independentContext1Success);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtendedContext2>(
				ExtendedContext2.class);
		evaluator.isThrowing(mySecondBadException);
		registry.register(evaluator);
		
		validator = new Validator(registry);
	}

	@After
	public void unsetup() {
		validator = null;
		validation = null;
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationIsSuccessful() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_Returns1ValidationResults() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationtHasResultsOfIndependentContext1() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), have(independentContext1Success));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfSuperContext1() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHaveFaultsWith(myFirstBadException));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfExtendedContext1() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(extendedContext1Success));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfSuperContext2() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(superContext2Success));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfExtendedContext2() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHaveFaultsWith(mySecondBadException));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationIsNotSuccessful() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_Returns2ValidationResults() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.getResults(), hasSize(2));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationHasResultsOfSuperContext2() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.getResults(), have(superContext2Success));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationHasResultsOfExtendedContext2() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.getResults(), haveFaultsWith(mySecondBadException));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationDoesntHaveResultsOfSuperContext1() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.getResults(), doesntHaveFaultsWith(myFirstBadException));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationDoesntHaveResultsOfExtendedConext1() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.getResults(), doesntHave(extendedContext1Success));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationDoesntHaveResultsOfIndependentContext1() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext2());
		assertThat(validation.getResults(),
				doesntHave(independentContext1Success));
	}

	@Test
	public void validate_MonkeyOnNoContext_ValidationIsSuccessful() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyOnNoContext_ReturnsNoValidationResults() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), hasSize(0));
	}
}
