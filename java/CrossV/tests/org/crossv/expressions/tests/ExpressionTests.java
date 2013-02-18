package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.crossv.expressions.Expression;
import org.junit.Test;

@SuppressWarnings("unused")
public class ExpressionTests {

	@Test
	public void template()
			throws Exception {
		Expression e = null;//exp(params);
		assertThat(e, is(nullValue()));
	}
}
