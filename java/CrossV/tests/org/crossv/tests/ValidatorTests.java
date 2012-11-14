package org.crossv.tests;

import static org.crossv.tests.helpers.Assert.assertThat;
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
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ValidatorTests {

	EvaluatorRegistry registry;
	TestableEvaluator errorEvaluator;
	TestableEvaluator extendedEvaluator;
	TestableEvaluator independentEvaluator;
	Validator validator;
	Validation validation;

	@Before
	public void setup() {
		registry = new EvaluatorRegistry();
		errorEvaluator = new TestableMonkeyEvaluator<SuperContext>(
				SuperContext.class);
		errorEvaluator.returns(Evaluation.error("Error"));
		registry.register(errorEvaluator);
		extendedEvaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		extendedEvaluator.returns(Evaluation.success("Success"));
		registry.register(extendedEvaluator);
		independentEvaluator = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		independentEvaluator.returns(Evaluation.success("Success"));
		registry.register(independentEvaluator);
	}

	@Test 
	@Ignore
	public void validate_Monkey() {
		validator = new Validator(registry);
		validation = validator.validate(Monkey.class, new Monkey());
		assertThat(validation.isSuccessful(), is(false));
	}
}
