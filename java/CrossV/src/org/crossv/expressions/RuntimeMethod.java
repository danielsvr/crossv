package org.crossv.expressions;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.CObject;

import java.lang.reflect.InvocationTargetException;

import org.crossv.primitives.ClassDescriptor;
import org.crossv.primitives.MemberDescriptor;

public class RuntimeMethod extends MemberDescriptor {
	private String name;
	private Expression instance;

	public RuntimeMethod(Expression instance, String name) {
		this.instance = instance;
		this.name = name;
	}
	
	@Override
	public boolean isMethod() {
		return true;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<?> getDeclaringClass() {
		return CObject;
	}

	@Override
	public Class<?> getMemberClass() {
		return CObject;
	}

	public Expression getInstance() {
		return instance;
	}
	
	@Override
	public Object invoke(Object instance, Object... parameters)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		if(instance == null)
			throw new NullPointerException();
		Class<?> instanceClass = instance.getClass();
		ClassDescriptor desctiptor;
		desctiptor = new ClassDescriptor(instanceClass);
		MemberDescriptor member = desctiptor.findMember(name);
		if (member == null || !member.isMethod()) {
			String message = "Cannot invoke \"{0}\" member.";
			message = format(message, name);
			throw new IllegalAccessException(message);
		}
		return member.invoke(instance, parameters);
	}
}
