package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.equalToObject;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

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
	List<Evaluator> unorderedEcaluators;
	Iterable<Evaluator> strategicIterator;
	ValidationByCotextStrategy strategy;

	@Before
	public void setup() {
		TestableEvaluator evaluator;

		unorderedEcaluators = new ArrayList<Evaluator>();
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
		strategicIterator = null;
		strategy = null;
	}

	@Test
	public void Iterate_OneElement_ContextOfCurrentEvaluatorIsSupertContext() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterator = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterator, 0);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(SuperContext.class));
	}

	@Test
	public void Iterate_TwoElements_ContextOfCurrentEvaluatorIsIndependentContext1() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterator = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterator, 1);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(IndependentContext1.class));
	}

	@Test
	public void Iterate_ThreeElements_ContextOfCurrentEvaluatorIsExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterator = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterator, 2);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
	}

	@Test
	public void Iterate_FourElements_ContextOfCurrentEvaluatorIsExtraExtendedConext() {
		Evaluator element;
		Class<?> contextClass;
		strategicIterator = strategy.apply(unorderedEcaluators);
		element = Iterables.elementAt(strategicIterator, 3);
		contextClass = element.getContextClass();
		assertThat(contextClass, equalToObject(ExtraExtendedConext.class));
	}
}
