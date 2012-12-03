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
import org.crossv.Validation;
import org.crossv.Validator;
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

	static RuntimeException exception1 = new RuntimeException("my first bad.");
	static RuntimeException exception2 = new RuntimeException("my second bad.");
	static Evaluation superContext2Success = Evaluation.success("Success");
	static Evaluation extendedContext1Success = Evaluation.success("Success");
	static Evaluation independentContext1Success = Evaluation
			.success("Success");

	BasicEvaluatorRegistry registry;
	Validator validator;
	Validation validation;

	@Before
	public void setup() {
		registry = new BasicEvaluatorRegistry();
		TestableEvaluator evaluator;
		evaluator = new TestableMonkeyEvaluator<SuperContext1>(
				SuperContext1.class);
		evaluator.isThrowing(exception1);
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
		evaluator.isThrowing(exception2);
		registry.register(evaluator);
	}

	@After
	public void unsetup() {
		registry = null;
		validator = null;
		validation = null;
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationIsSuccessful() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_Returns1ValidationResults() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationtHasResultsOfIndependentContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), have(independentContext1Success));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfSuperContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHaveFaultsWith(exception1));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfExtendedContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(extendedContext1Success));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfSuperContext2() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(superContext2Success));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfExtendedContext2() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHaveFaultsWith(exception2));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationIsNotSuccessful() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_Returns2ValidationResults() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.getResults(), hasSize(2));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationHasResultsOfSuperContext2() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.getResults(), have(superContext2Success));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationHasResultsOfExtendedContext2() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.getResults(), haveFaultsWith(exception2));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationDoesntHaveResultsOfSuperContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.getResults(), doesntHaveFaultsWith(exception1));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationDoesntHaveResultsOfExtendedConext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.getResults(), doesntHave(extendedContext1Success));
	}

	@Test
	public void validate_MonkeyOnExtendedConext2_ValidationDoesntHaveResultsOfIndependentContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext2());
		assertThat(validation.getResults(),
				doesntHave(independentContext1Success));
	}

	@Test
	public void validate_MonkeyOnNoContext_ValidationIsNotSuccessful() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void validate_MonkeyOnNoContext_Returns4ValidationResults() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.getResults(), hasSize(4));
	}

	@Test
	public void validate_MonkeyOnNoContext_ValidationHasResultsOfSuperContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.getResults(), haveFaultsWith(exception1));
	}

	@Test
	public void validate_MonkeyOnNoContext_ValidationHasResultsOfSuperContext2() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.getResults(), have(superContext2Success));
	}
	
	@Test
	public void validate_MonkeyOnNoContext_ValidationHasResultsOfIndependentContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.getResults(), have(independentContext1Success));
	}
	
	@Test
	public void validate_MonkeyOnNoContext_ValidationHasResultsOfExtendedContext2() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.getResults(), haveFaultsWith(exception2));
	}
	
	@Test
	public void validate_MonkeyOnNoContext_ValidationDoesntHaveResultsOfExtendedContext1() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.getResults(), have(extendedContext1Success));
	}
	
}
