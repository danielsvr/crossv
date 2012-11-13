package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.has;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.crossv.Evaluator;
import org.crossv.EvaluatorRegistry;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.IndependentContext2;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.SuperContext;
import org.junit.Before;
import org.junit.Test;

public class EvaluatorRegistryTests_With4EvaluatorsForMonkeyClass {

	private Evaluator evaluator1;
	private Evaluator evaluator2;
	private Evaluator evaluator3;
	private Evaluator evaluator4;
	private EvaluatorRegistry registry;

	@Before
	public void setup() {
		registry = new EvaluatorRegistry();

		evaluator1 = TestObjectFactory.createMonkeyEvaluator(
				SuperContext.class, "Rule1");
		registry.register(evaluator1);

		evaluator2 = TestObjectFactory.createMonkeyEvaluator(
				ExtendedConext.class, "Rule2");
		registry.register(evaluator2);

		evaluator3 = TestObjectFactory.createMonkeyEvaluator(
				IndependentContext1.class, "Rule3");
		registry.register(evaluator3);

		evaluator4 = TestObjectFactory.createMonkeyEvaluator(
				IndependentContext2.class, "Rule4");
		registry.register(evaluator4);
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_Returns2EvaluationResults() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, hasSize(2));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfEvaluator1() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, has(evaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfEvaluator2() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, has(evaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfEvaluator3() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, doesntHave(evaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfEvaluator4() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, doesntHave(evaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_Return1EvaluationResult() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_ReturnResultsOfEvaluator3() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, has(evaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator1() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(evaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator2() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(evaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator4() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(evaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_Return1EvaluationResult() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_ReturnResultsOfEvaluator4() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, has(evaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator1() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(evaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator2() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(evaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator3() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(evaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_Return3EvaluationResult() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, hasSize(3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator1() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, has(evaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator3() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, has(evaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator4() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, has(evaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_DoesntReturnResultsOfEvaluator2() {
		Iterable<Evaluator> evaluators;
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, doesntHave(evaluator2));
	}
}