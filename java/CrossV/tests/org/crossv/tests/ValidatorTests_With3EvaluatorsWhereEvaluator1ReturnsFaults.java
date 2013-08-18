package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.crossv.tests.helpers.Matchers.have;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.BasicEvaluatorRegistry;
import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.EvaluationSuccess;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.tests.helpers.TestObjectFactory;
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
	static Evaluation superContextError = new EvaluationFault("Error");
	static Evaluation extendedContextSuccess = new EvaluationSuccess("Success");
	static Evaluation independentContext1Success = new EvaluationSuccess(
			"Success");

	Validator validator;
	Validation validation;

	@Before
	public void setup() {
		BasicEvaluatorRegistry registry;
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

		validator = TestObjectFactory.createValidator(registry);
	}

	@After
	public void unsetup() {
		validator = null;
		validation = null;
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationIsNotSuccessful() {
		Monkey moneky = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, moneky,
				new ExtendedContext1());
		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_Returns1ValidationResults() {
		Monkey monkey = TestObjectFactory.createMonkey();
		
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext1());
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationHasResultsOfSuperContext() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext1());
		assertThat(validation.getResults(), have(superContextError));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationDoesntHaveResultsOfExtendedContext() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class,monkey,
				new ExtendedContext1());
		assertThat(validation.getResults(), doesntHave(extendedContextSuccess));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationDoesntHaveResultsOfIndependentContext() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new ExtendedContext1());
		assertThat(validation.getResults(),
				doesntHave(independentContext1Success));
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
	public void validate_MonkeyOnIndependentContext1_ValidationHasResultsOfSuperContext() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(superContextError));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfExtendedContext() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), doesntHave(extendedContextSuccess));
	}

	@Test
	public void validate_MonkeyOnIndependentContext1_ValidationDoesntHaveResultsOfIndependentContext() {
		Monkey monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey,
				new IndependentContext1());
		assertThat(validation.getResults(), have(independentContext1Success));
	}
}