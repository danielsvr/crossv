package org.crossv.tests.scenarios.users;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.crossv.primitives.Iterables.*;

import org.crossv.Evaluation;
import org.crossv.EvaluationFault;
import org.crossv.EvaluationWarning;
import org.crossv.Evaluator;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.expressions.Expression;
import org.crossv.expressions.Expressions;
import org.crossv.primitives.Predicate;
import org.crossv.strategies.ValidationStrategy;
import org.junit.Test;

public class WithEmailAndPasswordRequired_And_FirstLastNameOptionalButWarn {

	Expression expressions = Expressions
			.parse("when obj instanceof org.crossv.tests.scenarios.users.User ["
					+ "obj.Email validif obj.Email != null else \"Email is required.\","
					+ "obj.Password validif obj.Password != null else \"Password is required.\","
					+ "obj.FirstName warnif obj.FirstName == null then \"First name is missing, and is nice to have a name.\","
					+ "obj.LastName warnif obj.LastName == null then \"Last name is missing, and is nice to have a name.\""
					+ "]\n"
					+ "\n"
					+ "when obj instanceof org.crossv.tests.scenarios.users.User "
					+ "		&& context instanceof org.crossv.tests.scenarios.users.HelperContext ["
					+ "obj.Email validif context.validatesEmailPattern(obj.Email) else \"Email does not respect 'email@company.com' pattern.\","
					+ "obj.Password validif context.meetsMinimalComplexity(obj.Password) else \"Password is too week.\","
					+ "]\n");

	@Test
	public void newUserInformationWithoutContext_ProvideEmailAndPassword_ValidationPasses()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"email@company.com", "secret password"));

		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void newUserInformationWithoutContext_ProvideEmailAndPassword_ResultContains2Warnings()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"email@company.com", "secret password"));

		Iterable<EvaluationWarning> warnings = Evaluation
				.filterInWarnings(validation.getResults());

		assertThat(count(warnings), is(2));
	}

	@Test
	public void newUserInformationWithoutContext_ProvideEmailAndPassword_ResultContainsFirstNameWarning()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"email@company.com", "secret password"));

		Iterable<EvaluationWarning> warnings = Evaluation
				.filterInWarnings(validation.getResults());
		boolean containsFirstNameWarning = any(warnings,
				new Predicate<EvaluationWarning>() {
					public boolean eval(EvaluationWarning value) {
						return value
								.getMessage()
								.equals("First name is missing, and is nice to have a name.");
					}
				});
		assertThat(containsFirstNameWarning, is(true));
	}

	@Test
	public void newUserInformationWithoutContext_ProvideEmailAndPassword_ResultContainsLastNameWarning()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"email@company.com", "secret password"));

		Iterable<EvaluationWarning> warnings = Evaluation
				.filterInWarnings(validation.getResults());
		boolean containsLastNameWarning = any(warnings,
				new Predicate<EvaluationWarning>() {
					public boolean eval(EvaluationWarning value) {
						return value
								.getMessage()
								.equals("Last name is missing, and is nice to have a name.");
					}
				});
		assertThat(containsLastNameWarning, is(true));
	}

	@Test
	public void newUserInformationWithHelperContext_ProvideValideEmailAndPassword_ValidationPasses()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"email@company.com", "s3cr3t PAssword!!"), new HelperContext());

		assertThat(validation.isSuccessful(), is(true));
	}

	@Test
	public void newUserInformationWithHelperContext_ProvideValideEmailAndPassword_ValidationPassesWithWarnings()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"email@company.com", "s3cr3t PAssword!!"), new HelperContext());

		Iterable<EvaluationWarning> warnings = Evaluation
				.filterInWarnings(validation.getResults());

		assertThat(count(warnings), is(2));
	}

	@Test
	public void newUserInformationWithHelperContext_ProvideInvalidEmailAndPassword_ValidationFalils()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"emailcompany.com", "secret"), new HelperContext());

		assertThat(validation.isSuccessful(), is(false));
	}

	@Test
	public void newUserInformationWithHelperContext_ProvideInvalideEmailAndPassword_ValidationFsilsWithWarningsAndErrors()
			throws Exception {
		Iterable<Evaluator> evaluators = expressions.evaluate();
		Validator validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		Validation validation = validator.validate(User.class, new User(
				"emailcompany.com", "secret"), new HelperContext());

		Iterable<EvaluationWarning> warnings = Evaluation
				.filterInWarnings(validation.getResults());

		Iterable<EvaluationFault> faults = Evaluation.filterInFaults(validation
				.getResults());

		assertThat(count(faults), is(2));
		assertThat(count(warnings), is(2));
	}
}
