package org.crossv;

import static org.crossv.primitives.Iterables.addAllToList;

import java.util.ArrayList;
import java.util.List;

import org.crossv.strategies.ValidationStrategy;

public class Validator {

	private EvaluatorProvider evaluatorProvider;
	private ValidationStrategy strategy;

	public Validator(Evaluator evaluator) {
		this(evaluator, (Evaluator[]) null);
	}

	public Validator(Iterable<Evaluator> evaluators) {
		this(new BasicEvaluatorRegistry(evaluators));
	}

	public Validator(Evaluator evaluator1, Evaluator... evaluators) {
		this(new BasicEvaluatorRegistry(evaluator1, evaluators));
	}

	public Validator(BasicEvaluatorRegistry registry) {
		this((EvaluatorProvider) registry);
	}

	public Validator(EvaluatorProvider evaluatorProvider) {
		this.evaluatorProvider = evaluatorProvider;
		setStrategy(ValidationStrategy.getDefault());
	}

	public void setStrategy(ValidationStrategy strategy) {
		this.strategy = strategy == null ? ValidationStrategy.getDefault()
				: strategy;
	}

	public <E> Validation validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, NoContext.instance);
	}

	public <E> Validation validate(Class<E> objClass, E obj, Object context) {
		List<Evaluation> allEvaluations;
		Iterable<Evaluation> currentEvaluations;
		Iterable<? extends Evaluator> evaluators;
		Class<?> contextClass;

		context = context != null ? context : NoContext.instance;
		contextClass = context.getClass();

		evaluators = evaluatorProvider.get(objClass, contextClass);
		evaluators = strategy.apply(evaluators);

		allEvaluations = new ArrayList<Evaluation>();
		for (Evaluator evaluator : evaluators) {
			currentEvaluations = evaluator.evaluate(obj, context);
			addAllToList(allEvaluations, currentEvaluations);
		}
		return new Validation(allEvaluations);
	}
}
