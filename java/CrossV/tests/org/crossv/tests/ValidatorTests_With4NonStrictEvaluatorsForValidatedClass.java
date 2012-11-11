package org.crossv.tests;

import static org.crossv.tests.helpers.Matchers.*;
import static org.junit.Assert.*;

import org.crossv.ValidationResult;
import org.crossv.Validator;
import org.crossv.tests.subjects.ExtendedConext;
import org.crossv.tests.subjects.IndependentContext1;
import org.crossv.tests.subjects.IndependentContext2;
import org.crossv.tests.subjects.Monkey;
import org.crossv.tests.subjects.TestableMonkeyEvaluator;
import org.crossv.tests.subjects.SuperContext;
import org.junit.Before;
import org.junit.Test;

public class ValidatorTests_With4NonStrictEvaluatorsForValidatedClass {

	private Validator validator;
	private TestableMonkeyEvaluator<SuperContext> evaluator1;
	private TestableMonkeyEvaluator<ExtendedConext> evaluator2;
	private TestableMonkeyEvaluator<IndependentContext1> evaluator3;
	private TestableMonkeyEvaluator<IndependentContext2> evaluator4;
	private Monkey monkey;

	@Before
	public void setup() {
		evaluator1 = new TestableMonkeyEvaluator<SuperContext>(
				SuperContext.class);
		evaluator2 = new TestableMonkeyEvaluator<ExtendedConext>(
				ExtendedConext.class);
		evaluator3 = new TestableMonkeyEvaluator<IndependentContext1>(
				IndependentContext1.class);
		evaluator4 = new TestableMonkeyEvaluator<IndependentContext2>(
				IndependentContext2.class);
		
		monkey = new Monkey();

		validator = new Validator(evaluator1, evaluator2, evaluator3, evaluator4);
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_Returns2EvaluationResults() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasSize(2));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfEvaluator1() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasAll(evaluator1.results));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_ReturnsResultsOfEvaluator2() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasAll(evaluator2.results));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfEvaluator3() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator3.results));
	}

	@Test
	public void validate_MonkeyObjectOnExtendedConext_DoesntReturnResultsOfEvaluator4() {
		Object context = new ExtendedConext();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator4.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_Return1EvaluationResult() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_ReturnResultsOfEvaluator3() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasAll(evaluator3.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator1() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator1.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator2() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator2.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext1_DoesntReturnResultsOfEvaluator4() {
		Object context = new IndependentContext1();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator4.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_Return1EvaluationResult() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasSize(1));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_ReturnResultsOfEvaluator4() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);
		assertThat(validation.getResults(), hasAll(evaluator4.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator1() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator1.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator2() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator2.results));
	}

	@Test
	public void validate_MonkeyObjectOnIndependentContext2_DoesntReturnResultsOfEvaluator3() {
		Object context = new IndependentContext2();
		ValidationResult validation = validator.validate(Monkey.class, monkey, context);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator3.results));
	}


	@Test
	public void validate_MonkeyObjectOnNoContext_Return3EvaluationResult() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), hasSize(3));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator1() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), hasAll(evaluator1.results));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator3() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), hasAll(evaluator3.results));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_ReturnResultsOfEvaluator4() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);
		assertThat(validation.getResults(), hasAll(evaluator4.results));
	}

	@Test
	public void validate_MonkeyObjectOnNoContext_DoesntReturnResultsOfEvaluator2() {
		ValidationResult validation = validator.validate(Monkey.class, monkey);		
		assertThat(validation.getResults(), doesntHaveAny(evaluator2.results));
	}
}