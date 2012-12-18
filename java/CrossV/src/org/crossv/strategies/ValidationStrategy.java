package org.crossv.strategies;

import java.io.InputStream;
import java.util.Properties;

import org.crossv.Evaluator;

public abstract class ValidationStrategy {
	public final static ValidationStrategy BY_CONTEXT = createValidationByContext();
	public final static ValidationStrategy EXCEPTION_BY_CONTEXT = createExceptionExceptionBasedValidationStrategy();

	protected ValidationStrategy() {
	}

	public abstract Iterable<? extends Evaluator> apply(
			Iterable<? extends Evaluator> evaluators);

	public static ValidationStrategy getDefault() {
		return getDefault(null);
	}

	public static ValidationStrategy getDefault(InputStream resourceStream) {
		if (resourceStream == null)
			return createValidationByContext();

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

			strategyProperty = properties.get("default_strategy");
			strategyProperty = strategyProperty == null ? properties
					.get("strategy") : strategyProperty;
			strategyName = strategyProperty != null ? strategyProperty
					.toString().toUpperCase() : "BYCONTEXT";

			if (strategyName.equals("BYCONTEXT")
					|| strategyName.equals("BY_CONTEXT"))
				return createValidationByContext();
			if (strategyName.equals("EXCEPTION")
					|| strategyName.equals("VALIDATIONEXCEPTION")
					|| strategyName.equals("EXCEPTIONBYCONTEXT")
					|| strategyName.equals("EXCEPTION_BY_CONTEXT"))
				return createExceptionExceptionBasedValidationStrategy();
			strategyClass = Class.forName(strategyName);
			strategy = thisClass.cast(strategyClass.newInstance());
			return strategy;

		} catch (Exception e) {
			e.printStackTrace();
			return createValidationByContext();
		}
	}

	private static ValidationByCotextStrategy createValidationByContext() {
		return new ValidationByCotextStrategy();
	}

	private static ExceptionBasedValidationByCotextStrategy createExceptionExceptionBasedValidationStrategy() {
		return new ExceptionBasedValidationByCotextStrategy();
	}
}
