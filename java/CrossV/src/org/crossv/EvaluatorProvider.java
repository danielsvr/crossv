package org.crossv;

public interface EvaluatorProvider {

	<E, EContext> Iterable<Evaluator> get(Class<E> objClass, Class<EContext> contextClass);

}
