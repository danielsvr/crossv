package org.crossv.primitives;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.Iterables.empty;
import static org.crossv.primitives.Iterables.toArray;

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

	public final Object invoke(Object instance) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		return invoke(instance, empty());
	}

	public Object invoke(Object instance, Iterable<Object> parameters)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		if (member instanceof Method) {
			Method method = (Method) member;

			Object[] params = toArray(parameters, new Object[0]);
			return method.invoke(instance, params);
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
