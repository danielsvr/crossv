package org.crossv;

public interface Evaluator {
	
	Iterable<Evaluation> evaluate(Object obj, Object context) throws IllegalObjectException;

	Class<?> getInstanceClass();

	Class<?> getContextClass();
}
