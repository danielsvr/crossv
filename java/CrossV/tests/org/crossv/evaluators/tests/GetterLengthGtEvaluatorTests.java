package org.crossv.evaluators.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.EvaluationSuccess;
import org.crossv.EvaluationWarning;
import org.crossv.evaluators.getters.LengthGreaterThan;
import org.crossv.primitives.Iterables;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class GetterLengthGtEvaluatorTests {
	LengthGreaterThan<Monkey> evalutor;
	Iterable<Evaluation> evaluations;

	@Test
	public void evaluate_EmptyNameForLegthGreaterThan10_OneEvaluationFaultIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "Name", 10);
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationFault.class));
	}

	@Test
	public void evaluate_ShortNameForLegthGreaterThan10_OneEvaluationFaultIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "Name", 10);
		monkey = new Monkey();
		monkey.setName("123456789");
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationFault.class));
	}

	@Test
	public void evaluate_LongNameForLegthGreaterThan10_OneEvaluationSuccessIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "Name", 10);
		monkey = new Monkey();
		monkey.setName("12345678901");
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationSuccess.class));
	}

	@Test
	public void evaluate_BrotherForLegthGreaterThan10_OneEvaluationSuccessIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "Mother", 10);
		monkey = new Monkey();
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationWarning.class));
	}

	@Test
	public void evaluate_RelativesAsArrayForLegthGreaterThan10_OneEvaluationSuccessIsReturned() {
		Evaluation evaluation;
		Monkey monkey;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "RelativesAsArray", 10);
		monkey = new Monkey();
		monkey.setRelativesAsArray(new Monkey[11]);
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationSuccess.class));
	}

	@Test
	public void evaluate_RelativesAsListForLegthGreaterThan10_OneEvaluationSuccessIsReturned() {
		Evaluation evaluation;
		Monkey monkey;
		Iterable<Monkey> monkeys;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "RelativesAsList", 10);
		monkeys = Iterables.repeatDefault(11);
		monkey = new Monkey();
		monkey.setRelativesAsList(Iterables.toList(monkeys));
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationSuccess.class));
	}

	@Test
	public void evaluate_RelativesAsIterableForLegthGreaterThan10_OneEvaluationSuccessIsReturned() {
		Evaluation evaluation;
		Monkey monkey;
		Iterable<Monkey> monkeys;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "RelativesAsIterable", 10);
		monkeys = Iterables.repeatDefault(11);
		monkey = new Monkey();
		monkey.setRelativesAsIterable(monkeys);
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationSuccess.class));
	}

	@Test
	public void evaluate_RelativesAsEnumerationForLegthGreaterThan10_OneEvaluationSuccessIsReturned() {
		Evaluation evaluation;
		Monkey monkey;
		Iterable<Monkey> monkeys;

		evalutor = TestObjectFactory.createGetterLengthGtEvaluator(
				Monkey.class, "RelativesAsEnumeration", 10);
		monkeys = Iterables.repeatDefault(11);
		monkey = new Monkey();
		monkey.setRelativesAsEnumeration(Iterables.toEnumeration(monkeys));
		evaluations = evalutor.evaluate(monkey);

		evaluation = Iterables.firstOrDefault(evaluations);
		assertThat(evaluation, is(EvaluationSuccess.class));
	}
}
