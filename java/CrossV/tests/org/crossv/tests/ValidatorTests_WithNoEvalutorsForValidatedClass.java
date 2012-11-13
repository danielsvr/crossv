package org.crossv.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.crossv.tests.helpers.Matchers.*;

import org.crossv.*;
import org.crossv.tests.helpers.*;
import org.crossv.tests.subjects.*;

import org.junit.*;

public class ValidatorTests_WithNoEvalutorsForValidatedClass {

	Validator validator;
	Validation validation;
	Monkey monkey;
	Object context;

	@Before
	public void setup() {
		validator = TestObjectFactory
				.createValidatorWithAEvaluatorForMouseClass();
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsNoEvaluationResults() {
		monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsASuccessfulValidation() {
		monkey = TestObjectFactory.createMonkey();
		validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsNoEvaluationResults() {
		monkey = TestObjectFactory.createMonkey();
		context = TestObjectFactory.createAnyContext();
		validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsASuccessfulValidation() {
		monkey = TestObjectFactory.createMonkey();
		context = TestObjectFactory.createAnyContext();
		validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.isSuccessful(), is(true));
	}
}
