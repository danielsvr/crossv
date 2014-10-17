package org.crossv.expressions;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.CBoolean;
import static org.crossv.primitives.ClassDescriptor.CDouble;
import static org.crossv.primitives.ClassDescriptor.CEnumeration;
import static org.crossv.primitives.ClassDescriptor.CFloat;
import static org.crossv.primitives.ClassDescriptor.CInteger;
import static org.crossv.primitives.ClassDescriptor.CIterable;
import static org.crossv.primitives.ClassDescriptor.CLong;
import static org.crossv.primitives.ClassDescriptor.CNumber;
import static org.crossv.primitives.ClassDescriptor.CString;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;
import static org.crossv.primitives.Iterables.count;
import static org.crossv.primitives.Iterables.elementAt;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Stack;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ConvertibleTo;

public class ExpressionEvaluator {

	private static class Missing {
		public static final Missing VALUE = new Missing();

		private Missing() {
		}
	}

	public static final Object NO_CONTEXT = Missing.VALUE;
	public static final Object NO_INSTANCE = Missing.VALUE;
	private final Object context;
	private final Object instance;
	protected final Stack<Object> stack;
	private final ExpressionVisitor visitor;

	public ExpressionEvaluator(Object instance, Object context) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (context == null)
			throw new ArgumentNullException("context");
		this.instance = instance;
		this.context = context;
		visitor = new ExpressionEvaluationVisitor(this);
		stack = new Stack<Object>();
	}

	public final void evaluate(Expression expression)
			throws EvaluationException {
		try {
			eval(expression);
		} catch (RuntimeEvaluationException e) {
			throw evalError(e);
		}
	}

	public Object getValue() throws EvaluationException {
		if (stack.size() != 1)
			throw new EvaluationException("Incorrect evaluation.");
		return stack.pop();
	}

	private EvaluationException evalError(RuntimeEvaluationException e) {
		String trail = " Please inspect cause for more details.";
		trail = e.getCause() != null ? trail : "";
		return new EvaluationException(e.getMessage() + trail, e.getCause());
	}

	private void evaluateAdditivity(AdditiveExpression expression, EvalOp op) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else if (op == EvalOp.SUBTRACT)
				stack.push(left - right);
		} else if (expression.isAssignableTo(CLong)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else if (op == EvalOp.SUBTRACT)
				stack.push(left - right);
		} else if (expression.isAssignableTo(CFloat)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else if (op == EvalOp.SUBTRACT)
				stack.push(left - right);
		} else if (expression.isAssignableTo(CDouble)) {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else if (op == EvalOp.SUBTRACT)
				stack.push(left - right);
		} else if (op == EvalOp.PLUS) {
			// @formatter:off
			String left = leftPop == null ? "null" : leftPop.toString();
			String right = rightPop == null ? "null" : rightPop.toString();
			stack.push(left + right);
			// @formatter:on
		}
	}

	private void evaluateBitwise(BitwiseExpression expression, EvalOp op) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CBoolean)) {
			boolean left = ((Boolean) leftPop).booleanValue();
			boolean right = ((Boolean) rightPop).booleanValue();
			if (op == EvalOp.AND)
				stack.push(left & right);
			else if (op == EvalOp.OR)
				stack.push(left | right);
			else if (op == EvalOp.XOR)
				stack.push(left ^ right);
		} else if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.AND)
				stack.push(left & right);
			else if (op == EvalOp.OR)
				stack.push(left | right);
			else if (op == EvalOp.XOR)
				stack.push(left ^ right);
		} else {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.AND)
				stack.push(left & right);
			else if (op == EvalOp.OR)
				stack.push(left | right);
			else if (op == EvalOp.XOR)
				stack.push(left ^ right);
		}
	}

	private void evaluateConditionalBinary(
			ConditionalBinaryExpression expression, EvalOp op) {
		eval(expression.getLeft());
		boolean obj = op == EvalOp.AND_ALSO;
		if (stack.peek().equals(obj)) {
			stack.pop();
			eval(expression.getRight());
		}
	}

	private <E extends Expression> void evaluateConverableExpression(
			ConvertibleTo<E> expression) {
		E converted = expression.convert();
		eval(converted);
	}

	private void evaluateEquality(EqualityExpression expression, EvalOp op) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (op == EvalOp.EQUAL)
			stack.push((leftPop == null && rightPop == null)
					|| (leftPop != null && leftPop.equals(rightPop)));
		else if (op == EvalOp.NOT_EQUAL)
			stack.push((leftPop == null && rightPop != null)
					|| (leftPop != null && !leftPop.equals(rightPop)));
	}

	private void evaluateMultiplicity(MultiplicityExpression expression,
			EvalOp op) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.DEVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else if (op == EvalOp.MULTIPLY)
				stack.push(left * right);
		} else if (expression.isAssignableTo(CLong)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.DEVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else if (op == EvalOp.MULTIPLY)
				stack.push(left * right);
		} else if (expression.isAssignableTo(CFloat)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			if (op == EvalOp.DEVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else if (op == EvalOp.MULTIPLY)
				stack.push(left * right);
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			if (op == EvalOp.DEVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else if (op == EvalOp.MULTIPLY)
				stack.push(left * right);
		}
	}

	private void evaluateNumericalComparison(
			NumericalComparisonExpression expression, EvalOp op) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		Class<?> leftClass = expression.getLeft().getResultClass();
		Class<?> rightClass = expression.getRight().getResultClass();
		Class<?> promotion = getNumericPromotion(leftClass, rightClass);
		if (CInteger.isAssignableFrom(promotion)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else if (op == EvalOp.LESS_THAN_OR_EQUAL)
				stack.push(left <= right);
		} else if (CLong.isAssignableFrom(promotion)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else if (op == EvalOp.LESS_THAN_OR_EQUAL)
				stack.push(left <= right);
		} else if (CFloat.isAssignableFrom(promotion)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else if (op == EvalOp.LESS_THAN_OR_EQUAL)
				stack.push(left <= right);
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else if (op == EvalOp.LESS_THAN_OR_EQUAL)
				stack.push(left <= right);
		}
	}

	private void evaluateShift(ShiftExpression expression, EvalOp op) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.LEFT_SHIFT)
				stack.push(left << right);
			else if (op == EvalOp.RIGHT_SHIFT)
				stack.push(left >> right);
		} else {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.LEFT_SHIFT)
				stack.push(left << right);
			else if (op == EvalOp.RIGHT_SHIFT)
				stack.push(left >> right);
		}
	}

	protected void eval(Expression expression) {
		expression.accept(visitor);
	}

	protected void evaluateAdd(Add expression) {
		evaluateAdditivity(expression, EvalOp.PLUS);
	}

	protected void evaluateAnd(And expression) {
		evaluateBitwise(expression, EvalOp.AND);
	}

	protected void evaluateAndAlso(AndAlso expression) {
		evaluateConditionalBinary(expression, EvalOp.AND_ALSO);
	}

	protected void evaluateCall(Call expression) {
		eval(expression.getInstance());
		Object instance = stack.pop();
		Expression[] paramExpressions = expression.getParameters();
		Object[] params = new Object[paramExpressions.length];
		for (int i = 0; i < params.length; i++) {
			eval(paramExpressions[i]);
			params[i] = stack.pop();
		}
		try {
			Method method = expression.getMethod();
			Object value = method.invoke(instance, params);
			stack.push(value);
		} catch (IllegalAccessException e) {
			throw new RuntimeEvaluationException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeEvaluationException(e);
		}
	}

	protected void evaluateCast(Cast expression) {
		eval(expression.getOperand());
		Class<?> resultClass = expression.getResultClass();
		Class<?> transformedResultClass;
		transformedResultClass = transformToTypeIfPrimitive(resultClass);
		char chr = 0;
		Object popedValue = stack.pop();
		boolean isChr = popedValue instanceof Character;
		Number no = null;
		boolean isNo = popedValue instanceof Number;
		boolean isBool = popedValue instanceof Boolean;

		if ((isChr || isNo) && transformedResultClass.equals(Boolean.TYPE)) {
			Class<?> evaluatedClass = popedValue.getClass();
			evaluatedClass = transformToTypeIfPrimitive(evaluatedClass);
			String message = "Cannot cast a {0} value to boolean.";
			message = format(message, evaluatedClass.getName());
			Throwable cause = new ClassCastException(message);
			throw new RuntimeEvaluationException(cause);
		}

		if (isChr)
			chr = ((Character) popedValue).charValue();
		if (isNo)
			no = (Number) popedValue;

		if ((isChr || isNo) && transformedResultClass.equals(Character.TYPE))
			stack.push(isChr ? (char) chr : (char) no.intValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Byte.TYPE))
			stack.push(isChr ? (byte) chr : no.byteValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Short.TYPE))
			stack.push(isChr ? (short) chr : no.shortValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Integer.TYPE))
			stack.push(isChr ? (int) chr : no.intValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Long.TYPE))
			stack.push(isChr ? (long) chr : no.longValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Float.TYPE))
			stack.push(isChr ? (float) chr : no.floatValue());
		else if ((isChr || isNo) && transformedResultClass.equals(Double.TYPE))
			stack.push(isChr ? (double) chr : no.doubleValue());
		else if (isBool && transformedResultClass.equals(Character.TYPE)
				|| CNumber.isAssignableFrom(resultClass)) {
			String message = "Cannot cast a boolean value to {0}.";
			message = format(message, transformedResultClass.getName());
			Throwable cause = new ClassCastException(message);
			throw new RuntimeEvaluationException(cause);
		} else if (isBool && transformedResultClass.equals(Boolean.TYPE)) {
			stack.push(popedValue);
		} else
			try {
				stack.push(transformedResultClass.cast(popedValue));
			} catch (ClassCastException e) {
				Class<?> evaluatedClass = popedValue.getClass();
				String message = "Cannot cast a {0} value to {1}.";
				message = format(message, evaluatedClass.getName(),
						transformedResultClass.getName());
				throw new RuntimeEvaluationException(message, e);
			}
	}

	protected void evaluateCoalesce(Coalesce expression) {
		evaluateConverableExpression(expression);
	}

	protected void evaluateConditional(ConditionalTernaryExpression expression) {
		eval(expression.getTest());
		Object popedValue = stack.pop();
		if (popedValue.equals(true))
			eval(expression.getIfTrue());
		else
			eval(expression.getIfFalse());
	}

	protected void evaluateConstant(Constant expression) {
		stack.push(expression.getValue());
	}

	protected void evaluateContext(Context expression) {
		if (context instanceof Missing) {
			String message = "Context value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(context);
	}

	protected void evaluateDevide(Devide expression) {
		evaluateMultiplicity(expression, EvalOp.DEVIDE);
	}

	protected void evaluateEqual(Equal expression) {
		evaluateEquality(expression, EvalOp.EQUAL);
	}

	protected void evaluateGreaterThan(GreaterThan expression) {
		evaluateNumericalComparison(expression, EvalOp.GREATER_THAN);
	}

	protected void evaluateGreaterThanOrEqual(GreaterThanOrEqual expression) {
		evaluateNumericalComparison(expression, EvalOp.GREATER_THAN_OR_EQUAL);
	}

	protected void evaluateInstance(Instance expression) {
		if (instance instanceof Missing) {
			String message = "Instance value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(instance);
	}

	protected void evaluateInstanceOf(InstanceOf expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Class<?> rightPop = (Class<?>) stack.pop();
		Object leftPop = stack.pop();
		stack.push(leftPop != null ? rightPop.isAssignableFrom(leftPop
				.getClass()) : false);
	}

	protected void evaluateLeftShift(LeftShift expression) {
		evaluateShift(expression, EvalOp.LEFT_SHIFT);
	}

	protected void evaluateLessThan(LessThan expression) {
		evaluateNumericalComparison(expression, EvalOp.LESS_THAN);
	}

	protected void evaluateLessThanOrEqual(LessThanOrEqual expression) {
		evaluateNumericalComparison(expression, EvalOp.LESS_THAN_OR_EQUAL);
	}

	protected void evaluateModulo(Modulo expression) {
		evaluateMultiplicity(expression, EvalOp.MODULO);
	}

	protected void evaluateMultiply(Multiply expression) {
		evaluateMultiplicity(expression, EvalOp.MULTIPLY);
	}

	protected void evaluateNegate(Negate expression) {
		eval(expression.getOperand());

		Object opPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int op = ((Number) opPop).intValue();
			stack.push(-op);
		} else if (expression.isAssignableTo(CLong)) {
			long op = ((Number) opPop).longValue();
			stack.push(-op);
		} else if (expression.isAssignableTo(CFloat)) {
			float op = ((Number) opPop).floatValue();
			stack.push(-op);
		} else {
			double op = ((Number) opPop).doubleValue();
			stack.push(-op);
		}
	}

	protected void evaluateNot(Not expression) {
		eval(expression.getOperand());
		boolean op = (Boolean) stack.pop();
		stack.push(!op);
	}

	protected void evaluateNotEqual(NotEqual expression) {
		evaluateEquality(expression, EvalOp.NOT_EQUAL);
	}

	protected void evaluateOr(Or expression) {
		evaluateBitwise(expression, EvalOp.OR);
	}

	protected void evaluateOrElse(OrElse expression) {
		evaluateConditionalBinary(expression, EvalOp.OR_ELSE);
	}

	protected void evaluatePlus(UnaryPlus expression) {
		eval(expression.getOperand());
	}

	protected void evaluateRightShift(RightShift expression) {
		evaluateShift(expression, EvalOp.RIGHT_SHIFT);
	}

	protected void evaluateSubtract(Subtract expression) {
		evaluateAdditivity(expression, EvalOp.SUBTRACT);
	}

	protected void evaluateUnrecognizedExpresison(Expression expression) {
		Class<?> expressionClass = expression.getClass();
		String message = "Unrecognized expression of type {0}.";
		message = format(message, expressionClass.getName());
		throw new RuntimeEvaluationException(message);
	}

	protected void evaluateXor(Xor expression) {
		evaluateBitwise(expression, EvalOp.XOR);
	}

	protected void evaluateComplement(Complement expression) {
		eval(expression.getOperand());

		Object opPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int op = ((Number) opPop).intValue();
			stack.push(~op);
		} else {
			long op = ((Number) opPop).longValue();
			stack.push(~op);
		}
	}

	protected void evaluateSequenceLength(SequenceLength expression) {
		eval(expression.getOperand());
		Expression opExp = expression.getOperand();
		Object opPop = stack.pop();
		if (opExp.isArray()) {
			stack.push(Array.getLength(opPop));
		} else if (opExp.isAssignableTo(CString)) {
			String string = (String) opPop;
			stack.push(string.length());
		} else if (opExp.isAssignableTo(CIterable)) {
			Iterable<?> iterable = (Iterable<?>) opPop;
			stack.push(count(iterable));
		} else if (opExp.isAssignableTo(CEnumeration)) {
			Enumeration<?> enumeration = (Enumeration<?>) opPop;
			stack.push(count(enumeration));
		}
	}

	public void evaluateSequenceIndex(SequenceIndex expression) {
		Expression seqExp = expression.getLeft();
		Expression indexExp = expression.getRight();
		eval(seqExp);
		eval(indexExp);

		int indexPop = (Integer) stack.pop();
		Object seqPop = stack.pop();

		if (seqExp.isArray()) {
			stack.push(Array.get(seqPop, indexPop));
		} else if (seqExp.isAssignableTo(CString)) {
			String string = (String) seqPop;
			stack.push(string.charAt(indexPop));
		} else if (seqExp.isAssignableTo(CIterable)) {
			Iterable<?> iterable = (Iterable<?>) seqPop;
			stack.push(elementAt(iterable, indexPop));
		} else if (seqExp.isAssignableTo(CEnumeration)) {
			Enumeration<?> iterable = (Enumeration<?>) seqPop;
			stack.push(elementAt(iterable, indexPop));
		}
	}
}