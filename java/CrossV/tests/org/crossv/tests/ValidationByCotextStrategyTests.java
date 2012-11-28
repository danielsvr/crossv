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
		evaluator.returns(Evaluation.fault("fail"));
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtraExtendedConext.class);
		evaluator.returns(Evaluation.success("ok"));
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtendedConext.class);
		evaluator.returns(Evaluation.success("ok"));
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(IndependentContext1.class);
		evaluator.returns(Evaluation.success("ok"));
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
	public void Evaluate_FirstThreeElements_StrategicIteratorDoentIterate() {
		Iterator<Evaluator> iterator;

		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, new SuperContext());
		iterator.next().evaluate(null, new IndependentContext1());
		iterator.next().evaluate(null, new ExtendedConext());
		assertThat(iterator.hasNext(), equalTo(false));
	}

	@Test
	public void Evaluate_FirstThreeElements_ThirdEvaluatorsReturnEmptyIterable() {
		Iterator<Evaluator> iterator;
		Evaluator element;
		Iterable<Evaluation> results;

		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, new SuperContext());
		iterator.next().evaluate(null, new IndependentContext1());
		element = iterator.next();
		results = element.evaluate(null, new ExtendedConext());

		assertThat(results, isEmpty());
	}
}
