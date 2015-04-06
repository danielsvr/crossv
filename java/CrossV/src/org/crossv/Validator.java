package org.crossv;

import static org.crossv.primitives.Iterables.addAllToList;

import java.util.ArrayList;
import java.util.List;

import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.expressions.Expressions;
import org.crossv.strategies.ValidationStrategy;

/**
 * A class that describes an object validator.
 */
public class Validator {

	private EvaluatorProvider evaluatorProvider;
	private ValidationStrategy strategy;
	private Facilities facilities;

	/**
	 * Creates a new {@link Validator} object that uses an evaluation
	 * expression.
	 * 
	 * @param expresison
	 *            the expression to be evaluated as a list of evaluators.
	 * @throws EvaluationException
	 */
	public Validator(String expression) throws EvaluationException {
		this(Expressions.parse(expression));
	}

	/**
	 * Creates a new {@link Validator} object that uses an evaluation
	 * expression.
	 * 
	 * @param expresison
	 *            the expression to be evaluated as a list of evaluators.
	 * @throws EvaluationException
	 */
	public Validator(Expression expression) throws EvaluationException {
		this(expression.<Iterable<Evaluator>> evaluate());
	}

	/**
	 * Creates a new {@link Validator} object that uses one evaludator.
	 * 
	 * @param evaluator
	 *            the single evaluator that this instance will use.
	 */
	public Validator(Evaluator evaluator) {
		this(evaluator, (Evaluator[]) null);
	}

	/**
	 * Creates a new {@link Validator} object that uses the provided
	 * evaludators.
	 * 
	 * @param evaluators
	 *            the evaluators that this instance will use.
	 */
	public Validator(Iterable<Evaluator> evaluators) {
		this(new BasicEvaluatorRegistry(evaluators));
	}

	/**
	 * Creates a new {@link Validator} object that uses the provided
	 * evaludators.
	 * 
	 * @param evaluator
	 *            the first evaluator that this instance will use.
	 * @param evaluators
	 *            the rest of the evaluators that this instance will use.
	 */
	public Validator(Evaluator evaluator1, Evaluator... evaluators) {
		this(new BasicEvaluatorRegistry(evaluator1, evaluators));
	}

	/**
	 * Creates a new {@link Validator} object that uses a basic evaluator
	 * registry.
	 * 
	 * @param registry
	 *            the evaluator registry that will resolve all the needed
	 *            evaluators.
	 */
	public Validator(BasicEvaluatorRegistry registry) {
		this((EvaluatorProvider) registry);
	}

	/**
	 * Creates a new {@link Validator} object that uses an evaluator provider.
	 * 
	 * @param evaluatorProvider
	 *            the evaluator provider that will resolve all the needed
	 *            evaluators.
	 */
	public Validator(EvaluatorProvider evaluatorProvider) {
		this(evaluatorProvider, ValidatorOptions.DEFAULT);
	}

	/**
	 * Creates a new {@link Validator} object that uses an evaluation
	 * expression.
	 * 
	 * @param expresison
	 *            the expression to be evaluated as a list of evaluators.
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 * @throws EvaluationException
	 */
	public Validator(String expression, ValidatorOptions options)
			throws EvaluationException {
		this(Expressions.parse(expression), options);
	}

	/**
	 * Creates a new {@link Validator} object that uses an evaluation
	 * expression.
	 * 
	 * @param expresison
	 *            the expression to be evaluated as a list of evaluators.
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 * @throws EvaluationException
	 */
	public Validator(Expression expression, ValidatorOptions options)
			throws EvaluationException {
		this(options, expression.<Iterable<Evaluator>> evaluate());
	}

	/**
	 * Creates a new {@link Validator} object that uses one evaludator.
	 * 
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 * @param evaluator
	 *            the single evaluator that this instance will use.
	 */
	public Validator(ValidatorOptions options, Evaluator evaluator) {
		this(options, evaluator, (Evaluator[]) null);
	}

	/**
	 * Creates a new {@link Validator} object that uses the provided
	 * evaludators.
	 * 
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 * @param evaluators
	 *            the evaluators that this instance will use.
	 */
	public Validator(ValidatorOptions options, Iterable<Evaluator> evaluators) {
		this(new BasicEvaluatorRegistry(evaluators), options);
	}

	/**
	 * Creates a new {@link Validator} object that uses the provided
	 * evaludators.
	 * 
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 * @param evaluator
	 *            the first evaluator that this instance will use.
	 * @param evaluators
	 *            the rest of the evaluators that this instance will use.
	 */
	public Validator(ValidatorOptions options, Evaluator evaluator1,
			Evaluator... evaluators) {
		this(new BasicEvaluatorRegistry(evaluator1, evaluators), options);
	}

	/**
	 * Creates a new {@link Validator} object that uses a basic evaluator
	 * registry.
	 * 
	 * @param registry
	 *            the evaluator registry that will resolve all the needed
	 *            evaluators.
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 */
	public Validator(BasicEvaluatorRegistry registry, ValidatorOptions options) {
		this((EvaluatorProvider) registry, options);
	}

	/**
	 * Creates a new {@link Validator} object that uses an evaluator provider.
	 * 
	 * @param evaluatorProvider
	 *            the evaluator provider that will resolve all the needed
	 *            evaluators.
	 * @param options
	 *            the options on which validation will create its facilities,
	 *            like logging, time monitor, performance dump.
	 */
	public Validator(EvaluatorProvider evaluatorProvider,
			ValidatorOptions options) {
		this.evaluatorProvider = evaluatorProvider;
		this.facilities = new Facilities(options);
		
		setStrategy(ValidationStrategy.getDefault());
	}

	/**
	 * Sets a validation strategy as current that will affect the validation
	 * behavior.
	 * 
	 * @param strategy
	 *            an instance of a {@link ValidationStrategy} class.
	 */
	public void setStrategy(ValidationStrategy strategy) {
		this.facilities.trace.enter("setStrategy", strategy);
		this.strategy = strategy == null ? ValidationStrategy.getDefault()
				: strategy;
		this.facilities.trace.exit("setStrategy");
	}

	/**
	 * Validates the provided object without a context.
	 * 
	 * @param objClass
	 *            an instance of a {@link Class} class, the same class that the
	 *            object should be.
	 * @param obj
	 *            an instance of an object, from the same class that objClass
	 *            indicates, that will be validated.
	 * @return an instance of the {@link Validation} class that will indicate
	 *         that the validation have passed.
	 * @throws ValidationException
	 *             when the validation is done with an exception based
	 *             validation strategy.
	 */
	public <E> Validation validate(Class<E> objClass, E obj) {
		this.facilities.trace.enter("validate", objClass, obj);
		Validation result = validate(objClass, obj, NoContext.instance);
		this.facilities.trace.exit("validate", result);
		return result;
	}

	/**
	 * Validates the provided object with a context.
	 * 
	 * @param objClass
	 *            an instance of a {@link Class} class, the same class that the
	 *            object should be.
	 * @param obj
	 *            an instance of an object, from the same class that objClass
	 *            indicates, that will be validated.
	 * @param context
	 *            an instance of an object that stands as a context during the
	 *            validation.
	 * @return an instance of the {@link Validation} class that will indicate
	 *         that the validation have passed.
	 * @throws ValidationException
	 *             when the validation is done with an exception based
	 *             validation strategy.
	 */
	public <E> Validation validate(Class<E> objClass, E obj, Object context) {
		this.facilities.trace.enter("validate", objClass, obj, context);
		List<Evaluation> allEvaluations;
		Iterable<Evaluation> currentEvaluations;
		Iterable<? extends Evaluator> evaluators;

		evaluators = evaluatorProvider.get(objClass, context);
		evaluators = strategy.apply(evaluators);

		allEvaluations = new ArrayList<Evaluation>();
		for (Evaluator evaluator : evaluators) {
			currentEvaluations = evaluator.evaluate(obj, context);
			addAllToList(allEvaluations, currentEvaluations);
		}
		Validation result = new Validation(allEvaluations);
		this.facilities.trace.exit("validate", result);
		return result;
	}
}
