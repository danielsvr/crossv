package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.equalToObject;
import static org.crossv.tests.helpers.Matchers.isEmpty;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.primitives.Iterables;
import org.crossv.primitives.Predicate;
import org.crossv.strategies.ValidationByCotextStrategy;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.ExtraExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.SuperContext;
import org.crossv.tests.subjects.TestableEvaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ValidationByCotextStrategyTests {
	List<TestableEvaluator> unorderedEcaluators;
	Iterable<Evaluator> strategicIterable;
	ValidationByCotextStrategy strategy;

	@Before
	public void setup() {
		TestableEvaluator evaluator;

		unorderedEcaluators = new ArrayList<TestableEvaluator>();
		evaluator = TestObjectFactory.createMonkeyEvaluator(SuperContext.class);
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtraExtendedConext.class);
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtendedConext.class);
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(IndependentContext1.class);
		unorderedEcaluators.add(evaluator);

		strategy = TestObjectFactory.createValidationByCotextStrategy();
	}

	@After
	public void unsetup() {
		unorderedEcaluators = null;
		strategicIterable = null;
		strategy = null;
	}

	@Test
	public void Iterate_OneElement_ContextOfCurrentEvaluatorIsSuperContext() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 0);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(SuperContext.class));
	}

	@Test
	public void Iterate_TwoElements_ContextOfCurrentEvaluatorIsIndependentContext1() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 1);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(IndependentContext1.class));
	}

	@Test
	public void Iterate_ThreeElements_ContextOfCurrentEvaluatorIsExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 2);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
	}

	@Test
	public void Iterate_FourElements_ContextOfCurrentEvaluatorIsExtraExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 3);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(ExtraExtendedConext.class));
	}

	@Test
	public void EvaluateFirstThreeElements_OriginalFirstElementReturnFaults_StrategicIteratorDoentIterate() {
		TestableEvaluator element;
		Iterator<Evaluator> iterator;

		element = Iterables.firstOrDefault(unorderedEcaluators,
				new Predicate<TestableEvaluator>() {
					public boolean eval(TestableEvaluator value) {
						return value.getContextClass().equals(
								SuperContext.class);
					}
				});

		element.withRsults(Evaluation.fault("fail"));
		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, new SuperContext());
		iterator.next().evaluate(null, new IndependentContext1());
		iterator.next().evaluate(null, new ExtendedConext());
		assertThat(iterator.hasNext(), equalTo(false));
	}

	@Test
	public void EvaluateFirstThreeElements_OriginalFirstElementReturnFaults_ThirdEvaluatorsReturnEmptyIterator() {
		TestableEvaluator element;
		Iterator<Evaluator> iterator;
		Evaluator thirdEvaluator;
		ExtendedConext thirdContext;
		Iterable<Evaluation> thirdEvaluatorResults;

		element = Iterables.firstOrDefault(unorderedEcaluators,
				new Predicate<TestableEvaluator>() {
					public boolean eval(TestableEvaluator value) {
						return value.getContextClass().equals(
								SuperContext.class);
					}
				});

		element.withRsults(Evaluation.fault("fail"));
		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, new SuperContext());
		iterator.next().evaluate(null, new IndependentContext1());
		thirdEvaluator = iterator.next();
		thirdContext = new ExtendedConext();
		thirdEvaluatorResults = thirdEvaluator.evaluate(null, thirdContext);

		assertThat(thirdEvaluatorResults, isEmpty());
	}
}
