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
		validator = TestObjectFactory.CreateValidator();
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsNoEvaluationResults() {
		Monkey monkey = TestObjectFactory.CreateMonkey();
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsASuccessfulValidation() {
		Monkey monkey = TestObjectFactory.CreateMonkey();
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsNoEvaluationResults() {
		Monkey monkey = TestObjectFactory.CreateMonkey();
		Object context = TestObjectFactory.CreateAnyContext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsASuccessfulValidation() {
		Monkey monkey = TestObjectFactory.CreateMonkey();
		Object context = TestObjectFactory.CreateAnyContext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.isSuccessful(), is(true));
	}
}
