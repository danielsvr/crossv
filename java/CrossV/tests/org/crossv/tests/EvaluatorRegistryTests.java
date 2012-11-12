package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.*;
import static org.junit.Assert.*;

import org.crossv.BasicEvaluator;
import org.crossv.EvaluationResult;
import org.crossv.EvaluatorRegistry;
import org.junit.Test;

public class EvaluatorRegistryTests {

	@Test
	public void register_ObjectEvaluator_registryContainsEvaluator() {
		BasicEvaluator<Object> objectEvaluator;
		objectEvaluator = new BasicEvaluator<Object>(Object.class) {
			@Override
			public Iterable<EvaluationResult> evaluateInstance(Object obj) {
				return null;
			}
		};
		EvaluatorRegistry registry = new EvaluatorRegistry();
		registry.register(objectEvaluator);
		
		assertThat(registry, contains(objectEvaluator));
	}
}
