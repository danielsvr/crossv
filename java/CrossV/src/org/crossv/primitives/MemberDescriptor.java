package org.crossv.primitives;

import static java.text.MessageFormat.format;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MemberDescriptor {

	private AccessibleObject member;
	private String name;
	private Class<?> memberClass;
	private Class<?> declaringClass;
	private Class<?>[] methodParameterTypes;

	protected MemberDescriptor() {

	}

	public MemberDescriptor(AccessibleObject reflectedMember) {
		if (reflectedMember == null)
			throw new ArgumentNullException("reflectedMember");
		member = reflectedMember;
		if (member instanceof Method) {
			Method method = (Method) member;
			name = method.getName();
			declaringClass = method.getDeclaringClass();
			memberClass = method.getReturnType();
			methodParameterTypes = method.getParameterTypes();
		} else if (member instanceof Field) {
			Field field = (Field) member;
			name = field.getName();
			declaringClass = field.getDeclaringClass();
			memberClass = field.getType();
		}
	}

	public String getName() {
		return name;
	}

	public Object invoke(Object instance, Object... parameters)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		if (member instanceof Method) {
			Method method = (Method) member;
			return method.invoke(instance, parameters);
		} else if (member instanceof Field) {
			Field field = (Field) member;
			return field.get(instance);
		}

		String message = "Cannot invoke member.";
		if (member != null) {
			message = "Cannot invoke member of type {0}.";
			message = format(message, member.getClass());
		}
		throw new IllegalAccessException(message);
	}

	public Class<?> getDeclaringClass() {
		return declaringClass;
	}

	public Class<?> getMemberClass() {
		return memberClass;
	}

	public boolean isMethod() {
		return member instanceof Method;
	}

	public int getParametersCount() {
		return methodParameterTypes.length;
	}

	public Method toMethod() {
		return (Method) member;
	}
}
