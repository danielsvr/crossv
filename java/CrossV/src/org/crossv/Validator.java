package org.crossv;

import static org.crossv.primitives.Iterables.addAllToList;

import java.util.ArrayList;
import java.util.List;

import org.crossv.strategies.ValidationStrategy;

/**
 * A class that describes an object validator.
 */
public class Validator {

	private EvaluatorProvider evaluatorProvider;
	private ValidationStrategy strategy;

  /**
   * Creates a new {@link Validator} object that uses one evaludator.
   * @param evaluator
   *          the single evaluator that this instance will use.
   */
	public Validator(Evaluator evaluator) {
		this(evaluator, (Evaluator[]) null);
	}

  /**
   * Creates a new {@link Validator} object that uses the provided evaludators.
   * @param evaluator
   *          the first evaluator that this instance will use.
   * @param evaluators
   *          the rest of the evaluators that this instance will use.
   */
	public Validator(Evaluator evaluator1, Evaluator... evaluators) {
		this(new BasicEvaluatorRegistry(evaluator1, evaluators));
	}

  /**
   * Creates a new {@link Validator} object that uses a basic evaluator registry.
   * @param registry
   *          the evaluator registry that will resolve all the needed evaluators.
   */
	public Validator(BasicEvaluatorRegistry registry) {
		this((EvaluatorProvider) registry);
	}

  /**
   * Creates a new {@link Validator} object that uses an evaluator provider.
   * @param evaluatorProvider
   *          the evaluator provider that will resolve all the needed evaluators. 
   */
	public Validator(EvaluatorProvider evaluatorProvider) {
		this.evaluatorProvider = evaluatorProvider;
		setStrategy(ValidationStrategy.getDefault());
	}

	/**
   * Sets a validation strategy as current that will affect the validation behavior.
   * @param strategy
   *          an instance of a {@link ValidationStrategy} class.
   */
  public void setStrategy(ValidationStrategy strategy) {
		this.strategy = strategy == null ? ValidationStrategy.getDefault()
				: strategy;
	}

  /**
   * Validates the provided object without a context.
   * @param objClass
   *          an instance of a {@link Class} class, the same class that the object should be.
   * @param obj
   *          an instance of an object, from the same class that objClass indicates, that will be validated.
   * @return
   *          an instance of the {@link Validation} class that will indicate that the validation have passed.
   * @throws  ValidationException
   *          when the validation is done with an exception based validation strategy.
   */
	public <E> Validation validate(Class<E> objClass, E obj) {
		return validate(objClass, obj, NoContext.instance);
	}

  /**
   * Validates the provided object with a context.
   * @param objClass
   *          an instance of a {@link Class} class, the same class that the object should be.
   * @param obj
   *          an instance of an object, from the same class that objClass indicates, that will be validated.
   * @param context
   *          an instance of an object that stands as a context during the validation.
   * @return
   *          an instance of the {@link Validation} class that will indicate that the validation have passed.
   * @throws  ValidationException
   *          when the validation is done with an exception based validation strategy.
   */
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
