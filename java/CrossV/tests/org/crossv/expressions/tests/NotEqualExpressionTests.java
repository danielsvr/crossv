package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.notEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.subjects.Mouse;
import org.crossv.tests.subjects.Rat;
import org.crossv.tests.subjects.WhiteMouse;
import org.junit.Test;

public class NotEqualExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createNotEqualExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		notEqual(new WhiteMouse(), new Rat());
	}

	@Test
	public void createEqualExpression_SuperAndSubClassOperands_IllegalOperandExceptionIsNotThrown() {
		notEqual(new Mouse(), new Rat());
	}

	@Test
	public void createEqualExpression_SubAndSuperClassOperands_IllegalOperandExceptionIsNotThrown() {
		notEqual(new Rat(), new Mouse());
	}

	@Test
	public void createNotEqualExpression_IntAndIntOperands_ReturnClassIsBoolean() {
		Class<?> expectedClass = Boolean.class;
		Object left = 1;
		Object right = 2;
		Expression e = notEqual(left, right);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createNotEqualExpression_callingToString_getsJavaLikeExpression() {
		Expression e = notEqual(1, 2);
		assertThat(e.toString(), is("1 != 2"));
	}
}
