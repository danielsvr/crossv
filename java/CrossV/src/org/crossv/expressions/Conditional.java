package org.crossv.expressions;

import org.crossv.primitives.ArgumentNullException;

public class Conditional extends Expression {
	private Expression test;
	private Expression ifTrue;
	private Expression ifFalse;
	private Class<?> resultClass;

	public Conditional(Expression test, Expression ifTrue, Expression ifFalse) {
		if (test == null)
			throw new ArgumentNullException("test");
		if (ifTrue == null)
			throw new ArgumentNullException("ifTrue");
		if (ifFalse == null)
			throw new ArgumentNullException("ifFalse");

		checkOperandClass(test, Boolean.class);
		if (ifTrue instanceof Constant && ifFalse instanceof Constant
				&& ((Constant) ifTrue).getValue() == null
				&& ((Constant) ifFalse).getValue() == null)
			throw new IllegalOperandException();

		this.resultClass = Object.class;
		Class<?> ifTrueClass = ifTrue.getResultClass();
		Class<?> ifFalseClass = ifFalse.getResultClass();

		if (ifTrueClass.equals(ifFalseClass))
			resultClass = ifTrueClass;
		else if (Byte.class.isAssignableFrom(ifTrueClass)) {
			if (Integer.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Byte.class;
			} else if (Short.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Short.class;
			} else if (Long.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Long.class;
			} else if (Double.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Double.class;
			}
		} else if (Character.class.isAssignableFrom(ifTrueClass)) {
			if (Integer.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Character.class;
			} else if (Long.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Long.class;
			} else if (Double.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Double.class;
			}
		} else if (Short.class.isAssignableFrom(ifTrueClass)) {
			if (Integer.class.isAssignableFrom(ifFalseClass)
					|| Byte.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Short.class;
			} else if (Long.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Long.class;
			} else if (Double.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Double.class;
			}
		} else if (Integer.class.isAssignableFrom(ifTrueClass)) {
			if (Short.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Short.class;
			} else if (Byte.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Byte.class;
			} else if (Character.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Character.class;
			} else if (Long.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Long.class;
			} else if (Double.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Double.class;
			}
		} else if (Double.class.isAssignableFrom(ifTrueClass)) {
			if (Number.class.isAssignableFrom(ifFalseClass)
					|| Character.class.isAssignableFrom(ifFalseClass)) {
				resultClass = Double.class;
			}
		} else {
			if (ifTrue instanceof Constant
					&& ((Constant) ifTrue).getValue() == null)
				resultClass = ifFalseClass;
			else if (ifFalse instanceof Constant
					&& ((Constant) ifFalse).getValue() == null)
				resultClass = ifTrueClass;
		}
		
		this.test = test;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitConditional(this);
	}

	public Expression getTest() {
		return test;
	}

	public Expression getIfTrue() {
		return ifTrue;
	}

	public Expression getIfFalse() {
		return ifFalse;
	}
}
