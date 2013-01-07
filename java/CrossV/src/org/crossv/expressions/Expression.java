package org.crossv.expressions;

public abstract class Expression {

	public abstract String toString();

	public abstract Class<?> getResultClass();

	public boolean isPrimitive() {
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

	protected static void checkOperandClass(Expression ex, Class<?> clazz) {
		if (!clazz.isAssignableFrom(ex.getResultClass()))
			throw new IllegalOperandException();
	}

	protected static void checkIfReturnsPrimitive(Expression ex) {
		if (!ex.isPrimitive())
			throw new IllegalOperandException();
	}
	
	protected static void checkIfReturnsReference(Expression ex) {
		if (ex.isPrimitive())
			throw new IllegalOperandException();
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

	public static Expression gt(Expression left, Expression right) {
		return new GreaterThan(left, right);
	}

	public static Expression gt(Object left, Object right) {
		return gt(constant(left), constant(right));
	}

	public static Expression gt(Expression left, Object right) {
		return gt(left, constant(right));
	}

	public static Expression gt(Object left, Expression right) {
		return gt(constant(left), right);
	}

	public static Expression ge(Expression left, Expression right) {
		return new GreaterThanOrEqual(left, right);
	}

	public static Expression ge(Object left, Object right) {
		return ge(constant(left), constant(right));
	}

	public static Expression ge(Expression left, Object right) {
		return ge(left, constant(right));
	}

	public static Expression ge(Object left, Expression right) {
		return ge(constant(left), right);
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
}
