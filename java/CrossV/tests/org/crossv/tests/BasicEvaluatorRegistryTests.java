package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.doesntHave;
import static org.crossv.tests.helpers.Matchers.has;
import static org.crossv.tests.helpers.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.crossv.BasicEvaluatorRegistry;
import org.crossv.Evaluator;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedContext1;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.IndependentContext2;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.SuperContext1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicEvaluatorRegistryTests {
	static Evaluator monkeyEvaluator1;
	static Evaluator monkeyEvaluator2;
	static Evaluator monkeyEvaluator3;
	static Evaluator monkeyEvaluator4;
	static Evaluator monkeyEvaluator5;
	static Evaluator monkeyEvaluator6;
	static Evaluator monkeyEvaluator7;
	static Evaluator mouseEvaluator1;
	static Evaluator mouseEvaluator2;
	static BasicEvaluatorRegistry registry;
	static Iterable<Evaluator> evaluators;

	@Before
	public void setup() {
		registry = TestObjectFactory.createEvaluatorRegistry();

		monkeyEvaluator1 = TestObjectFactory
				.createMonkeyEvaluator(SuperContext1.class);
		registry.register(monkeyEvaluator1);

		monkeyEvaluator2 = TestObjectFactory
				.createMonkeyEvaluator(ExtendedContext1.class);
		registry.register(monkeyEvaluator2);

		monkeyEvaluator3 = TestObjectFactory
				.createMonkeyEvaluator(IndependentContext1.class);
		registry.register(monkeyEvaluator3);

		monkeyEvaluator4 = TestObjectFactory
				.createMonkeyEvaluator(IndependentContext2.class);
		registry.register(monkeyEvaluator4);

		monkeyEvaluator5 = TestObjectFactory.createMonkeyEvaluator();
		registry.register(monkeyEvaluator5);

		monkeyEvaluator6 = TestObjectFactory.createMonkeyEvaluator();
		registry.register(monkeyEvaluator6);

		monkeyEvaluator7 = TestObjectFactory.createMonkeyEvaluator();
		registry.register(monkeyEvaluator7);

		mouseEvaluator1 = TestObjectFactory
				.createMouseEvaluator(SuperContext1.class);
		registry.register(mouseEvaluator1);

		mouseEvaluator2 = TestObjectFactory
				.createMouseEvaluator(SuperContext1.class);
		registry.register(mouseEvaluator2);

	}

	@After
	public void unsetup() {
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
	public void getEvaluators_ForMonkeyAndExtendedConext_Returns2Evaluators() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, hasSize(2 + 3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndExtendedConext_ReturnsMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, has(monkeyEvaluator1));
	}

	@Test
	public void getEvaluators_ForMonkeyAndExtendedConext_ReturnsMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, has(monkeyEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndExtendedConext_DoesntReturnMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, doesntHave(monkeyEvaluator3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndExtendedConext_DoesntReturnMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, doesntHave(monkeyEvaluator4));
	}

	@Test
	public void getEvaluators_ForMonkeyAndExtendedConext_DoesntReturnMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void getEvaluators_ForMonkeyAndExtendedConext_DoesntReturnMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, new ExtendedContext1());
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext1_Return1Evaluator() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, hasSize(1 + 3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext1_ReturnMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, has(monkeyEvaluator3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext1_DoesntReturnMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, doesntHave(monkeyEvaluator1));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext1_DoesntReturnMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, doesntHave(monkeyEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext1_DoesntReturnMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, doesntHave(monkeyEvaluator4));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext1_DoesntReturnMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void vgetEvaluators_ForMonkeyAndIndependentContext1_DoesntReturnMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, new IndependentContext1());
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_Return1Evaluator() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, hasSize(1 + 3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_ReturnMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, has(monkeyEvaluator4));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_DoesntReturnMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, doesntHave(monkeyEvaluator1));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_DoesntReturnMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, doesntHave(monkeyEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_DoesntReturnMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, doesntHave(monkeyEvaluator3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_DoesntReturnMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void getEvaluators_ForMonkeyAndIndependentContext2_DoesntReturnMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, new IndependentContext2());
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_Return3Evaluators() {
		evaluators = registry.get(Monkey.class, (Object) null);
		assertThat(evaluators, hasSize(3));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_ReturnMonkeyEvaluator1() {
		evaluators = registry.get(Monkey.class, (Object) null);
		assertThat(evaluators, has(monkeyEvaluator5));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_ReturnMonkeyEvaluator3() {
		evaluators = registry.get(Monkey.class, (Object) null);
		assertThat(evaluators, has(monkeyEvaluator6));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_ReturnMonkeyEvaluator4() {
		evaluators = registry.get(Monkey.class, (Object) null);
		assertThat(evaluators, has(monkeyEvaluator7));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_DoesntReturnMonkeyEvaluator2() {
		evaluators = registry.get(Monkey.class, new Object());
		assertThat(evaluators, doesntHave(monkeyEvaluator2));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_DoesntReturnMouseEvaluator1() {
		evaluators = registry.get(Monkey.class, new Object());
		assertThat(evaluators, doesntHave(mouseEvaluator1));
	}

	@Test
	public void getEvaluators_ForMonkeyAndNoContext_DoesntReturnMouseEvaluator2() {
		evaluators = registry.get(Monkey.class, new Object());
		assertThat(evaluators, doesntHave(mouseEvaluator2));
	}
}