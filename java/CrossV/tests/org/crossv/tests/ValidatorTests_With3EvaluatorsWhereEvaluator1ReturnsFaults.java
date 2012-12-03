package org.crossv.tests;

import static org.junit.Assert.assertThat;
import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.have;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import org.crossv.Evaluation;
import org.crossv.BasicEvaluatorRegistry;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.tests.subjects.ExtendedContext1;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.SuperContext1;
import org.crossv.tests.subjects.TestableEvaluator;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_With3EvaluatorsWhereEvaluator1ReturnsFaults {

	static Evaluation superContextError = Evaluation.fault("Error");
	static Evaluation extendedContextSuccess = Evaluation.success("Success");
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
		evaluator.returns(superContextError);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtendedContext1>(
				ExtendedContext1.class);
		evaluator.returns(extendedContextSuccess);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		evaluator.returns(independentContext1Success);
		registry.register(evaluator);
	}

	@After
	public void unsetup() {
		registry = null;
		validator = null;
		validation = null;
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationIsNotSuccessful() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext1());
		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_Returns1ValidationResults() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext1());
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationHasResultsOfSuperContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext1());
		assertThat(validation.getResults(), have(superContextError));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationDoesntHaveResultsOfExtendedContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext1());
		assertThat(validation.getResults(), doesntHave(extendedContextSuccess));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationDoesntHaveResultsOfIndependentContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedContext1());
		assertThat(validation.getResults(),
				doesntHave(independentContext1Success));
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
	public void validate_MonkeyOnIndependentContext1_ValidationHasResultsOfSuperContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(superContextError));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfExtendedContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(extendedContextSuccess));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfIndependentContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new IndependentContext1());
		assertThat(validation.getResults(), have(independentContext1Success));
	}
}
