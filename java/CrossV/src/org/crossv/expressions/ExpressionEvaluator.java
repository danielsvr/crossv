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
import static org.crossv.primitives.ClassDescriptor.CByte;
import static org.crossv.primitives.ClassDescriptor.CShort;
import static org.crossv.primitives.ClassDescriptor.CRuntimeObject;
import static org.crossv.primitives.ClassDescriptor.getNumericPromotion;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;
import static org.crossv.primitives.Iterables.count;
import static org.crossv.primitives.Iterables.elementAt;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

import org.crossv.primitives.ArgumentException;
import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ConvertibleTo;
import org.crossv.primitives.MemberDescriptor;
import org.crossv.primitives.RuntimeMember;

public abstract class ExpressionEvaluator implements ExpressionEvaluationScope {

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
	private EvaluationOptions options;

	protected ExpressionEvaluator(Object instance, Object context) {
		this(instance, context, new EvaluationOptions());
	}

	protected ExpressionEvaluator() {
		this(NO_INSTANCE, NO_CONTEXT, new EvaluationOptions());
	}

	protected ExpressionEvaluator(Object instance, Object context,
			EvaluationOptions options) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (context == null)
			throw new ArgumentNullException("context");
		this.instance = instance;
		this.context = context;
		this.options = options;
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

	@SuppressWarnings("unchecked")
	public <E> E getValue() throws EvaluationException {
		int stackSize = stack.size();
		if (stackSize != 1) {
			String message = "Incorrect evaluation.";
			if (options.printStackCount) {
				message += format("\nThere are {0} value on the stack.",
						stackSize);
				message += "\n";
			}
			if (options.printStackValues) {
				for (int i = 0; i < stackSize; i++) {
					Object value = stack.pop();
					message += format("\nStack value at {0} depth: {1}",
							stackSize - i, value);
				}
				message += "\n";
			}
			throw new EvaluationException(message);
		}
		return (E) stack.pop();
	}

	public Object getInstance() {
		return instance;
	}

	public Object getContext() {
		return context;
	}

	private EvaluationException evalError(RuntimeEvaluationException e) {
		String trail = " Please inspect cause for more details.";
		trail = e.getCause() != null ? trail : "";
		return new EvaluationException(e.getMessage() + trail, e.getCause());
	}

	private void evaluateAdditivity(AdditiveExpression expression, EvalOp op) {
		if (!op.isAdditivityOp()) {
			String message;
			message = "Valid op values: " + EvalOp.getAdditivityOpsToString();
			throw new ArgumentException("op", message);
		}
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CRuntimeObject)) {
			Class<? extends Object> leftClass = leftPop.getClass();
			Class<? extends Object> rightClass = rightPop.getClass();
			
			Class<?> numericPromotion = getNumericPromotion(leftClass, rightClass);
			expression.setResultClass(numericPromotion);
		} 
		
		if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else { // EvalOp.SUBTRACT
				stack.push(left - right);
			}
		} else if (expression.isAssignableTo(CLong)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else { // EvalOp.SUBTRACT
				stack.push(left - right);
			}
		} else if (expression.isAssignableTo(CFloat)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else { // EvalOp.SUBTRACT
				stack.push(left - right);
			}
		} else if (expression.isAssignableTo(CDouble)) {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			if (op == EvalOp.PLUS)
				stack.push(left + right);
			else {// EvalOp.SUBTRACT
				stack.push(left - right);
			}
		} else {// EvalOp.PLUS
			// @formatter:off
			String left = leftPop == null ? "null" : leftPop.toString();
			String right = rightPop == null ? "null" : rightPop.toString();
			stack.push(left + right);
			// @formatter:on
		}
	}

	private void evaluateBitwise(BitwiseExpression expression, EvalOp op) {
		if (!op.isBitwiseOp()) {
			String message;
			message = "Valid op values: " + EvalOp.getBitwiseOpsToString();
			throw new ArgumentException("op", message);
		}
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
			else {// EvalOp.XOR
				stack.push(left ^ right);
			}
		} else if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.AND)
				stack.push(left & right);
			else if (op == EvalOp.OR)
				stack.push(left | right);
			else {// EvalOp.XOR
				stack.push(left ^ right);
			}
		} else {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.AND)
				stack.push(left & right);
			else if (op == EvalOp.OR)
				stack.push(left | right);
			else {// EvalOp.XOR
				stack.push(left ^ right);
			}
		}
	}

	private void evaluateConditionalBinary(
			ConditionalBinaryExpression expression, EvalOp op) {
		if (!op.isBinaryConditionalOp()) {
			String message;
			message = "Valid op values: "
					+ EvalOp.getBinaryConditionalOpsToString();
			throw new ArgumentException("op", message);
		}

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
		if (!op.isEqualityOp()) {
			String message;
			message = "Valid op values: " + EvalOp.getEqualityOpsToString();
			throw new ArgumentException("op", message);
		}
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (op == EvalOp.EQUAL)
			stack.push((leftPop == null && rightPop == null)
					|| (leftPop != null && leftPop.equals(rightPop)));
		else {// EvalOp.NOT_EQUAL
			stack.push((leftPop == null && rightPop != null)
					|| (leftPop != null && !leftPop.equals(rightPop)));
		}
	}

	private void evaluateMultiplicity(MultiplicityExpression expression,
			EvalOp op) {
		if (!op.isMultiplicityOp()) {
			String message;
			message = "Valid op values: " + EvalOp.getMultiplicityOpsToString();
			throw new ArgumentException("op", message);
		}

		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			int right = ((Number) rightPop).intValue();
			if (op == EvalOp.DIVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else {// if EvalOp.MULTIPLY
				stack.push(left * right);
			}
		} else if (expression.isAssignableTo(CLong)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.DIVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else {// if EvalOp.MULTIPLY
				stack.push(left * right);
			}
		} else if (expression.isAssignableTo(CFloat)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			if (op == EvalOp.DIVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else {// if EvalOp.MULTIPLY
				stack.push(left * right);
			}
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			if (op == EvalOp.DIVIDE)
				stack.push(left / right);
			else if (op == EvalOp.MODULO)
				stack.push(left % right);
			else {// if EvalOp.MULTIPLY
				stack.push(left * right);
			}
		}
	}

	private void evaluateNumericalComparison(
			NumericalComparisonExpression expression, EvalOp op) {
		if (!op.isNumericalComparisonOp()) {
			String message;
			message = "Valid op values: "
					+ EvalOp.getNumericalComparisonOpsToString();
			throw new ArgumentException("op", message);
		}
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		Class<?> leftClass = leftPop.getClass();
		Class<?> rightClass = rightPop.getClass();
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
			else {// EvalOp.LESS_THAN_OR_EQUAL
				stack.push(left <= right);
			}
		} else if (CLong.isAssignableFrom(promotion)) {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else {// EvalOp.LESS_THAN_OR_EQUAL
				stack.push(left <= right);
			}
		} else if (CFloat.isAssignableFrom(promotion)) {
			float left = ((Number) leftPop).floatValue();
			float right = ((Number) rightPop).floatValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else {// EvalOp.LESS_THAN_OR_EQUAL
				stack.push(left <= right);
			}
		} else {
			double left = ((Number) leftPop).doubleValue();
			double right = ((Number) rightPop).doubleValue();
			if (op == EvalOp.GREATER_THAN)
				stack.push(left > right);
			else if (op == EvalOp.GREATER_THAN_OR_EQUAL)
				stack.push(left >= right);
			else if (op == EvalOp.LESS_THAN)
				stack.push(left < right);
			else {// EvalOp.LESS_THAN_OR_EQUAL
				stack.push(left <= right);
			}
		}
	}

	private void evaluateShift(ShiftExpression expression, EvalOp op) {
		if (!op.isShiftOp()) {
			String message;
			message = "Valid op values: " + EvalOp.getShiftOpsToString();
			throw new ArgumentException("op", message);
		}
		eval(expression.getLeft());
		eval(expression.getRight());

		Object rightPop = stack.pop();
		Object leftPop = stack.pop();
		if (expression.isAssignableTo(CInteger)) {
			int left = ((Number) leftPop).intValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.LEFT_SHIFT)
				stack.push(left << right);
			else {// EvalOp.RIGHT_SHIFT
				stack.push(left >> right);
			}
		} else {
			long left = ((Number) leftPop).longValue();
			long right = ((Number) rightPop).longValue();
			if (op == EvalOp.LEFT_SHIFT)
				stack.push(left << right);
			else {// EvalOp.RIGHT_SHIFT
				stack.push(left >> right);
			}
		}
	}

	public void eval(Expression expression) {
		expression.accept(visitor);
	}

	public void evaluateAdd(Add expression) {
		evaluateAdditivity(expression, EvalOp.PLUS);
	}

	public void evaluateAnd(And expression) {
		evaluateBitwise(expression, EvalOp.AND);
	}

	public void evaluateAndAlso(AndAlso expression) {
		evaluateConditionalBinary(expression, EvalOp.AND_ALSO);
	}

	public void evaluateCall(Call expression) {
		eval(expression.getInstance());
		Object instance = stack.pop();
		Iterable<Expression> paramExpressions = expression.getParameters();
		List<Object> params = new ArrayList<Object>();
		for (Expression param : paramExpressions) {
			eval(param);
			params.add(stack.pop());
		}
		try {
			MemberDescriptor member = expression.getMethod();
			Object value = member.invoke(instance, params);
			stack.push(value);
		} catch (IllegalAccessException e) {
			throw new RuntimeEvaluationException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeEvaluationException(e);
		}
	}

	public void evaluateCast(Cast expression) {
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

	public void evaluateCoalesce(Coalesce expression) {
		evaluateConverableExpression(expression);
	}

	public void evaluateConditional(ConditionalTernary expression) {
		eval(expression.getTest());
		Object popedValue = stack.pop();
		if (popedValue.equals(true))
			eval(expression.getIfTrue());
		else
			eval(expression.getIfFalse());
	}

	public void evaluateConstant(Constant expression) {
		stack.push(expression.getValue());
	}

	public void evaluateContext(Context expression) {
		if (context instanceof Missing) {
			String message = "Context value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(context);
	}

	public void evaluateDivide(Divide expression) {
		evaluateMultiplicity(expression, EvalOp.DIVIDE);
	}

	public void evaluateEqual(Equal expression) {
		evaluateEquality(expression, EvalOp.EQUAL);
	}

	public void evaluateGreaterThan(GreaterThan expression) {
		evaluateNumericalComparison(expression, EvalOp.GREATER_THAN);
	}

	public void evaluateGreaterThanOrEqual(GreaterThanOrEqual expression) {
		evaluateNumericalComparison(expression, EvalOp.GREATER_THAN_OR_EQUAL);
	}

	public void evaluateInstance(Instance expression) {
		if (instance instanceof Missing) {
			String message = "Instance value is missing.";
			NullPointerException cause = new NullPointerException(message);
			throw new RuntimeEvaluationException(cause);
		}
		stack.push(instance);
	}

	public void evaluateInstanceOf(InstanceOf expression) {
		eval(expression.getLeft());
		eval(expression.getRight());

		Class<?> rightPop = (Class<?>) stack.pop();
		Object leftPop = stack.pop();
		stack.push(leftPop != null ? rightPop.isAssignableFrom(leftPop
				.getClass()) : false);
	}

	public void evaluateLeftShift(LeftShift expression) {
		evaluateShift(expression, EvalOp.LEFT_SHIFT);
	}

	public void evaluateLessThan(LessThan expression) {
		evaluateNumericalComparison(expression, EvalOp.LESS_THAN);
	}

	public void evaluateLessThanOrEqual(LessThanOrEqual expression) {
		evaluateNumericalComparison(expression, EvalOp.LESS_THAN_OR_EQUAL);
	}

	public void evaluateModulo(Modulo expression) {
		evaluateMultiplicity(expression, EvalOp.MODULO);
	}

	public void evaluateMultiply(Multiply expression) {
		evaluateMultiplicity(expression, EvalOp.MULTIPLY);
	}

	public void evaluateNegate(Negate expression) {
		eval(expression.getOperand());

		Object opPop = stack.pop();
		if (expression.isAssignableToAny(CInteger, CShort, CByte)) {
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

	public void evaluateNot(Not expression) {
		eval(expression.getOperand());
		boolean op = (Boolean) stack.pop();
		stack.push(!op);
	}

	public void evaluateNotEqual(NotEqual expression) {
		evaluateEquality(expression, EvalOp.NOT_EQUAL);
	}

	public void evaluateOr(Or expression) {
		evaluateBitwise(expression, EvalOp.OR);
	}

	public void evaluateOrElse(OrElse expression) {
		evaluateConditionalBinary(expression, EvalOp.OR_ELSE);
	}

	public void evaluateUnaryPlus(UnaryPlus expression) {
		eval(expression.getOperand());
	}

	public void evaluateRightShift(RightShift expression) {
		evaluateShift(expression, EvalOp.RIGHT_SHIFT);
	}

	public void evaluateSubtract(Subtract expression) {
		evaluateAdditivity(expression, EvalOp.SUBTRACT);
	}

	public void evaluateUnrecognizedExpresison(Expression expression) {
		Class<?> expressionClass = expression.getClass();
		String message = "Unrecognized expression of type {0}.";
		message = format(message, expressionClass.getName());
		throw new RuntimeEvaluationException(message);
	}

	public void evaluateXor(Xor expression) {
		evaluateBitwise(expression, EvalOp.XOR);
	}

	public void evaluateComplement(Complement expression) {
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

	public void evaluateSequenceLength(SequenceLength expression) {
		eval(expression.getOperand());
		Expression operand = expression.getOperand();
		if (!operand.isKnownOnlyAtRuntime() && !operand.isArray()
				&& !operand.isAssignableToAny(CString, CIterable, CEnumeration))
			throw new RuntimeEvaluationException("Invalid operand type!");

		Object value = stack.pop();
		if (value instanceof Enumeration) {
			Enumeration<?> enumeration = (Enumeration<?>) value;
			stack.push(count(enumeration));
		} else if (value instanceof String) {
			String string = (String) value;
			stack.push(string.length());
		} else if (value instanceof Iterable) {
			Iterable<?> iterable = (Iterable<?>) value;
			stack.push(count(iterable));
		} else if (value.getClass().isArray()) {
			stack.push(Array.getLength(value));
		} else {
			throw new RuntimeEvaluationException("Invalid operand type!");
		}
	}

	public void evaluateSequenceIndex(SequenceIndex expression) {
		Expression sequence = expression.getSequence();
		Expression index = expression.getIndex();
		if (!sequence.isKnownOnlyAtRuntime()
				&& !sequence.isArray()
				&& !sequence
						.isAssignableToAny(CString, CIterable, CEnumeration))
			throw new RuntimeEvaluationException("Invalid operand type!");

		eval(index);
		int indexValue = (Integer) stack.pop();

		eval(sequence);
		Object values = stack.pop();

		if (values instanceof Enumeration) {
			Enumeration<?> iterable = (Enumeration<?>) values;
			stack.push(elementAt(iterable, indexValue));
		} else if (values instanceof String) {
			String string = (String) values;
			stack.push(string.charAt(indexValue));
		} else if (values instanceof Iterable) {
			Iterable<?> iterable = (Iterable<?>) values;
			stack.push(elementAt(iterable, indexValue));
		} else if (values.getClass().isArray()) {
			stack.push(Array.get(values, indexValue));
		} else
			throw new RuntimeEvaluationException("Invalid operand type!");
	}

	public void evaluateMemberAccess(MemberAccess expression) {
		try {
			Object value = null;
			MemberDescriptor member = expression.getMember();
			if (member instanceof RuntimeMember) {
				RuntimeMember runtimeMember = (RuntimeMember) member;
				eval(runtimeMember.getInstance());
				Object instance = stack.pop();
				value = runtimeMember.invoke(instance);
			} else {
				eval(expression.getInstance());
				Object instance = stack.pop();
				value = member.invoke(instance);
			}
			stack.push(value);
		} catch (IllegalAccessException e) {
			throw new RuntimeEvaluationException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeEvaluationException(e);
		}
	}

	public void evaluateValidIf(ValidIf expression) {
		Expression scope = expression.getScope();
		Expression test = expression.getTest();
		Expression ifFalse = expression.getIfFalse();

		String scopeText = null;
		if (scope instanceof Constant && scope.isAssignableTo(CString)) {
			eval(scope);
			Object scopeValue = stack.pop();
			scopeText = (String) scopeValue;
		} else if (scope instanceof MemberAccess) {
			MemberAccess access = (MemberAccess) scope;
			MemberDescriptor member = access.getMember();
			String name = member.getName();
			scopeText = name;
		} else if (scope instanceof Call) {
			Call call = (Call) scope;
			MemberDescriptor method = call.getMethod();
			scopeText = method.getName();
		}

		EvaluationDescriptor descriptor;
		descriptor = new EvaluationDescriptor(scopeText, test, null, ifFalse);
		descriptor.isValidation(true);
		stack.push(descriptor);
	}

	public void evaluateWarnIf(WarnIf expression) {
		Expression scopeE = expression.getScope();
		Expression test = expression.getTest();
		Expression ifTrue = expression.getIfTrue();

		String scopeText = null;
		if (scopeE instanceof Constant && scopeE.isAssignableTo(CString)) {
			eval(scopeE);
			Object popedScope = stack.pop();
			scopeText = (String) popedScope;
		} else if (scopeE instanceof MemberAccess) {
			MemberAccess access = (MemberAccess) scopeE;
			MemberDescriptor member = access.getMember();
			String name = member.getName();
			scopeText = name;
		} else if (scopeE instanceof Call) {
			Call call = (Call) scopeE;
			MemberDescriptor method = call.getMethod();
			scopeText = method.getName();
		}

		EvaluationDescriptor descriptor;
		descriptor = new EvaluationDescriptor(scopeText, test, ifTrue, null);
		descriptor.isWarning(true);
		stack.push(descriptor);
	}

	public void evaluateWhen(When expression) {
		Expression scope;
		AndAlso andScope;
		InstanceOf firstScopeExp;
		InstanceOf additionalScopeExp;
		Expression[] evals;

		Class<?> objClass = null;
		Class<?> contextClass = null;

		scope = expression.getScope();

		if (scope instanceof AndAlso) {
			andScope = (AndAlso) scope;
			firstScopeExp = (InstanceOf) andScope.getLeft();
			additionalScopeExp = (InstanceOf) andScope.getRight();
			if (additionalScopeExp.getLeft() instanceof Instance) {
				eval(additionalScopeExp.getRight());
				objClass = (Class<?>) stack.pop();
			} else if (additionalScopeExp.getLeft() instanceof Context) {
				eval(additionalScopeExp.getRight());
				contextClass = (Class<?>) stack.pop();
			}
		} else
			firstScopeExp = (InstanceOf) scope;

		if (firstScopeExp.getLeft() instanceof Instance) {
			eval(firstScopeExp.getRight());
			objClass = (Class<?>) stack.pop();
		} else if (firstScopeExp.getLeft() instanceof Context) {
			eval(firstScopeExp.getRight());
			contextClass = (Class<?>) stack.pop();
		}

		evals = expression.getEvaluators();
		IterableExpressionEvaluators evaluators;
		evaluators = new IterableExpressionEvaluators(objClass, contextClass,
				evals, this);
		stack.push(evaluators);
	}
}