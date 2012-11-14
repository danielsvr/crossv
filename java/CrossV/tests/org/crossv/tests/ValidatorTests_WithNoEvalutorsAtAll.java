package org.crossv.tests;

import static org.crossv.tests.helpers.Assert.assertThat;
import static org.crossv.tests.helpers.Matchers.isEmpty;
import static org.hamcrest.CoreMatchers.is;

import org.crossv.EvaluatorRegistry;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.AnyContext;
import org.crossv.tests.subjects.Monkey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_WithNoEvalutorsAtAll {
	Validator validator;
	Validation validation;
	Monkey monkey;
	EvaluatorRegistry registry;

	@Before
	public void setup() {
		registry = new EvaluatorRegistry();
		monkey = TestObjectFactory.createMonkey();
		validator = TestObjectFactory.createValidator(registry);
	}

	@After
	public void unsetup() {
		registry = null;
		validator = null;
		validation = null;
		monkey = null;
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsNoEvaluationResults() {
		validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithNoContext_ReturnsASuccessfulValidation() {
		validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsNoEvaluationResults() {
		validation = validator.validate(Monkey.class, monkey,
				AnyContext.instance);
		assertThat(validation.getResults(), isEmpty());
	}

	@Test
	public void validate_MonkeyObjectWithAnyContext_ReturnsASuccessfulValidation() {
		validation = validator.validate(Monkey.class, monkey,
				AnyContext.instance);
		assertThat(validation.isSuccessful(), is(true));
	}
}
