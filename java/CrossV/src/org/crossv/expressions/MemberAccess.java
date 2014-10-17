package org.crossv.expressions;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.ClassDescriptor;

public class MemberAccess extends Expression {
	private Expression instance;
	private AccessibleObject member;
	private Class<?> resultClass;

	public MemberAccess(Expression instance, AccessibleObject member) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (member == null)
			throw new ArgumentNullException("member");
		this.instance = instance;
		this.member = member;
		verifyInstance();
		verifyMember();
		resultClass = calculateResultClass();
	}

	public MemberAccess(Expression instance, String member) {
		this(instance, findMember(instance.getResultClass(), member));
	}

	private static AccessibleObject findMember(Class<?> instanceClass,
			String member) {
		ClassDescriptor descriptor;
		descriptor = new ClassDescriptor(instanceClass);

		return descriptor.findMember(member);
	}

	private void verifyMember() {
		throw illegalOperand();
	}

	private void verifyInstance() {
		throw illegalOperand();
	}

	public Class<?> calculateResultClass() {
		if (member instanceof Field)
			return ((Field) member).getType();
		return ((Method) member).getReturnType();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

}
