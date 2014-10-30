package org.crossv;

public interface EvaluatorProvider {	
	<E> Iterable<Evaluator> get(Class<E> objClass,
			Object contextClass);
}
