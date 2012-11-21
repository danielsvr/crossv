package org.crossv.strategies;

public interface ObservableEvaluator {

	void addListener(EvaluatorProxyListener listener);

	void removeListener(EvaluatorProxyListener listener);
}
