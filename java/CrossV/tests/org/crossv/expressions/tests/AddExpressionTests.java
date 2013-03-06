package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.add;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class AddExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createAddExpression_IntAndBooleanOperands_ThrowsIllegalOperandException() {
		add(1, false);
	}

	@Test
	public void createAddExpression_StringAndObjectOperands_ReturnClassIsString() {
		Class<?> expectedClass = String.class;
		Object left = "1";
		Object right = new Object();
		Expression e = add(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAddExpression_ObjectAndStringOperands_ReturnClassIsString() {
		Class<?> expectedClass = String.class;
		Object left = new Object();
		Object right = "1";
		Expression e = add(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAddExpression_NumberOperands_ReturnClassIsNumber() {
		Class<?> expectedClass = Number.class;
		Object left = (int) 1;
		Object right = (byte) 1;
		Expression e = add(left, right);
		assertThat(e.getResultClass(), is(assignableTo(expectedClass)));
	}

	@Test
	public void createAddExpression_callingToString_getsJavaLikeExpression() {
		Expression e = add(1, 2);
		assertThat(e.toString(), is("1 + 2"));
	}
}
