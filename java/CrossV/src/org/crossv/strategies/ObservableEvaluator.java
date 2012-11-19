package org.crossv.strategies;

public interface ObservableEvaluator {

	void addListener(EvaluatorListener listener);

	void removeListener(EvaluatorListener listener);
}
