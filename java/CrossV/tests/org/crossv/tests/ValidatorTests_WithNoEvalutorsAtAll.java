package org.crossv.tests;

import static org.crossv.tests.helpers.Assert.assertThat;
import static org.crossv.tests.helpers.Matchers.isEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import org.crossv.Evaluator;
import org.crossv.EvaluatorRegistry;
import org.crossv.NoContext;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.primitives.Iterables;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.AnyContext;
import org.crossv.tests.subjects.Monkey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ValidatorTests_WithNoEvalutorsAtAll {

	Validator validator;
	Validation validation;
	Monkey monkey;

	@Mock
	EvaluatorRegistry registry;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(registry.get(Monkey.class, AnyContext.class)).thenReturn(
				Iterables.<Evaluator> empty());
		when(registry.get(Monkey.class, NoContext.class)).thenReturn(
				Iterables.<Evaluator> empty());

		monkey = TestObjectFactory.createMonkey();
		validator = new Validator(registry);
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
