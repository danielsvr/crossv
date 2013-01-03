package org.crossv.expressions.tests;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.expressions.Constant;
import org.junit.Test;

public class ExpressionTests {
	@Test
	public void Message_toString_returnsMessage() {
		Constant e;
		Object value;

		value = "blah";
		e = new Constant(value);
		assertThat(e.getValue(), is(value));
	}

	// @Test
	// public void ContidionExpression_() {
	// If e;
	// e = new If(condition, then, _else);
	// }

	// @Test
	// public void BinaryOperation_() {
	// BinaryOperation o;
	// o = new BinaryOperation()
	// }

}
