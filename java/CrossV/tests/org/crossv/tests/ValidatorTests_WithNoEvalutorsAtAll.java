package org.crossv.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.crossv.tests.helpers.Matchers.*;

import org.crossv.*;
import org.crossv.tests.helpers.*;
import org.crossv.tests.subjects.*;

import org.junit.*;

public class ValidatorTests_WithNoEvalutorsAtAll {

	Validator validator;

	@Before
	public void setup() {
		validator = TestObjectFactory.createValidator();
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsNoEvaluationResults() {
		Monkey monkey = TestObjectFactory.createMonkey();
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat("returns no evaluation results", validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsASuccessfulValidation() {
		Monkey monkey = TestObjectFactory.createMonkey();
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat("returns a successful validation", validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsNoEvaluationResults() {
		Monkey monkey = TestObjectFactory.createMonkey();
		Object context = TestObjectFactory.createAnyContext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat("returns no evaluation results", validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsASuccessfulValidation() {
		Monkey monkey = TestObjectFactory.createMonkey();
		Object context = TestObjectFactory.createAnyContext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat("returns a successful validation", validation.isSuccessful(), is(true));
	}
}
