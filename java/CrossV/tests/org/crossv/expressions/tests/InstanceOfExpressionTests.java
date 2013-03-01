package org.crossv.expressions.tests;

import static java.text.MessageFormat.format;
import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.instance;
import static org.crossv.expressions.Expression.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.junit.Test;

public class InstanceOfExpressionTests {

	@Test(expected = IllegalOperandException.class)
	public void createInstanceOfExpression_DifferentClassOperands_IllegalOperandExceptionIsThrown() {
		instanceOf(1, constant(2));
	}

	@Test
	public void createInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression e = instanceOf("1", Integer.class);
		assertThat(e.toString(), is("\"1\" instanceof java.lang.Integer"));
	}

	@Test
	public void createInstanceOfExpression_StringValueAndIntegerClassOperands_ReturnsSameClassAsJava() {
		Class<?> expectedClass = ((Object) (((Object) "1") instanceof Integer))
				.getClass();
		Expression e = instanceOf("1", Integer.class);
		assertThat(format("Result is {0}", expectedClass.getName()), e
				.getResultClass().equals(expectedClass), is(true));
	}

	@Test
	public void createContextInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression e = instanceOf(context(), String.class);
		assertThat(e.toString(), is("context instanceof java.lang.String"));
	}

	@Test
	public void createInstanceInstanceOfExpression_callingToString_getsJavaLikeExpression() {
		Expression e = instanceOf(instance(), String.class);
		assertThat(e.toString(), is("obj instanceof java.lang.String"));
	}
}
