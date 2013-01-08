package org.crossv.expressions;

import java.io.PrintWriter;
import java.io.StringWriter;

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

	public abstract Class<?> getResultClass();

	public boolean returnsPrimitiveType() {
		Class<?> resultClass = getResultClass();

		if ((resultClass.isPrimitive() && !resultClass.equals(Void.TYPE))
				|| resultClass.equals(Boolean.class)
				|| resultClass.equals(Character.class)
				|| resultClass.equals(Byte.class)
				|| resultClass.equals(Short.class)
				|| resultClass.equals(Integer.class)
				|| resultClass.equals(Long.class)
				|| resultClass.equals(Float.class)
				|| resultClass.equals(Double.class))
			return true;
		return false;
	}

	public void accept(ExpressionVisitor visitor) {
		visitor.visit(this);
	}
	
	protected static void checkOperandClass(Expression expressin, Class<?> clazz) {
		Class<?> resultClass = expressin.getResultClass();
		if (!clazz.isAssignableFrom(resultClass))
			throw new IllegalOperandException();
	}

	protected static void checkIfReturnsPrimitive(Expression expressin) {
		if (!expressin.returnsPrimitiveType())
			throw new IllegalOperandException();
	}

	protected static void checkIfReturnsReference(Expression expressin) {
		if (expressin.returnsPrimitiveType())
			throw new IllegalOperandException();
	}

	public static Expression constant(Object value) {
		return new Constant(value);
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
}