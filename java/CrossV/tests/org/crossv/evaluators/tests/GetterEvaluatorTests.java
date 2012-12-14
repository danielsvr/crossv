package org.crossv.evaluators.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.Evaluator;
import org.crossv.NoContext;
import org.crossv.evaluators.getters.NoSuchMemberException;
import org.crossv.primitives.Iterables;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.TestableGetterEvaluator;
import org.junit.Test;

public class GetterEvaluatorTests {
	@Test
	public void evaluate_NonExistingGetterOnMokeyInstance_OneEvaluationFaultIsReturned() {
		Evaluator evalutor;
		Evaluation evaluation;
		
		evalutor = new TestableGetterEvaluator<Monkey, Object>(Monkey.class,
				Object.class, "NonExistingGetter");
		evaluation = Iterables.firstOrDefault(evalutor.evaluate(new Monkey(),
				NoContext.instance));
		assertThat(evaluation, is(EvaluationFault.class));
	}

	@Test
	public void evaluate_NonExistingGetterOnMokeyInstance_EvaluationFaultCauseIsNoSuchMemberException() {
		Evaluator evalutor;
		EvaluationFault evaluation;
		
		evalutor = new TestableGetterEvaluator<Monkey, Object>(Monkey.class,
				Object.class, "NonExistingGetter");
		evaluation = (EvaluationFault) Iterables.firstOrDefault(evalutor.evaluate(
				new Monkey(), NoContext.instance));
		assertThat(evaluation.getCause(), is(NoSuchMemberException.class));
	}

}
