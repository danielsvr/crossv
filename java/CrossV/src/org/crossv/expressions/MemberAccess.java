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
		init(instance, member);
	}

	public MemberAccess(Expression instance, String memberName) {
		AccessibleObject member = findMember(instance.getResultClass(),
				memberName);
		if (member == null
				&& (instance instanceof Instance || instance instanceof Context))
			member = new RuntimeMember(instance, memberName);
		init(instance, member);
	}

	private void init(Expression instance, AccessibleObject member) {
		if (instance == null)
			throw new ArgumentNullException("instance");
		if (member == null)
			throw new ArgumentNullException("member");
		this.instance = instance;
		this.member = member;
		verifyOperands();
		resultClass = calculateResultClass();
	}

	private static AccessibleObject findMember(Class<?> instanceClass,
			String member) {
		ClassDescriptor descriptor;
		descriptor = new ClassDescriptor(instanceClass);

		return descriptor.findMember(member);
	}

	private void verifyOperands() {
		Class<?> memberDeclaringClass = null;
		Class<?> memberReturnClass = null;

		if (member instanceof RuntimeMember) {
			memberDeclaringClass = Object.class;
			memberReturnClass = Object.class;
		} else if (member instanceof Field) {
			Field field = (Field) member;
			memberDeclaringClass = field.getDeclaringClass();
			memberReturnClass = field.getType();
		} else {
			Method method = (Method) member;
			memberDeclaringClass = method.getDeclaringClass();
			memberReturnClass = method.getReturnType();
			int memberParametersCount = method.getParameterTypes().length;
			if (memberParametersCount > 0)
				throw illegalOperand();
		}
		Class<?> instaceClass = instance.getResultClass();

		if (!memberDeclaringClass.isAssignableFrom(instaceClass)
				|| memberReturnClass.equals(Void.TYPE))
			throw illegalOperand();
	}

	public Class<?> calculateResultClass() {
		if (member instanceof RuntimeMember)
			return Object.class;
		if (member instanceof Field)
			return ((Field) member).getType();
		return ((Method) member).getReturnType();
	}

	@Override
	public Class<?> getResultClass() {
		return resultClass;
	}

	public Expression getInstance() {
		return instance;
	}

	public AccessibleObject getMember() {
		return member;
	}

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitMemberAccess(this);
	}
}
