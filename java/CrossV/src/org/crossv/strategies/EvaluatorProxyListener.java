package org.crossv.strategies;

import org.crossv.Evaluation;

public interface EvaluatorProxyListener {
	void evaluateMethodCalled(EvaluatorProxy proxy, Iterable<Evaluation> result);
}
