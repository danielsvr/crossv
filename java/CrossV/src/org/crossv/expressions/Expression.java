package org.crossv.expressions;

import static org.crossv.expressions.ExpressionEvaluator.NO_CONTEXT;
import static org.crossv.expressions.ExpressionEvaluator.NO_INSTANCE;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

public abstract class Expression {

	@Override
	public final String toString() {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		print(writer);
		return stringWriter.toString();
	}

	public void print() {
		PrintWriter writer = new PrintWriter(System.out);
		print(writer);
	}

	public void print(PrintWriter writer) {
		print(new ExpressionWriter(writer));
	}

	public void print(ExpressionWriter writer) {
		writer.print(this);
	}

	public Object evaluate() throws EvaluationException {
		return evaluate(NO_INSTANCE);
	}

	public Object evaluate(Object instance) throws EvaluationException {
		return evaluate(instance, NO_CONTEXT);
	}

	public Object evaluate(Object instance, Object context)
			throws EvaluationException {
		ExpressionEvaluator evaluator;
		instance = instance != null ? instance : NO_INSTANCE;
		evaluator = new ExpressionEvaluator(instance, context);
		return evaluateWith(evaluator);
	}

	public Object evaluateWith(ExpressionEvaluator evaluator)
			throws EvaluationException {
		evaluator.evaluate(this);
		return evaluator.getValue();
	}

	public abstract Class<?> getResultClass();

	protected boolean isNullConstant() {
		return false;
	}

	protected boolean isAssignableTo(Class<?> clazz) {
		Class<?> resultClass = getResultClass();
		return clazz.isAssignableFrom(resultClass);
	}

	protected boolean isAssignableToAny(Class<?> clazz1, Class<?> clazz2) {
		if (isAssignableTo(clazz1))
			return true;
		if (isAssignableTo(clazz2))
			return true;
		return false;
	}

	protected boolean isAssignableToAny(Class<?> clazz1, Class<?> clazz2,
			Class<?> clazz3) {
		if (isAssignableToAny(clazz1, clazz2))
			return true;
		if (isAssignableTo(clazz3))
			return true;
		return false;
	}

	protected boolean isAssignableToAny(Class<?> clazz1, Class<?> clazz2,
			Class<?> clazz3, Class<?> clazz4) {
		if (isAssignableToAny(clazz1, clazz2, clazz3))
			return true;
		if (isAssignableTo(clazz4))
			return true;
		return false;
	}

	protected static IllegalOperandException illegalOperand() {
		return new IllegalOperandException();
	}

	protected boolean returnsPrimitiveType() {
		return isAssignableToAny(Void.TYPE, Number.class, Character.class,
				Boolean.class);
	}

	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}

	public static Expression constant(Object value) {
		return new Constant(value);
	}

	public static Expression cast(Class<?> clazz, Object value) {
		return cast(clazz, new Constant(value));
	}

	public static Expression cast(Class<?> clazz, Expression value) {
		return new Cast(clazz, value);
	}

	public static Expression and(Expression left, Expression right) {
		return new AndAlso(left, right);
	}

	public static Expression and(Boolean left, Boolean right) {
		return and(constant(left), constant(right));
	}

	public static Expression and(Boolean left, Expression right) {
		return and(constant(left), right);
	}

	public static Expression and(Expression left, Boolean right) {
		return and(left, constant(right));
	}

	public static Expression equal(Expression left, Expression right) {
		return new Equal(left, right);
	}

	public static Expression equal(Object left, Object right) {
		return equal(constant(left), constant(right));
	}

	public static Expression equal(Expression left, Object right) {
		return equal(left, constant(right));
	}

	public static Expression equal(Object left, Expression right) {
		return equal(constant(left), right);
	}

	public static Expression greaterThan(Expression left, Expression right) {
		return new GreaterThan(left, right);
	}

	public static Expression greaterThan(Object left, Object right) {
		return greaterThan(constant(left), constant(right));
	}

	public static Expression greaterThan(Expression left, Object right) {
		return greaterThan(left, constant(right));
	}

	public static Expression greaterThan(Object left, Expression right) {
		return greaterThan(constant(left), right);
	}

	public static Expression greaterThanOrEqual(Expression left,
			Expression right) {
		return new GreaterThanOrEqual(left, right);
	}

	public static Expression greaterThanOrEqual(Object left, Object right) {
		return greaterThanOrEqual(constant(left), constant(right));
	}

	public static Expression greaterThanOrEqual(Expression left, Object right) {
		return greaterThanOrEqual(left, constant(right));
	}

	public static Expression greaterThanOrEqual(Object left, Expression right) {
		return greaterThanOrEqual(constant(left), right);
	}

	public static Expression instanceOf(Expression left, Expression right) {
		return new InstanceOf(left, right);
	}

	public static Expression instanceOf(Object left, Class<?> right) {
		return instanceOf(constant(left), constant(right));
	}

	public static Expression instanceOf(Expression left, Class<?> right) {
		return instanceOf(left, constant(right));
	}

	public static Expression instanceOf(Object left, Expression right) {
		return instanceOf(constant(left), right);
	}

	public static Expression lessThan(Expression left, Expression right) {
		return new LessThan(left, right);
	}

	public static Expression lessThan(Object left, Object right) {
		return lessThan(constant(left), constant(right));
	}

	public static Expression lessThan(Expression left, Object right) {
		return lessThan(left, constant(right));
	}

	public static Expression lessThan(Object left, Expression right) {
		return lessThan(constant(left), right);
	}

	public static Expression lessThanOrEqual(Expression left, Expression right) {
		return new LessThanOrEqual(left, right);
	}

	public static Expression lessThanOrEqual(Object left, Object right) {
		return lessThanOrEqual(constant(left), constant(right));
	}

	public static Expression lessThanOrEqual(Expression left, Object right) {
		return lessThanOrEqual(left, constant(right));
	}

	public static Expression lessThanOrEqual(Object left, Expression right) {
		return lessThanOrEqual(constant(left), right);
	}

	public static Expression notEqual(Expression left, Expression right) {
		return new NotEqual(left, right);
	}

	public static Expression notEqual(Object left, Object right) {
		return notEqual(constant(left), constant(right));
	}

	public static Expression notEqual(Expression left, Object right) {
		return notEqual(left, constant(right));
	}

	public static Expression notEqual(Object left, Expression right) {
		return notEqual(constant(left), right);
	}

	public static Expression or(Expression left, Expression right) {
		return new OrElse(left, right);
	}

	public static Expression or(Boolean left, Boolean right) {
		return or(constant(left), constant(right));
	}

	public static Expression or(Boolean left, Expression right) {
		return or(constant(left), right);
	}

	public static Expression or(Expression left, Boolean right) {
		return or(left, constant(right));
	}

	public static Expression instance() {
		return new Instance();
	}

	public static Expression context() {
		return new Context();
	}

	public static Expression context(Class<?> clazz) {
		return new Context(clazz);
	}

	public static Expression call(Object instance, String methodName,
			Object... parameters) throws NoSuchMethodException {
		Expression[] params = new Expression[parameters.length];
		for (int i = 0; i < params.length; i++)
			params[i] = constant(parameters[i]);

		return call(constant(instance), methodName, params);
	}

	public static Expression call(Object instance, String methodName,
			Expression... parameters) throws NoSuchMethodException {
		return call(constant(instance), methodName, parameters);
	}

	public static Expression call(Expression instance, String methodName,
			Expression... parameters) throws NoSuchMethodException {
		return new Call(instance, methodName, parameters);
	}

	public static Expression call(Expression instance, Method method,
			Expression... parameters) throws NoSuchMethodException {
		return new Call(instance, method, parameters);
	}

	public static Expression negate(Expression instance) {
		return new Negate(instance);
	}

	public static Expression negate(byte instance) {
		return negate(constant(instance));
	}

	public static Expression negate(short instance) {
		return negate(constant(instance));
	}

	public static Expression negate(int instance) {
		return negate(constant(instance));
	}

	public static Expression negate(long instance) {
		return negate(constant(instance));
	}

	public static Expression negate(float instance) {
		return negate(constant(instance));
	}

	public static Expression negate(double instance) {
		return negate(constant(instance));
	}

	public static Expression plus(Expression instance) {
		return new UnaryPlus(instance);
	}

	public static Expression plus(byte instance) {
		return plus(constant(instance));
	}

	public static Expression plus(short instance) {
		return plus(constant(instance));
	}

	public static Expression plus(int instance) {
		return plus(constant(instance));
	}

	public static Expression plus(long instance) {
		return plus(constant(instance));
	}

	public static Expression plus(float instance) {
		return plus(constant(instance));
	}

	public static Expression plus(double instance) {
		return plus(constant(instance));
	}

	public static Expression not(Expression operand) {
		return new Not(operand);
	}

	public static Expression not(Boolean operand) {
		return not(constant(operand));
	}

	public static Expression add(Expression left, Expression right) {
		return new Add(left, right);
	}

	public static Expression add(Object left, Object right) {
		return add(constant(left), constant(right));
	}

	public static Expression add(Expression left, Object right) {
		return add(left, constant(right));
	}

	public static Expression add(Object left, Expression right) {
		return add(constant(left), right);
	}

	public static Expression bitwiseAnd(Expression left, Expression right) {
		return new And(left, right);
	}

	public static Expression bitwiseAnd(Object left, Object right) {
		return bitwiseAnd(constant(left), constant(right));
	}

	public static Expression bitwiseAnd(Object left, Expression right) {
		return bitwiseAnd(constant(left), right);
	}

	public static Expression bitwiseAnd(Expression left, Object right) {
		return bitwiseAnd(left, constant(right));
	}

	public static Expression devide(Expression left, Expression right) {
		return new Devide(left, right);
	}

	public static Expression devide(Object left, Object right) {
		return devide(constant(left), constant(right));
	}

	public static Expression devide(Object left, Expression right) {
		return devide(constant(left), right);
	}

	public static Expression devide(Expression left, Object right) {
		return devide(left, constant(right));
	}

	public static Expression bitwiseXor(Expression left, Expression right) {
		return new Xor(left, right);
	}

	public static Expression bitwiseXor(Object left, Object right) {
		return bitwiseXor(constant(left), constant(right));
	}

	public static Expression bitwiseXor(Object left, Expression right) {
		return bitwiseXor(constant(left), right);
	}

	public static Expression bitwiseXor(Expression left, Object right) {
		return bitwiseXor(left, constant(right));
	}

	public static Expression leftShift(Expression left, Expression right) {
		return new LeftShift(left, right);
	}

	public static Expression leftShift(Object left, Object right) {
		return leftShift(constant(left), constant(right));
	}

	public static Expression leftShift(Object left, Expression right) {
		return leftShift(constant(left), right);
	}

	public static Expression leftShift(Expression left, Object right) {
		return leftShift(left, constant(right));
	}

	public static Expression rightShift(Expression left, Expression right) {
		return new RightShift(left, right);
	}

	public static Expression rightShift(Object left, Object right) {
		return rightShift(constant(left), constant(right));
	}

	public static Expression rightShift(Object left, Expression right) {
		return rightShift(constant(left), right);
	}

	public static Expression rightShift(Expression left, Object right) {
		return rightShift(left, constant(right));
	}

	public static Expression bitwiseOr(Expression left, Expression right) {
		return new Or(left, right);
	}

	public static Expression bitwiseOr(Object left, Object right) {
		return bitwiseOr(constant(left), constant(right));
	}

	public static Expression bitwiseOr(Object left, Expression right) {
		return bitwiseOr(constant(left), right);
	}

	public static Expression bitwiseOr(Expression left, Object right) {
		return bitwiseOr(left, constant(right));
	}

	public static Expression subtract(Expression left, Expression right) {
		return new Subtract(left, right);
	}

	public static Expression subtract(Object left, Object right) {
		return subtract(constant(left), constant(right));
	}

	public static Expression subtract(Expression left, Object right) {
		return subtract(left, constant(right));
	}

	public static Expression subtract(Object left, Expression right) {
		return subtract(constant(left), right);
	}

	public static Expression multiply(Expression left, Expression right) {
		return new Multiply(left, right);
	}

	public static Expression multiply(Object left, Object right) {
		return multiply(constant(left), constant(right));
	}

	public static Expression multiply(Expression left, Object right) {
		return multiply(left, constant(right));
	}

	public static Expression multiply(Object left, Expression right) {
		return multiply(constant(left), right);
	}

	public static Expression modulo(Expression left, Expression right) {
		return new Modulo(left, right);
	}

	public static Expression modulo(Object left, Object right) {
		return modulo(constant(left), constant(right));
	}

	public static Expression modulo(Expression left, Object right) {
		return modulo(left, constant(right));
	}

	public static Expression modulo(Object left, Expression right) {
		return modulo(constant(left), right);
	}

	public static Expression conditional(Expression test, Expression ifTrue,
			Expression ifFalse) {
		return new ConditionalTernaryExpression(test, ifTrue, ifFalse);
	}

	public static Expression conditional(boolean test, Expression ifTrue,
			Expression ifFalse) {
		return conditional(constant(test), ifTrue, ifFalse);
	}

	public static Expression conditional(Expression test, Object ifTrue,
			Object ifFalse) {
		return conditional(test, constant(ifTrue), constant(ifFalse));
	}

	public static Expression conditional(boolean test, Object ifTrue,
			Object ifFalse) {
		return conditional(constant(test), constant(ifTrue), constant(ifFalse));
	}

	public static Expression coalesce(Expression left, Expression right) {
		return new Coalesce(left, right);
	}

	public static Expression coalesce(Object left, Object right) {
		return coalesce(constant(left), constant(right));
	}

	public static Expression coalesce(Expression left, Object right) {
		return coalesce(left, constant(right));
	}

	public static Expression coalesce(Object left, Expression right) {
		return coalesce(constant(left), right);
	}
}