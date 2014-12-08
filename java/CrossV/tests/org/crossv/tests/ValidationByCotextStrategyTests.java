package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.crossv.tests.helpers.Matchers.isEmpty;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.NoContext;
import org.crossv.primitives.Iterables;
import org.crossv.strategies.EvaluatorProxy;
import org.crossv.strategies.ValidationByCotextStrategy;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedContext1;
import org.crossv.tests.subjects.ExtraExtendedConext1;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.SuperContext1;
import org.crossv.tests.subjects.TestableEvaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidationByCotextStrategyTests {
	static List<TestableEvaluator> unorderedEcaluators;
	static ValidationByCotextStrategy strategy;
	private TestableEvaluator superContextEvaluator;
	private TestableEvaluator extraExtendedContextEvaluator;
	private TestableEvaluator extendedContextEvaluator;
	private TestableEvaluator independentContextEvaluator;
	private TestableEvaluator noContextEvaluator;

	@Before
	public void setup() {
		TestableEvaluator evaluator;

		unorderedEcaluators = new ArrayList<TestableEvaluator>();
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(SuperContext1.class);
		evaluator.returns(Evaluation.fault("fail"));
		superContextEvaluator = evaluator;
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtraExtendedConext1.class);
		evaluator.returns(Evaluation.success("ok"));
		extraExtendedContextEvaluator = evaluator;
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtendedContext1.class);
		evaluator.returns(Evaluation.success("ok"));
		extendedContextEvaluator = evaluator;
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(IndependentContext1.class);
		evaluator.returns(Evaluation.success("ok"));
		independentContextEvaluator = evaluator;
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory.createMonkeyEvaluator();
		evaluator.returns(Evaluation.warning("ok"));
		noContextEvaluator = evaluator;
		unorderedEcaluators.add(evaluator);

		strategy = TestObjectFactory.createValidationByCotextStrategy();
	}

	@After
	public void unsetup() {
		unorderedEcaluators = null;
		strategy = null;
	}

	@Test
	public void iterate_OneElement_EvaluatorIsNoContext() {
		Evaluator element;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 0);
		element = ((EvaluatorProxy) element).getRealEvaluator();
		assertThat(element, equalTo(noContextEvaluator));
	}

	@Test
	public void iterate_OneElement_ContextOfCurrentEvaluatorIsNoContextEvaluator() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 0);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(NoContext.class));
	}

	@Test
	public void iterate_TwoElements_EvaluatorIsSuperContextEvaluator() {
		Evaluator element;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 1);
		element = ((EvaluatorProxy) element).getRealEvaluator();
		assertThat(element, equalTo(superContextEvaluator));
	}

	@Test
	public void iterate_TwoElements_ContextOfCurrentEvaluatorIsSuperContext1() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 1);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(SuperContext1.class));
	}

	@Test
	public void iterate_ThreeElements_EvaluatorIsIndependentContextEvaluator() {
		Evaluator element;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 2);
		element = ((EvaluatorProxy) element).getRealEvaluator();
		assertThat(element, equalTo(independentContextEvaluator));
	}

	@Test
	public void iterate_ThreeElements_ContextOfCurrentEvaluatorIsIndependentContext() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 2);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(IndependentContext1.class));
	}

	@Test
	public void iterate_FourElements_EvaluatorIsExtendedConextEvaluator() {
		Evaluator element;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 3);
		element = ((EvaluatorProxy) element).getRealEvaluator();
		assertThat(element, equalTo(extendedContextEvaluator));
	}

	@Test
	public void iterate_FourElements_ContextOfCurrentEvaluatorIsExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 3);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(ExtendedContext1.class));
	}

	@Test
	public void iterate_FiveElements_ContextOfCurrentEvaluatorIsExtraExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 4);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(ExtraExtendedConext1.class));
	}

	@Test
	public void iterate_FiveElements_EvaluatorIsExtraExtendedConextEvaluator() {
		Evaluator element;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 4);
		element = ((EvaluatorProxy) element).getRealEvaluator();
		assertThat(element, equalTo(extraExtendedContextEvaluator));
	}

	@Test
	public void evaluate_FirstThreeElements_StrategicIteratorDoentIterate() {
		Iterator<? extends Evaluator> iterator;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, NoContext.instance);
		iterator.next().evaluate(null, new SuperContext1());
		iterator.next().evaluate(null, new IndependentContext1());
		iterator.next().evaluate(null, new ExtendedContext1());
		assertThat(iterator.hasNext(), equalTo(false));
	}

	@Test
	public void evaluate_FirstThreeElements_ThirdEvaluatorsReturnEmptyIterable() {
		Iterator<? extends Evaluator> iterator;
		Evaluator element;
		Iterable<Evaluation> results;
		Iterable<? extends Evaluator> strategicIterable;

		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, NoContext.instance);
		iterator.next().evaluate(null, new SuperContext1());
		iterator.next().evaluate(null, new IndependentContext1());
		element = iterator.next();
		results = element.evaluate(null, new ExtendedContext1());

		assertThat(results, isEmpty());
	}
}
