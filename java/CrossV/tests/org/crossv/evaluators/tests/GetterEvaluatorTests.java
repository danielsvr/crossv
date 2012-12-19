package org.crossv.evaluators.tests;

import static org.crossv.tests.helpers.Matchers.isEmpty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.getters.GetterDescriptor;
import org.crossv.getters.GetterEvaluator;
import org.crossv.getters.NoSuchMemberException;
import org.crossv.primitives.Iterables;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.TestableGetterEvaluator;
import org.junit.Test;

public class GetterEvaluatorTests {
	GetterEvaluator<Monkey> evalutor;
	Iterable<Evaluation> evaluations;

	@Test
	public void evaluate_NonExistingGetterOnMokeyInstance_OneEvaluationFaultIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = new TestableGetterEvaluator<Monkey>(
				new GetterDescriptor<Monkey>(Monkey.class,
						"NonExistingGetterName"));
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationFault.class));
	}

	@Test
	public void evaluate_NonExistingGetterOnMokeyInstance_EvaluationFaultCauseIsNoSuchMemberException() {
		EvaluationFault evaluation;
		Monkey monkey;

		evalutor = new TestableGetterEvaluator<Monkey>(
				new GetterDescriptor<Monkey>(Monkey.class,
						"NonExistingGetterName"));
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		evaluation = (EvaluationFault) Iterables.firstOrDefault(evaluations);
		assertThat(evaluation.getCause(), is(NoSuchMemberException.class));
	}

	@Test
	public void evaluate_NameGetterOnMokeyInstance_NoExceptionsAreThrown() {
		Monkey monkey;

		evalutor = new TestableGetterEvaluator<Monkey>(
				new GetterDescriptor<Monkey>(Monkey.class, "Name"));
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		assertThat(evaluations, isEmpty());
	}

	@Test
	public void evaluate_NameGetterOnNullMokeyInstance_NoExceptionsAreThrown() {
		Monkey monkey;

		evalutor = new TestableGetterEvaluator<Monkey>(
				new GetterDescriptor<Monkey>(Monkey.class, "Name"));
		monkey = null;
		evaluations = evalutor.evaluate(monkey);

		assertThat(evaluations, isEmpty());
	}
	
	@Test
	public void evaluate_NicknameGetterOnNullMokeyInstance_NoExceptionsAreThrown() {
		Monkey monkey;
		
		evalutor = new TestableGetterEvaluator<Monkey>(
				new GetterDescriptor<Monkey>(Monkey.class, "Nickname"));
		monkey = null;
		evaluations = evalutor.evaluate(monkey);
		
		assertThat(evaluations, isEmpty());
	}
}
