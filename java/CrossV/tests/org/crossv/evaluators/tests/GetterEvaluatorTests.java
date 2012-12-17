package org.crossv.evaluators.tests;

import static org.crossv.tests.helpers.Matchers.isEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.evaluators.getters.GetterEvaluator;
import org.crossv.evaluators.getters.NoSuchMemberException;
import org.crossv.primitives.Iterables;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class GetterEvaluatorTests {
	GetterEvaluator<Monkey, ?> evalutor;
	Iterable<Evaluation> evaluations;

	@Test
	public void evaluate_NonExistingGetterOnMokeyInstance_OneEvaluationFaultIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createTestableGetterEvaluator(
				Monkey.class, Object.class, "NonExistingGetterName");
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationFault.class));
	}

	@Test
	public void evaluate_NonExistingGetterOnMokeyInstance_EvaluationFaultCauseIsNoSuchMemberException() {
		EvaluationFault evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createTestableGetterEvaluator(
				Monkey.class, Object.class, "NonExistingGetterName");
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		evaluation = (EvaluationFault) Iterables.firstOrDefault(evaluations);
		assertThat(evaluation.getCause(), is(NoSuchMemberException.class));
	}

	@Test
	public void evaluate_NameGetterOnMokeyInstance_NoExceptionsAreThrown() {
		Monkey monkey;

		evalutor = TestObjectFactory.createTestableGetterEvaluator(
				Monkey.class, String.class, "Name");
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		assertThat(evaluations, isEmpty());
	}
	
	@Test
	public void evaluate_NameGetterOnNullMokeyInstance_NoExceptionsAreThrown() {
		Monkey monkey;

		evalutor = TestObjectFactory.createTestableGetterEvaluator(
				Monkey.class, String.class, "Name");
		monkey = null;
		evaluations = evalutor.evaluate(monkey);

		assertThat(evaluations, isEmpty());
	}
}
