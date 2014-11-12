package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.validIf;
import static org.crossv.expressions.Expression.memberAccess;
import static org.crossv.expressions.Expression.instance;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.EvaluatorDescriptor;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ValidIfExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createValidIfExpression_NullScope_ThrowsIllegalOperandException() {
		Expression scope = null;
		Expression test = constant(true);
		String ifFalseMessage = "message";
		validIf(scope, test, ifFalseMessage);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createValidIfExpression_NullTest_ThrowsIllegalOperandException() {
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = null;
		String ifFalseMessage = "error";
		validIf(scope, test, ifFalseMessage);
	}

	@Test
	public void createValidIfExpression_AnyOperands_ReturnClassIsEvaluatorDescriptor() {
		Class<?> expectedClass = EvaluatorDescriptor.class;
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createValidIfExpression_callingToString_getsJavaLikeExpression() {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		assertThat(e.toString(), is("obj.nickname validif true else \"error\""));
	}

	@Test
	public void evaluateValidIfExpression_ForInstanceNickname_ReturnsDescriptorWithScopeTextNickname()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluatorDescriptor descriptor = (EvaluatorDescriptor) e.evaluate();
		assertThat(descriptor.getScopeText(), is(equalTo("nickname")));
	}

	@Test
	public void evaluateValidIfExpression_WithErrorAsIfFalseMessage_ReturnsDescriptorWithIfFalseMessageError()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluatorDescriptor descriptor = (EvaluatorDescriptor) e.evaluate();
		assertThat(descriptor.getIfFalseMessage(), is(equalTo("error")));
	}

	@Test
	public void evaluateValidIfExpression_WithConstantBooleanAsTest_ReturnsDescriptorWithTestSetToBooleanConstant()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluatorDescriptor descriptor = (EvaluatorDescriptor) e.evaluate();
		assertThat(descriptor.getTest(), is(equalTo(test)));
	}
}
