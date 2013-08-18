package org.crossv.strategies;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.crossv.Evaluator;
import org.crossv.primitives.Lists;

public abstract class ValidationStrategy {
	public final static ValidationStrategy BY_CONTEXT = createValidationByContext();
	public final static ValidationStrategy EXCEPTION_BY_CONTEXT = createExceptionBasedValidationStrategy();

	private static final String BYCONTEXT_LITERAL = "BYCONTEXT";
	private static final String STRATEGY_LITERAL = "strategy";
	private static final String DEFAULT_STRATEGY_PROPERTY = "default_strategy";

	private static final List<String> BY_CONTEXT_LITERALS = Lists.of(
			BYCONTEXT_LITERAL, "BY_CONTEXT");

	private static final List<String> EXCEPTION_BY_CONTEXT_LITERALS = Lists.of(
			"EXCEPTION", "VALIDATIONEXCEPTION", "EXCEPTIONBYCONTEXT",
			"EXCEPTION_BY_CONTEXT");

	protected ValidationStrategy() {
	}

	public abstract Iterable<? extends Evaluator> apply(
			Iterable<? extends Evaluator> evaluators);

	public static ValidationStrategy getDefault() {
		return getDefault(null);
	}

	public static ValidationStrategy getDefault(InputStream resourceStream) {
		if (resourceStream == null)
			return createExceptionBasedValidationStrategy();
		Class<ValidationStrategy> thisClass;
		Class<?> strategyClass;
		ValidationStrategy strategy = null;
		String strategyName;
		Object strategyProperty;
		Properties properties;

		thisClass = ValidationStrategy.class;
		try {
			properties = new Properties();
			properties.load(resourceStream);

			strategyProperty = properties.get(DEFAULT_STRATEGY_PROPERTY);
			strategyProperty = strategyProperty == null ? properties
					.get(STRATEGY_LITERAL) : strategyProperty;
			strategyName = strategyProperty != null ? strategyProperty
					.toString().toUpperCase() : BYCONTEXT_LITERAL;

			if (BY_CONTEXT_LITERALS.contains(strategyName))
				return createValidationByContext();
			if (EXCEPTION_BY_CONTEXT_LITERALS.contains(strategyName))
				return createExceptionBasedValidationStrategy();
			strategyClass = Class.forName(strategyName);
			strategy = thisClass.cast(strategyClass.newInstance());
			return strategy;

		} catch (Exception e) {
			e.printStackTrace();
			return createExceptionBasedValidationStrategy();
		}
	}

	private static ValidationByCotextStrategy createValidationByContext() {
		return new ValidationByCotextStrategy();
	}

	private static ExceptionBasedValidationByCotextStrategy createExceptionBasedValidationStrategy() {
		return new ExceptionBasedValidationByCotextStrategy();
	}
}
