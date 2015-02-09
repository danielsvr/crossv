package org.crossv.tests.scenarios.accountTransfer;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.crossv.Evaluation;
import org.crossv.Evaluator;
import org.crossv.Validation;
import org.crossv.Validator;
import org.crossv.expressions.Expression;
import org.crossv.expressions.Expressions;
import org.crossv.strategies.ValidationStrategy;
import org.crossv.tests.scenarios.domain.Account;
import org.crossv.tests.scenarios.domain.User;
import org.junit.Test;

public class BasicAccountTransferScenario {

	// @formatter:off
	Expression expressions = Expressions.parse(
		"when obj instanceof org.crossv.tests.scenarios.domain.Account ["
		+ "obj.Email validif obj.Owner != null else \"The account needs to have an owner.\","
		+ "obj.Email validif obj.Balance != null else \"The account needs to have a balance.\","
		+ "obj.Email validif obj.Number != null else \"The account needs a number.\","
		+ "]\n"
		+ "\n"
		+ "when obj instanceof org.crossv.tests.scenarios.domain.Account "
		+ "		&& context instanceof org.crossv.tests.scenarios.accountTransfer.BasicContext ["
		+ "obj.Email validif context.validatesNumber(obj.Number) else \"The account number does not respect 'CNDDBANCKDDDDDDDDDDDDDDDD' format.\""
		+ "]\n"
		+ "\n"
		+ "when obj instanceof org.crossv.tests.scenarios.domain.Account "
		+ "		&& context instanceof org.crossv.tests.scenarios.accountTransfer.DebitContext ["
		+ "obj.Email warnif obj.Balance - context.Debit < 0.00001d then \"The account goes into credit account type.\""
		+ "]\n"
		+ "\n"
		+ "when obj instanceof org.crossv.tests.scenarios.domain.Account "
		+ "		&& context instanceof org.crossv.tests.scenarios.accountTransfer.StoreContext ["
		+ "obj.Email validif !context.containsNumber(obj.Number) else \"The account number must be unique.\","
		+ "]\n"
		);
	// @formatter:on

	@Test
	public void newAccountBalanceWithNumberAndOwnerAndNoContextValidation()
			throws Exception {

		Iterable<Evaluator> evaluators;
		Validator validator;
		Validation validation;

		evaluators = expressions.evaluate();
		validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		User owner = new User("email@company.com");
		Account account = new Account("US01BANK1234123412341234", owner);
		validation = validator.validate(Account.class, account);

		StringWriter actual = new StringWriter();
		PrintWriter actualWriter = new PrintWriter(actual);
		actualWriter.println("Validation is successfull: "
				+ validation.isSuccessful());
		actualWriter.println("Validation results [");

		for (Evaluation evaluation : validation.getResults()) {
			String className = evaluation.getClass().getSimpleName();
			String message = evaluation.getMessage();
			actualWriter.println(className + ": " + message);
		}
		actualWriter.println("]");

		StringWriter expected = new StringWriter();
		PrintWriter expectedWriter = new PrintWriter(expected);
		// @formatter:off
		expectedWriter.println("Validation is successfull: true");
		expectedWriter.println("Validation results [");
		expectedWriter.println("]");
		// @formatter:on
		assertThat(actual.toString(), is(expected.toString()));
	}

	@Test
	public void newAccountBalanceWithWrongNumberAndAOwnerAndBasicContextValidation()
			throws Exception {

		Iterable<Evaluator> evaluators;
		Validator validator;
		Validation validation;

		evaluators = expressions.evaluate();
		validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		User owner = new User("email@company.com");
		Account account = new Account("WRON_NUMBER", owner);
		validation = validator.validate(Account.class, account, new BasicContext());

		StringWriter actual = new StringWriter();
		PrintWriter actualWriter = new PrintWriter(actual);
		actualWriter.println("Validation is successfull: "
				+ validation.isSuccessful());
		actualWriter.println("Validation results [");

		for (Evaluation evaluation : validation.getResults()) {
			String className = evaluation.getClass().getSimpleName();
			String message = evaluation.getMessage();
			actualWriter.println(className + ": " + message);
		}
		actualWriter.println("]");

		StringWriter expected = new StringWriter();
		PrintWriter expectedWriter = new PrintWriter(expected);
		// @formatter:off
		expectedWriter.println("Validation is successfull: false");
		expectedWriter.println("Validation results [");
		expectedWriter.println("EvaluationFault: The account number does not respect 'CNDDBANCKDDDDDDDDDDDDDDDD' format.");
		expectedWriter.println("]");
		// @formatter:on
		assertThat(actual.toString(), is(expected.toString()));
	}

	@Test
	public void beforeDebit100UnitFromAnValidAccountBalanceWith99UnitsAndDebitContextValidation()
			throws Exception {

		Iterable<Evaluator> evaluators;
		Validator validator;
		Validation validation;

		evaluators = expressions.evaluate();
		validator = new Validator(evaluators);
		validator.setStrategy(ValidationStrategy.BY_CONTEXT);

		User owner = new User("email@company.com");
		Account account = new Account("WRON_NUMBER", owner, 99d);
		DebitContext context = new DebitContext();
		context.setDebit(100d);
		validation = validator.validate(Account.class, account, context);

		StringWriter actual = new StringWriter();
		PrintWriter actualWriter = new PrintWriter(actual);
		actualWriter.println("Validation is successfull: "
				+ validation.isSuccessful());
		
		actualWriter.println("Validation results [");
		for (Evaluation evaluation : validation.getResults()) {
			String className = evaluation.getClass().getSimpleName();
			String message = evaluation.getMessage();
			actualWriter.println(className + ": " + message);
		}
		actualWriter.println("]");

		StringWriter expected = new StringWriter();
		PrintWriter expectedWriter = new PrintWriter(expected);
		// @formatter:off
		expectedWriter.println("Validation is successfull: true");
		expectedWriter.println("Validation results [");
		expectedWriter.println("EvaluationWarning: The account goes into credit account type.");
		expectedWriter.println("]");
		// @formatter:on
		assertThat(actual.toString(), is(expected.toString()));
	}
}
