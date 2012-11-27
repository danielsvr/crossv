package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.equalToObject;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.crossv.Evaluator;
import org.crossv.primitives.Iterables;
import org.crossv.strategies.ValidationByCotextStrategy;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.ExtraExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.SuperContext;
import org.crossv.tests.subjects.TestableEvaluator;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.junit.Before;
import org.junit.Test;

public class EvaluatorsByContextIteratorTests {

	@Before
	public void setup() {

	}

	@Test
	public void monkey() {
		List<Evaluator> testedlist;
		TestableEvaluator evaluator;

		testedlist = new ArrayList<Evaluator>();
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		testedlist.add(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		testedlist.add(evaluator);
		evaluator = new TestableMonkeyEvaluator<SuperContext>(
				SuperContext.class);
		testedlist.add(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtraExtendedConext>(
				ExtraExtendedConext.class);
		testedlist.add(evaluator);
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		testedlist.add(evaluator);
		evaluator = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		testedlist.add(evaluator);
		
		Iterable<Evaluator> it = new ValidationByCotextStrategy().apply(testedlist);
		Evaluator next;
		Class<?> contextClass;

		 next = Iterables.elementAt(it, 0);
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(SuperContext.class));
		 next = Iterables.elementAt(it, 1);
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(IndependentContext1.class));
	    next = Iterables.elementAt(it, 2);
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
		next = Iterables.elementAt(it, 3);
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
		next = Iterables.elementAt(it, 4);
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
		next = Iterables.elementAt(it, 5);
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtraExtendedConext.class));
	}

}
