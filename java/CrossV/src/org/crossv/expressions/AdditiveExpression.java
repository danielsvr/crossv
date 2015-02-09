package org.crossv.expressions;

public abstract class AdditiveExpression extends BinaryExpression {
	protected Class<?> resultClass = null;
	
	public AdditiveExpression(Expression left, Expression right) {
		super(left, right);
	}

	void setResultClass(Class<?> resultClass) {
		this.resultClass = resultClass;
	}
	
	@Override
	public Class<?> getResultClass() {
		return this.resultClass;
	}
}
