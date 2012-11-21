package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.equalToObject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.crossv.Evaluator;
import org.crossv.strategies.EvaluatorProxy;
import org.crossv.strategies.EvaluatorsByContextIterator;
import org.crossv.strategies.NeverCancel;
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
		List<EvaluatorProxy> testedlist;
		TestableEvaluator evaluator;

		testedlist = new ArrayList<EvaluatorProxy>();
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		testedlist.add(new EvaluatorProxy(evaluator));
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		testedlist.add(new EvaluatorProxy(evaluator));
		evaluator = new TestableMonkeyEvaluator<SuperContext>(
				SuperContext.class);
		testedlist.add(new EvaluatorProxy(evaluator));
		evaluator = new TestableMonkeyEvaluator<ExtraExtendedConext>(
				ExtraExtendedConext.class);
		testedlist.add(new EvaluatorProxy(evaluator));
		evaluator = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		testedlist.add(new EvaluatorProxy(evaluator));
		evaluator = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		testedlist.add(new EvaluatorProxy(evaluator));
		Iterator<Evaluator> itr = new EvaluatorsByContextIterator(
				testedlist.iterator(), NeverCancel.instance);
		Evaluator next;
		Class<?> contextClass;

		next = itr.next();
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(SuperContext.class));
		next = itr.next();
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(IndependentContext1.class));
		next = itr.next();
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
		next = itr.next();
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
		next = itr.next();
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtendedConext.class));
		next = itr.next();
		contextClass = next.getContextClass();
		assertThat(contextClass, equalToObject(ExtraExtendedConext.class));
		assertThat(itr.hasNext(), is(false));
	}

}
