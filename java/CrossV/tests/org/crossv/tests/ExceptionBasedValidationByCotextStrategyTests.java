package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.ValidationException;
import org.crossv.primitives.Iterables;
import org.crossv.strategies.ExceptionBasedValidationByCotextStrategy;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedContext1;
import org.crossv.tests.subjects.ExtraExtendedConext1;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.SuperContext1;
import org.crossv.tests.subjects.TestableEvaluator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExceptionBasedValidationByCotextStrategyTests {
	static List<TestableEvaluator> unorderedEcaluators;
	static ExceptionBasedValidationByCotextStrategy strategy;

	@Before
	public void setup() {
		TestableEvaluator evaluator;

		unorderedEcaluators = new ArrayList<TestableEvaluator>();
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(SuperContext1.class);
		evaluator.returns(Evaluation.fault("fail"));
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtraExtendedConext1.class);
		evaluator.returns(Evaluation.success("ok"));
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(ExtendedContext1.class);
		evaluator.returns(Evaluation.success("ok"));
		unorderedEcaluators.add(evaluator);
		evaluator = TestObjectFactory
				.createMonkeyEvaluator(IndependentContext1.class);
		evaluator.returns(Evaluation.success("ok"));
		unorderedEcaluators.add(evaluator);

		strategy = TestObjectFactory
				.createExceptionBasedValidationByCotextStrategy();
	}

	@After
	public void unsetup() {
		unorderedEcaluators = null;
		strategy = null;
	}

	@Test
	public void iterate_OneElement_ContextOfCurrentEvaluatorIsSuperContext() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;
		
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 0);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(SuperContext1.class));
	}

	@Test
	public void iterate_TwoElements_ContextOfCurrentEvaluatorIsIndependentContext1() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;
		
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 1);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(IndependentContext1.class));
	}

	@Test
	public void iterate_ThreeElements_ContextOfCurrentEvaluatorIsExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;
		
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 2);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(ExtendedContext1.class));
	}

	@Test
	public void iterate_FourElements_ContextOfCurrentEvaluatorIsExtraExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		Iterable<? extends Evaluator> strategicIterable;
		
		strategicIterable = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterable, 3);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalTo(ExtraExtendedConext1.class));
	}

	@Test
	public void evaluate_FirstThreeElements_ThirdEvaluatorsThrowsValidationException() {
		Iterator<? extends Evaluator> iterator;
		Evaluator element;
		Iterable<? extends Evaluator> strategicIterable;
		
		strategicIterable = strategy.apply(unorderedEcaluators);
		iterator = strategicIterable.iterator();
		iterator.next().evaluate(null, new SuperContext1());
		iterator.next().evaluate(null, new IndependentContext1());
		element = iterator.next();
		try {
			element.evaluate(null, new ExtendedContext1());
			fail("this evaluation should throw a validation exception");
		} catch (ValidationException e) {
		}
	}
}
