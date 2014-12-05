package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.add;
import static org.crossv.expressions.Expressions.call;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.memberAccess;
import static org.crossv.expressions.Expressions.validIf;
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
import org.crossv.expressions.ValidIf;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class ValidIfExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createValidIfExpression_NullScope_ThrowsIllegalArgumentException() {
		Expression scope = null;
		Expression test = constant(true);
		String ifFalseMessage = "message";
		validIf(scope, test, ifFalseMessage);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createValidIfExpression_NullTest_ThrowsIllegalArgumentException() {
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = null;
		String ifFalseMessage = "error";
		validIf(scope, test, ifFalseMessage);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createValidIfExpression_NullIfFalse_ThrowsIllegalArgumentException() {
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = constant(true);
		Expression ifFalse = null;
		validIf(scope, test, ifFalse);
	}

	@Test(expected = IllegalOperandException.class)
	public void createValidIfExpression_ScopeOtherThanMemberAccessCallOrStringConstant_ThrowsIllegalOperandException() {
		Expression scope = add(1, 2);
		Expression test = constant(true);
		String ifFalseMessage = "error";
		validIf(scope, test, ifFalseMessage);
	}

	@Test(expected = IllegalOperandException.class)
	public void createValidIfExpression_ScopeIsIntConstant_ThrowsIllegalOperandException() {
		Expression scope = constant(1);
		Expression test = constant(true);
		String ifFalseMessage = "error";
		validIf(scope, test, ifFalseMessage);
	}

	@Test
	public void createValidIfExpression_AnyOperands_ReturnClassIsEvaluatorDescriptor() {
		Class<?> expectedClass = EvaluationDescriptor.class;
		Expression scope = memberAccess(new Monkey(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createValidIfExpressionForMemberAccess_callingToString_getsValidIfStringExpression() {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		assertThat(e.toString(), is("obj.nickname validif true else \"error\""));
	}

	@Test
	public void parseValidIfExpression_InstGetNameMethodValidIfTrueElseError_ScopeIsCall() {
		ValidIf e = ValidIf.parse("obj.getName() validif true else \"error\"");
		Expression scope = e.getScope();
		assertThat(scope.getClass(), is(assignableTo(Call.class)));
	}

	@Test
	public void parseValidIfExpression_CustomTextValidIfTrueElseError_ScopeConstantValueIsCustomText() {
		ValidIf e = ValidIf
				.parse("\"Custom text\" validif true else \"error\"");
		Constant scope = (Constant) e.getScope();
		assertThat(scope.getValue(), is(equalTo("Custom text")));
	}

	@Test
	public void parseValidIfExpression_InstNicknameMemberValidIfTrueElseError_ScopeIsMemberAccess() {
		ValidIf e = ValidIf.parse("obj.nickname validif true else \"error\"");
		Expression scope = e.getScope();
		assertThat(scope.getClass(), is(assignableTo(MemberAccess.class)));
	}

	@Test
	public void parseValidIfExpression_InstNicknameMemberValidIfTrueElseError_TestIsConstantTrue() {
		ValidIf e = ValidIf.parse("obj.nickname validif true else \"error\"");
		Constant test = (Constant) e.getTest();
		assertThat(test.getValue(), is(equalTo(true)));
	}

	@Test
	public void parseValidIfExpression_InstNicknameMemberValidIfTrueElseError_IfFalseIsConstantError() {
		ValidIf e = ValidIf.parse("obj.nickname validif true else \"error\"");
		Constant isFalse = (Constant) e.getIfFalse();
		assertThat(isFalse.getValue(), is(equalTo("error")));
	}

	@Test
	public void evaluateParsedValidIfExpression_InstNicknameMemberValidIfTrueElseError_EvaluationDescriptorScopeIsNickname()
			throws Exception {
		ValidIf e = ValidIf.parse("obj.nickname validif true else \"error\"");
		EvaluationDescriptor value = e.evaluate();
		assertThat(value.getScopeDescription(), is(equalTo("nickname")));
	}

	@Test
	public void evaluateParsedValidIfExpression_InstGetNameMethodValidIfTrueElseError_EvaluationDescriptorScopeIsGetName()
			throws Exception {
		ValidIf e = ValidIf.parse("obj.getName() validif true else \"error\"");
		EvaluationDescriptor value = e.evaluate();
		assertThat(value.getScopeDescription(), is(equalTo("getName")));
	}

	@Test
	public void createValidIfExpressionForCall_callingToString_getsValidIfStringExpression()
			throws Exception {
		Expression scope = call(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		assertThat(e.toString(),
				is("obj.nickname() validif true else \"error\""));
	}

	@Test
	public void evaluateValidIfExpressionForMockeyInstance_ForInstanceNickname_ReturnsDescriptorWithScopeTextNickname()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getScopeDescription(), is(equalTo("nickname")));
	}

	@Test
	public void evaluateValidIfExpressionForMockeyInstance_ForDescriptiveScopeText_ReturnsDescriptorWithScopeText()
			throws Exception {
		Expression scope = constant("Scope text");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getScopeDescription(), is(equalTo("Scope text")));
	}

	@Test
	public void evaluateValidIfExpressionForMockeyInstance_CallInstanceGetRelativesAsList_ReturnsDescriptorWithScopeTextGetRelativesAsList()
			throws Exception {
		Expression scope = call(instance(), "getRelativesAsList");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getScopeDescription(),
				is(equalTo("getRelativesAsList")));
	}

	@Test
	public void evaluateValidIfExpressionForMockeyInstance_WithErrorAsIfFalseMessage_ReturnsDescriptorWithIfFalseMessageError()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getIfFalse().evaluate(), is(equalTo("error")));
	}

	@Test
	public void evaluateValidIfExpression_WithConstantBooleanAsTest_ReturnsDescriptorWithTestSetToBooleanConstant()
			throws Exception {
		Expression scope = memberAccess(instance(), "nickname");
		Expression test = constant(true);
		String ifFalseMessage = "error";
		Expression e = validIf(scope, test, ifFalseMessage);
		EvaluationDescriptor descriptor;
		descriptor = (EvaluationDescriptor) e.evaluate(new Monkey());
		assertThat(descriptor.getTest(), is(equalTo(test)));
	}
}
