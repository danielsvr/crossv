package org.crossv.primitives;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.CRuntimeObject;

import java.lang.reflect.InvocationTargetException;

import org.crossv.expressions.Expression;

public class RuntimeMember extends MemberDescriptor {
	private String name;
	private Expression instance;

	public RuntimeMember(Expression instance, String name) {
		this.instance = instance;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<?> getDeclaringClass() {
		return CRuntimeObject;
	}

	@Override
	public Class<?> getMemberClass() {
		return CRuntimeObject;
	}

	public Expression getInstance() {
		return instance;
	}

	@Override
	public Object invoke(Object instance, Iterable<Object> parameters)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		ClassDescriptor desctiptor;
		desctiptor = new ClassDescriptor(instance.getClass());
		MemberDescriptor member = desctiptor.findMember(name);
		if (member == null) {
			String message = "Cannot invoke \"{0}\" member.";
			message = format(message, name);
			throw new IllegalAccessException(message);
		}
		return member.invoke(instance);
	}
}
