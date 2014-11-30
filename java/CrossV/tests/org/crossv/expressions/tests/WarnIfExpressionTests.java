package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.add;
import static org.crossv.expressions.Expressions.call;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.memberAccess;
import static org.crossv.expressions.Expressions.warnIf;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.EvaluationDescriptor;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class WarnIfExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createWarnIfExpression_NullScope_ThrowsArgumentOperandException() {
		Expression scope = null;
		Expression test = constant(true);
		String ifTrueMessage = "message";
		warnIf(scope, test, ifTrueMessage);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWarnIfExpression_NullTest_ThrowsArgumentOperandException() {
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = null;
		String ifTrueMessage = "warning";
		warnIf(scope, test, ifTrueMessage);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createWarnIfExpression_NullIfFalse_ThrowsArgumentOperandException() {
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = constant(null);
		Expression ifTrue = null;
		warnIf(scope, test, ifTrue);
	}

	@Test(expected = IllegalOperandException.class)
	public void createWarnIfExpression_ScopeOtherThanMemberAccessCallOrStringConstant_ThrowsIllegalOperandException() {
		Expression scope = add(1, 2);
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		warnIf(scope, test, ifTrueMessage);
	}

	@Test(expected = IllegalOperandException.class)
	public void createWarnIfExpression_ScopeIsIntConstant_ThrowsIllegalOperandException() {
		Expression scope = constant(1);
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		warnIf(scope, test, ifTrueMessage);
	}

	@Test
	public void createWarnIfExpression_AnyOperands_ReturnClassIsEvaluatorDescriptor() {
		Class<?> expectedClass = EvaluationDescriptor.class;
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createWarnIfExpressionForMemberAccess_callingToString_getsJavaLikeExpression() {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		assertThat(e.toString(),
				is("obj.nickname warnif true then \"warning\""));
	}

	@Test
	public void createWarnIfExpressionForCall_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression scope = call(instance(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		assertThat(e.toString(),
				is("obj.nickname() warnif true then \"warning\""));
	}

	@Test
	public void evaluateWarnIfExpressionForMockeyInstance_ForInstanceNickname_ReturnsDescriptorWithScopeTextNickname()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getScopeDescription(), is(equalTo("nickname")));
	}

	@Test
	public void evaluateWarnIfExpressionForMockeyInstance_CallInstanceGetRelativesAsList_ReturnsDescriptorWithScopeTextGetRelativesAsList()
			throws Exception {
		Expression scope = call(instance(), "getRelativesAsList");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getScopeDescription(),
				is(equalTo("getRelativesAsList")));
	}

	@Test
	public void evaluateWarnIfExpressionForMockeyInstance_WithWarningAsIfFalseMessage_ReturnsDescriptorWithIfTrueMessageWarning()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getIfTrue().evaluate(), is(equalTo("warning")));
	}

	@Test
	public void evaluateWarnIfExpression_WithConstantBooleanAsTest_ReturnsDescriptorWithTestSetToBooleanConstant()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getTest(), is(equalTo(test)));
	}

	@Test
	public void evaluateWarnIfExpressionForMockeyInstance_ForDescriptiveScopeText_ReturnsDescriptorWithScopeText()
			throws Exception {
		Expression scope = constant("Scope text");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getScopeDescription(), is(equalTo("Scope text")));
	}

}
