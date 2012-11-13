package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.has;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.crossv.tests.helpers.Assert.assertThat;

import org.crossv.Evaluator;
import org.crossv.EvaluatorRegistry;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.IndependentContext2;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.SuperContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EvaluatorRegistryTests_With4EvaluatorsForMonkeyClass {
	Evaluator monkeyEvaluator1;
	Evaluator monkeyEvaluator2;
	Evaluator monkeyEvaluator3;
	Evaluator monkeyEvaluator4;
	Evaluator mouseEvaluator1;
	Evaluator mouseEvaluator2;
	EvaluatorRegistry registry;
	Iterable<Evaluator> evaluators;
	
	@Before
	public void setup() {
		registry = TestObjectFactory.createEvaluatorRegistry();

		monkeyEvaluator1 = TestObjectFactory.createMonkeyEvaluator(
				SuperContext.class, "Rule1");
		registry.register(monkeyEvaluator1);

		monkeyEvaluator2 = TestObjectFactory.createMonkeyEvaluator(
				ExtendedConext.class, "Rule2");
		registry.register(monkeyEvaluator2);

		monkeyEvaluator3 = TestObjectFactory.createMonkeyEvaluator(
				IndependentContext1.class, "Rule3");
		registry.register(monkeyEvaluator3);

		monkeyEvaluator4 = TestObjectFactory.createMonkeyEvaluator(
				IndependentContext2.class, "Rule4");
		registry.register(monkeyEvaluator4);

		mouseEvaluator1 = TestObjectFactory.createMouseEvaluator(
				SuperContext.class, "Rule5");
		registry.register(mouseEvaluator1);

		mouseEvaluator2 = TestObjectFactory.createMouseEvaluator(
				SuperContext.class, "Rule6");
		registry.register(mouseEvaluator2);
	}
	
	@After
	public void unsetup(){
		monkeyEvaluator1 = null;
		monkeyEvaluator2 = null;
		monkeyEvaluator3 = null;
		monkeyEvaluator4 = null;
		mouseEvaluator1 = null;
		mouseEvaluator2 = null;
		registry = null;
		evaluators = null;
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_Returns2EvaluationResults() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, hasSize(2));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, has(monkeyEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, has(monkeyEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, ExtendedConext.class);
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_Return1EvaluationResult() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_ReturnResultsOfMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, has(monkeyEvaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, IndependentContext1.class);
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_Return1EvaluationResult() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_ReturnResultsOfMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, has(monkeyEvaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, IndependentContext2.class);
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_Return3EvaluationResult() {
		evaluators = registry.get(Monkey.class, null);
		assertThat(evaluators, hasSize(3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, null);
		assertThat(evaluators, has(monkeyEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, null);
		assertThat(evaluators, has(monkeyEvaluator3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, null);
		assertThat(evaluators, has(monkeyEvaluator4));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_DoesntReturnResultsOfMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, doesntHave(monkeyEvaluator2));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_DoesntReturnResultsOfMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_DoesntReturnResultsOfMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, Object.class);
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}
}