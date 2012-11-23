package org.crossv.strategies;

import static org.crossv.primitives.Iterables.select;

import java.util.UUID;

import org.crossv.Evaluator;
import org.crossv.primitives.Function;
import org.crossv.primitives.Iterables;

public abstract class ValidationStrategy {
	private UUID currentBatchId;
	private EvaluatorToProxyConverter convertingToProxies;

	protected ValidationStrategy() {
		convertingToProxies = new EvaluatorToProxyConverter();
	}

	public final Iterable<Evaluator> apply(Iterable<Evaluator> evaluators) {
		Iterable<Evaluator> proxies;
		Iterable<EvaluatorProxy> castedProxies;

		currentBatchId = UUID.randomUUID();
		proxies = select(evaluators, convertingToProxies);
		castedProxies = Iterables.<Evaluator, EvaluatorProxy> cast(proxies);

		return applyStrategy(castedProxies);
	}

	protected void proxyCreated(EvaluatorProxy proxy) {
	}

	protected UUID getCurrentBatchId() {
		return currentBatchId;
	}

	protected abstract Iterable<Evaluator> applyStrategy(
			Iterable<EvaluatorProxy> proxies);

	private Evaluator createProxy(Evaluator evaluator) {
		EvaluatorProxy proxy = new EvaluatorProxy(evaluator);
		proxy.setEvaluationBatchId(currentBatchId);
		proxyCreated(proxy);
		return proxy;
	}

	public static ValidationStrategy createDefault() {
		return new ValidationByContextStrategy();
	}

	private class EvaluatorToProxyConverter implements
			Function<Evaluator, Evaluator> {
		public Evaluator eval(Evaluator value) {
			return createProxy(value);
		}
	}
}