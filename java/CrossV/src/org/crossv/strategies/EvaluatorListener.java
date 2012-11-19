package org.crossv.strategies;

import org.crossv.Evaluation;

public interface EvaluatorListener {
	void evaluateMethodCalled(Iterable<Evaluation> result);
}
