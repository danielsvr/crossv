package org.crossv.strategies;

import org.crossv.Evaluator;
import org.crossv.primitives.Function;
import org.crossv.primitives.Iterables;

public abstract class ValidationStrategy {

	public static ValidationStrategy createDefault() {
		return new ValidationByContextStrategy();
	}

	public final Iterable<Evaluator> apply(Iterable<Evaluator> evaluators) {
		Iterable<Evaluator> proxies;
		Iterable<EvaluatorProxy> castedProxies;

		proxies = Iterables.select(evaluators,
				new Function<Evaluator, Evaluator>() {
					public Evaluator eval(Evaluator value) {
						return createProxy(value);
					}
				});
		castedProxies = Iterables.<Evaluator, EvaluatorProxy> cast(proxies);

		return applyStrategy(castedProxies);
	}

	protected abstract Iterable<Evaluator> applyStrategy(
			Iterable<EvaluatorProxy> proxies);

	protected void proxyCreated(EvaluatorProxy proxy) {
	}

	private Evaluator createProxy(Evaluator evaluator) {
		EvaluatorProxy proxy = new EvaluatorProxy(evaluator);
		proxyCreated(proxy);
		return proxy;
	}
}