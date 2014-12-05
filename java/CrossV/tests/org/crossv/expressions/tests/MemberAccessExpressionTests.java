package org.crossv.expressions.tests;

import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.equal;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.memberAccess;
import static org.crossv.tests.helpers.Matchers.assignableTo;
import static org.crossv.tests.helpers.Matchers.equalTo;
import static org.crossv.tests.helpers.Matchers.throwing;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.AccessibleObject;

import org.crossv.expressions.Expression;
import org.crossv.expressions.IllegalOperandException;
import org.crossv.expressions.Instance;
import org.crossv.expressions.MemberAccess;
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
	public void parseMemberAccessExpression_NameOfAInstance_InstnaceIsInstance()
			throws Exception {
		MemberAccess e = MemberAccess.parse("obj.name");
		Expression context = e.getInstance();
		assertThat(context.getClass(), is(assignableTo(Instance.class)));
	}

	@Test
	public void parseMemberAccessExpression_NameOfAContext_MemberNameIsName()
			throws Exception {
		MemberAccess e = MemberAccess.parse("obj.name");
		String name = e.getMember().getName();
		assertThat(name, is("name"));
	}

	@Test
	public void evaluateParsedMemberAccessExpression_NameOfMonkeyInstance_ReturnsValueOfGetName()
			throws Exception {
		Expression e = MemberAccess.parse("obj.name");
		Monkey obj = new Monkey();
		obj.setName("monkey name");
		Object value = e.evaluate(obj);
		assertThat(value, is(equalTo(obj.getName())));
	}

	@Test
	public void evaluateParsedMemberAccessExpression_ClassOfNameOfMonkeyInstance_ReturnsStringClass()
			throws Exception {
		Expression e = MemberAccess.parse("obj.name.class"
		/* same as "obj.getName().getClass()" */);

		// getting the platform specific class/type/etc will be a strongly
		// unrecommended practice.

		Monkey obj = new Monkey();
		obj.setName("monkey name");
		Object value = e.evaluate(obj);
		assertThat(value, is(equalTo(String.class)));
	}

	@Test
	public void evaluateParsedMemberAccessExpression_NicknameOfMonkeyInstance_ReturnsValueOfNickname()
			throws Exception {
		Expression e = MemberAccess.parse("obj.nickname");
		Monkey obj = new Monkey();
		obj.nickname = "monkey nickname";
		Object value = e.evaluate(obj);
		assertThat(value, is(equalTo(obj.nickname)));
	}

	@Test
	public void evaluateParsedMemberAccessExpression_GetNameOfMonkeyInstance_ReturnsValueOfGetName()
			throws Exception {
		Expression e = MemberAccess.parse("obj.getName");
		Monkey obj = new Monkey();
		obj.setName("monkey nickname");
		Object value = e.evaluate(obj);
		assertThat(value, is(equalTo(obj.getName())));
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
