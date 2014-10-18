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
		Class<?> instanceClass = instance.getResultClass();
		Class<?> declaringClass;
		Class<?> memberClass;
		int memberParametersCount = 0;
		if (member instanceof Field) {
			Field field = (Field) member;
			declaringClass = field.getDeclaringClass();
			memberClass = field.getType();
		} else {
			Method method = (Method) member;
			declaringClass = method.getDeclaringClass();
			memberClass = method.getReturnType();
			memberParametersCount = method.getParameterTypes().length;
		}

		if (!declaringClass.isAssignableFrom(instanceClass)
				|| memberClass.equals(Void.TYPE) || memberParametersCount != 0)
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

	@Override
	public void accept(ExpressionVisitor visitor) {
		visitor.visitMemberAccess(this);
	}

	public Expression getInstance() {
		return instance;
	}

	public AccessibleObject getMember() {
		return member;
	}
}
