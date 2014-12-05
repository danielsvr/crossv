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

import org.crossv.expressions.Call;
import org.crossv.expressions.Constant;
import org.crossv.expressions.EvaluationDescriptor;
import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.MemberAccess;
import org.crossv.expressions.WarnIf;
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
	public void parseWarnIfExpression_InstGetNameMethodWarnIfTrueElseError_ScopeIsCall() {
		WarnIf e = WarnIf.parse("obj.getName() warnif true then \"warn\"");
		Expression scope = e.getScope();
		assertThat(scope.getClass(), is(assignableTo(Call.class)));
	}

	@Test
	public void parseWarnIfExpression_CustomTextWarnIfTrueElseError_ScopeConstantValueIsCustomText() {
		WarnIf e = WarnIf.parse("\"Custom text\" warnif true then \"warn\"");
		Constant scope = (Constant) e.getScope();
		assertThat(scope.getValue(), is(equalTo("Custom text")));
	}

	@Test
	public void parseWarnIfExpression_InstNicknameMemberWarnIfTrueElseError_ScopeIsMemberAccess() {
		WarnIf e = WarnIf.parse("obj.nickname warnif true then \"warn\"");
		Expression scope = e.getScope();
		assertThat(scope.getClass(), is(assignableTo(MemberAccess.class)));
	}

	@Test
	public void parseWarnIfExpression_InstNicknameMemberWarnIfTrueElseError_TestIsConstantTrue() {
		WarnIf e = WarnIf.parse("obj.nickname warnif true then \"warn\"");
		Constant test = (Constant) e.getTest();
		assertThat(test.getValue(), is(equalTo(true)));
	}

	@Test
	public void parseWarnIfExpression_InstNicknameMemberWarnIfTrueElseError_IfTrueIsConstantError() {
		WarnIf e = WarnIf.parse("obj.nickname warnif true then \"warn\"");
		Constant isFalse = (Constant) e.getIfTrue();
		assertThat(isFalse.getValue(), is(equalTo("warn")));
	}

	@Test
	public void evaluateParsedWarnIfExpression_InstNicknameMemberWarnIfTrueElseError_EvaluationDescriptorScopeIsNickname()
			throws Exception {
		WarnIf e = WarnIf.parse("obj.nickname warnif true then \"warn\"");
		EvaluationDescriptor value = e.evaluate();
		assertThat(value.getScopeDescription(), is(equalTo("nickname")));
	}

	@Test
	public void evaluateParsedWarnIfExpression_InstGetNameMethodWarnIfTrueElseError_EvaluationDescriptorScopeIsGetName()
			throws Exception {
		WarnIf e = WarnIf.parse("obj.getName() warnif true then \"warn\"");
		EvaluationDescriptor value = e.evaluate();
		assertThat(value.getScopeDescription(), is(equalTo("getName")));
	}

	@Test
	public void createWarnIfExpressionForMemberAccess_callingToString_getsWarnIfStringExpression() {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifTrueMessage = "warning";
		Expression e = warnIf(scope, test, ifTrueMessage);
		assertThat(e.toString(),
				is("obj.nickname warnif true then \"warning\""));
	}

	@Test
	public void createWarnIfExpressionForCall_callingToString_getsWarnIfStringExpression()
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
