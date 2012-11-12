package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.*;
import static org.junit.Assert.*;

import org.crossv.ValidationResult;
import org.crossv.Validator;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.IndependentContext2;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.crossv.tests.subjects.SuperContext;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_With4NonStrictEvaluatorsThatDontReturnErrorsForValidatedClass {

	private Validator validator;
	private TestableMonkeyEvaluator<SuperContext> evaluator1;
	private TestableMonkeyEvaluator<ExtendedConext> evaluator2;
	private TestableMonkeyEvaluator<IndependentContext1> evaluator3;
	private TestableMonkeyEvaluator<IndependentContext2> evaluator4;
	private Monkey monkey;

	@Before
	public void setup() {
		evaluator1 = TestObjectFactory.createMonkeyEvaluator(
				SuperContext.class, "Rule1");
		evaluator2 = TestObjectFactory.createMonkeyEvaluator(
				ExtendedConext.class, "Rule2");
		evaluator3 = TestObjectFactory.createMonkeyEvaluator(
				IndependentContext1.class, "Rule3");
		evaluator4 = TestObjectFactory.createMonkeyEvaluator(
				IndependentContext2.class, "Rule4");

		monkey = TestObjectFactory.createMonkey();

		validator = TestObjectFactory.createValidator(evaluator1, evaluator2,
				evaluator3, evaluator4);
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_Returns2EvaluationResults() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), hasSize(2));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfEvaluator1() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), has(evaluator1.result));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfEvaluator2() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), has(evaluator2.result));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfEvaluator3() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator3.result));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfEvaluator4() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator4.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_Return1EvaluationResult() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_ReturnResultsOfEvaluator3() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), has(evaluator3.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator1() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator1.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator2() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator2.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator4() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator4.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_Return1EvaluationResult() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_ReturnResultsOfEvaluator4() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), has(evaluator4.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator1() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator1.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator2() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator2.result));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator3() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey,
				context);
		assertThat(validation.getResults(), doesntHave(evaluator3.result));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_Return3EvaluationResult() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), hasSize(3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator1() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), has(evaluator1.result));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator3() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), has(evaluator3.result));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator4() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), has(evaluator4.result));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_DoesntReturnResultsOfEvaluator2() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), doesntHave(evaluator2.result));
	}
}