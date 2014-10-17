package org.crossv.expressions.tests;

import static org.crossv.expressions.Expression.constant;
import static org.crossv.expressions.Expression.context;
import static org.crossv.expressions.Expression.equal;
import static org.crossv.expressions.Expression.memberAccess;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.AccessibleObject;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class MemberAccessExpressionTests {

	@Test(expected = IllegalArgumentException.class)
	public void createMemberAccess_WithNullInstanceAndSomeMember_ThrowsIllegalArgumentException()
			throws Exception {
		memberAccess(null, Monkey.class.getField("nickname"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createMemberAccess_WithSomeInstanceAndNullMember_ThrowsIllegalArgumentException()
			throws Exception {
		memberAccess(constant("instance"), (AccessibleObject) null);
	}

	@Test(expected = IllegalOperandException.class)
	public void createMemberAccessGetRelativesAsListMethodOfMonkeyClassExpressionForString_ThrowsIllegalOperandException()
			throws Exception {
		memberAccess(constant("123"),
				Monkey.class.getMethod("getRelativesAsList"));
	}

	@Test(expected = IllegalOperandException.class)
	public void createMemberAccessGetRelativeByIndexExpressionForMonkey_ThrowsIllegalOperandException()
			throws Exception {
		Monkey monkney = TestObjectFactory.createMonkey();

		memberAccess(constant(monkney),
				Monkey.class.getMethod("getRelativeByIndex", Integer.TYPE));
	}

	@Test
	public void createMemberAccessBytesExpressionForString_callingToString_getsFieldAccessLikeExpression()
			throws Exception {
		Expression e = memberAccess("123", "Bytes");
		assertThat(e.toString(), is("\"123\".bytes"));
	}

	@Test
	public void createMemberAccessNameExpressionForMonkeyContext_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = equal(memberAccess(context(Monkey.class), "name"),
				"name");
		assertThat(e.toString(), is("context.name == \"name\""));
	}

	@Test
	public void evaluateMemberAccessExpression_NameOfMonkeyConstant_ReturnsName()
			throws Exception {
		Monkey monkey = TestObjectFactory.createMonkey();
		monkey.setName("John");
		Expression e = memberAccess(constant(monkey), "Name");
		assertThat(e.evaluate(), is(equalTo("John")));
	}
}
