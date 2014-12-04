package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.equal;
import static org.crossv.expressions.Expressions.memberAccess;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.crossv.tests.helpers.Matchers.throwing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.AccessibleObject;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.primitives.Action;
import org.crossv.tests.helpers.TestObjectFactory;
import org.crossv.tests.subjects.Monkey;
import org.junit.Test;

public class MemberAccessExpressionTests {

	@Test
	public void createMemberAccess_WithNullInstanceAndSomeMember_ThrowsIllegalArgumentException() {
		assertThat(new Action() {
			public void run() throws Exception {
				memberAccess(null, Monkey.class.getField("nickname"));
			}
		}, is(throwing(IllegalArgumentException.class)));
	}

	@Test
	public void createMemberAccess_WithSomeInstanceAndNullMember_ThrowsIllegalArgumentException() {
		assertThat(new Action() {
			public void run() throws Exception {
				memberAccess(constant("instance"), (AccessibleObject) null);
			}
		}, is(throwing(IllegalArgumentException.class)));

	}

	@Test
	public void createMemberAccessGetRelativesAsListMethodOfMonkeyClassExpressionForString_ThrowsIllegalOperandException() {
		assertThat(new Action() {
			public void run() throws Exception {
				memberAccess(constant("123"),
						Monkey.class.getMethod("getRelativesAsList"));
			}
		}, is(throwing(IllegalOperandException.class)));

	}

	@Test
	public void createMemberAccessGetRelativeByIndexExpressionForMonkey_ThrowsIllegalOperandException() {
		assertThat(new Action() {
			public void run() throws Exception {
				Monkey monkney = TestObjectFactory.createMonkey();

				memberAccess(monkney, Monkey.class.getMethod(
						"getRelativeByIndex", Integer.TYPE));
			}
		}, is(throwing(IllegalOperandException.class)));
	}

	@Test
	public void createMemberAccessGetNameForInstance_ReturnClassIsObject()
			throws Exception {
		Expression e = memberAccess(instance(), "Name");
		assertThat(e.getResultClass(), is(equalTo(Object.class)));
	}

	// TODO create tests for parsing call expression text
	@Test
	public void createMemberAccessGetNameForInstance_callingToString_getsFieldAccessLikeExpression()
			throws Exception {
		Expression e = memberAccess(instance(), "Name");
		assertThat(e.toString(), is("obj.Name"));
	}

	@Test
	public void createMemberAccessNicknameExpressionForMonkeyContext_callingToString_getsFieldAccessLikeExpression()
			throws Exception {
		Expression e = memberAccess(context(Monkey.class), "nickname");
		assertThat(e.toString(), is("context.nickname"));
	}

	@Test
	public void createMemberAccessBytesExpressionForString_callingToString_getsFieldAccessLikeExpression()
			throws Exception {
		Expression e = memberAccess("123", "Bytes");
		assertThat(e.toString(), is("\"123\".getBytes()"));
	}

	@Test
	public void createMemberAccessNameExpressionForMonkeyContext_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = equal(memberAccess(context(Monkey.class), "name"),
				"name");
		assertThat(e.toString(), is("context.getName() == \"name\""));
	}

	@Test
	public void createMemberAccessNameExpressionForAnonymousContext_callingToString_getsJavaLikeExpression()
			throws Exception {
		Expression e = equal(memberAccess(context(), "name"), "name");
		assertThat(e.toString(), is("context.name == \"name\""));
	}

	@Test
	public void evaluateMemberAccessNicknameExpressionForAnonymousInstance_InstanceKnownAtRuntimeAsMonkey_ReturnsTheNickname()
			throws Exception {
		Expression e = memberAccess(instance(), "nickname");
		Monkey instance = new Monkey();
		instance.nickname = "Runtime known value";
		Object result = e.evaluate(instance);
		assertThat(result, is(equalTo("Runtime known value")));
	}

	@Test
	public void evaluateMemberAccessExpression_NameOfMonkeyConstant_ReturnsName()
			throws Exception {
		Monkey monkey = TestObjectFactory.createMonkey();
		monkey.setName("John");
		Expression e = memberAccess(monkey, "Name");
		assertThat(e.evaluate(), is(equalTo("John")));
	}

	@Test
	public void evaluateMemberAccessExpression_GetNameOfMonkeyConstant_ReturnsName()
			throws Exception {
		Monkey monkey = TestObjectFactory.createMonkey();
		monkey.setName("John");
		Expression e = memberAccess(monkey, Monkey.class.getMethod("getName"));
		assertThat(e.evaluate(), is(equalTo("John")));
	}
}
