package org.crossv.tests;

import static org.junit.Assert.assertThat;
import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.have;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.is;

import org.crossv.Evaluation;
import org.crossv.EvaluatorRegistry;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.SuperContext;
import org.crossv.tests.subjects.TestableEvaluator;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_With3EvaluatorsWhereEvaluator1ReturnsErrors {

	static Evaluation superContextError = Evaluation.error("Error");
	static Evaluation extendedContextSuccess = Evaluation.success("Success");
	static Evaluation independentContext1Success = Evaluation
			.success("Success");

	EvaluatorRegistry registry;
	Validator validator;
	Validation validation;

	@Before
	public void setup() {
		registry = new EvaluatorRegistry();
		TestableEvaluator evaluator;
		evaluator = new TestableMonkeyEvaluator<SuperContext>(
				SuperContext.class);
		evaluator.withRsults(superContextError);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		evaluator.withRsults(extendedContextSuccess);
		registry.register(evaluator);
		evaluator = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		evaluator.withRsults(independentContext1Success);
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
				new ExtendedConext());
		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_Returns1ValidationResults() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedConext());
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationHasResultsOfSuperContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedConext());
		assertThat(validation.getResults(), have(superContextError));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationDoesntHaveResultsOfExtendedContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedConext());
		assertThat(validation.getResults(), doesntHave(extendedContextSuccess));
	}

	@Test
	public void validate_MonkeyOnExtendedConext_ValidationDoesntHaveResultsOfIndependentContext() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey(),
				new ExtendedConext());
		assertThat(validation.getResults(),
				doesntHave(independentContext1Success));
	}
}
