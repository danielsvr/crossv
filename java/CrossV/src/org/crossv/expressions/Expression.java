package org.crossv.expressions;

public abstract class Expression {

	public abstract String toString();

	public abstract Class<?> getResultClass();

	protected static void checkOperandClass(Expression ex, Class<?> clazz) {
		if (!clazz.isAssignableFrom(ex.getResultClass()))
			throw new IllegalOperandException();
	}

	protected static String getExpressionString(Expression ex) {
		return String.class.equals(ex.getResultClass()) ? "\""
				+ ex.toString() + "\"" : ex.toString();
	}
	
	public static Expression constant(Object value) {
		return new Constant(value);
	}

	public static Expression and(Expression left, Expression right) {
		return new AndAlso(left, right);
	}

	public static Expression and(Object left, Object right) {
		return and(constant(left), constant(right));
	}

	public static Expression and(Object left, Expression right) {
		return and(constant(left), right);
	}

	public static Expression and(Expression left, Object right) {
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
}
