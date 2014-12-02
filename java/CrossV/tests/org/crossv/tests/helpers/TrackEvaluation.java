package org.crossv.tests.helpers;

import java.util.List;

import org.crossv.expressions.Add;
import org.crossv.expressions.And;
import org.crossv.expressions.AndAlso;
import org.crossv.expressions.Call;
import org.crossv.expressions.Cast;
import org.crossv.expressions.Coalesce;
import org.crossv.expressions.Complement;
import org.crossv.expressions.ConditionalTernary;
import org.crossv.expressions.Constant;
import org.crossv.expressions.Context;
import org.crossv.expressions.Divide;
import org.crossv.expressions.Equal;
import org.crossv.expressions.EvaluationException;
import org.crossv.expressions.Expression;
import org.crossv.expressions.ExpressionEvaluator;
import org.crossv.expressions.GreaterThan;
import org.crossv.expressions.GreaterThanOrEqual;
import org.crossv.expressions.Instance;
import org.crossv.expressions.InstanceOf;
import org.crossv.expressions.LeftShift;
import org.crossv.expressions.LessThan;
import org.crossv.expressions.LessThanOrEqual;
import org.crossv.expressions.MemberAccess;
import org.crossv.expressions.Modulo;
import org.crossv.expressions.Multiply;
import org.crossv.expressions.Negate;
import org.crossv.expressions.Not;
import org.crossv.expressions.NotEqual;
import org.crossv.expressions.Or;
import org.crossv.expressions.OrElse;
import org.crossv.expressions.RightShift;
import org.crossv.expressions.SequenceIndex;
import org.crossv.expressions.SequenceLength;
import org.crossv.expressions.Subtract;
import org.crossv.expressions.UnaryPlus;
import org.crossv.expressions.ValidIf;
import org.crossv.expressions.WarnIf;
import org.crossv.expressions.When;
import org.crossv.expressions.Xor;

public class TrackEvaluation extends ExpressionEvaluator {
	public static int evaluatedExpressionsCount;
	public static List<Expression> evaluatedExpressions;

	public TrackEvaluation() {
	}

	public TrackEvaluation(Object instance, Object context) {
		super(instance, context);
	}

	@Override
	public <E> E getValue() throws EvaluationException {
		return super.getValue();
	}

	@Override
	public void evaluateAdd(Add expression) {
		track(expression);
		super.evaluateAdd(expression);
	}

	private void track(Expression expression) {
		evaluatedExpressionsCount++;
		evaluatedExpressions.add(expression);
	}

	@Override
	public void evaluateAnd(And expression) {
		track(expression);
		super.evaluateAnd(expression);
	}

	@Override
	public void evaluateAndAlso(AndAlso expression) {
		track(expression);
		super.evaluateAndAlso(expression);
	}

	@Override
	public void evaluateCall(Call expression) {
		track(expression);
		super.evaluateCall(expression);
	}

	@Override
	public void evaluateCast(Cast expression) {
		track(expression);
		super.evaluateCast(expression);
	}

	@Override
	public void evaluateCoalesce(Coalesce expression) {
		track(expression);
		super.evaluateCoalesce(expression);
	}

	@Override
	public void evaluateConditional(ConditionalTernary expression) {
		track(expression);
		super.evaluateConditional(expression);
	}

	@Override
	public void evaluateConstant(Constant expression) {
		track(expression);
		super.evaluateConstant(expression);
	}

	@Override
	public void evaluateContext(Context expression) {
		track(expression);
		super.evaluateContext(expression);
	}

	@Override
	public void evaluateDivide(Divide expression) {
		track(expression);
		super.evaluateDivide(expression);
	}

	@Override
	public void evaluateEqual(Equal expression) {
		track(expression);
		super.evaluateEqual(expression);
	}

	@Override
	public void evaluateGreaterThan(GreaterThan expression) {
		track(expression);
		super.evaluateGreaterThan(expression);
	}

	@Override
	public void evaluateGreaterThanOrEqual(GreaterThanOrEqual expression) {
		track(expression);
		super.evaluateGreaterThanOrEqual(expression);
	}

	@Override
	public void evaluateInstance(Instance expression) {
		track(expression);
		super.evaluateInstance(expression);
	}

	@Override
	public void evaluateInstanceOf(InstanceOf expression) {
		track(expression);
		super.evaluateInstanceOf(expression);
	}

	@Override
	public void evaluateLeftShift(LeftShift expression) {
		track(expression);
		super.evaluateLeftShift(expression);
	}

	@Override
	public void evaluateLessThan(LessThan expression) {
		track(expression);
		super.evaluateLessThan(expression);
	}

	@Override
	public void evaluateLessThanOrEqual(LessThanOrEqual expression) {
		track(expression);
		super.evaluateLessThanOrEqual(expression);
	}

	@Override
	public void evaluateModulo(Modulo expression) {
		track(expression);
		super.evaluateModulo(expression);
	}

	@Override
	public void evaluateMultiply(Multiply expression) {
		track(expression);
		super.evaluateMultiply(expression);
	}

	@Override
	public void evaluateNegate(Negate expression) {
		track(expression);
		super.evaluateNegate(expression);
	}

	@Override
	public void evaluateNot(Not expression) {
		track(expression);
		super.evaluateNot(expression);
	}

	@Override
	public void evaluateNotEqual(NotEqual expression) {
		track(expression);
		super.evaluateNotEqual(expression);
	}

	@Override
	public void evaluateOr(Or expression) {
		track(expression);
		super.evaluateOr(expression);
	}

	@Override
	public void evaluateOrElse(OrElse expression) {
		track(expression);
		super.evaluateOrElse(expression);
	}

	@Override
	public void evaluateUnaryPlus(UnaryPlus expression) {
		track(expression);
		super.evaluateUnaryPlus(expression);
	}

	@Override
	public void evaluateRightShift(RightShift expression) {
		track(expression);
		super.evaluateRightShift(expression);
	}

	@Override
	public void evaluateSubtract(Subtract expression) {
		track(expression);
		super.evaluateSubtract(expression);
	}

	@Override
	public void evaluateUnrecognizedExpresison(Expression expression) {
		track(expression);
		super.evaluateUnrecognizedExpresison(expression);
	}

	@Override
	public void evaluateXor(Xor expression) {
		track(expression);
		super.evaluateXor(expression);
	}

	@Override
	public void evaluateComplement(Complement expression) {
		track(expression);
		super.evaluateComplement(expression);
	}

	@Override
	public void evaluateSequenceLength(SequenceLength expression) {
		track(expression);
		super.evaluateSequenceLength(expression);
	}

	@Override
	public void evaluateSequenceIndex(SequenceIndex expression) {
		track(expression);
		super.evaluateSequenceIndex(expression);
	}

	@Override
	public void evaluateMemberAccess(MemberAccess expression) {
		track(expression);
		super.evaluateMemberAccess(expression);
	}

	@Override
	public void evaluateValidIf(ValidIf expression) {
		track(expression);
		super.evaluateValidIf(expression);
	}

	@Override
	public void evaluateWarnIf(WarnIf expression) {
		track(expression);
		super.evaluateWarnIf(expression);
	}

	@Override
	public void evaluateWhen(When expression) {
		track(expression);
		super.evaluateWhen(expression);
	}

	@Override
	public ExpressionEvaluator beginScope(Object instance, Object context) {
		return new TrackEvaluation(instance, context);
	}
}