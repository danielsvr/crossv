package org.crossv.primitives;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.ClassDescriptor.CRuntimeObject;
import static org.crossv.primitives.Iterables.select;
import static org.crossv.primitives.Iterables.toArray;
import static org.crossv.primitives.Iterables.toIterable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.crossv.expressions.Expression;

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
		if (instance == null)
			throw new NullPointerException();
		Object[] parametersArray = toArray(parameters, new Object[0]);
		parameters = toIterable(parametersArray);

		Class<?> instanceClass = instance.getClass();
		ClassDescriptor desctiptor;
		desctiptor = new ClassDescriptor(instanceClass);
		Iterable<Class<?>> paramTypes;
		paramTypes = select(parameters, new Function<Object, Class<?>>() {
			@Override
			public Class<?> eval(Object value) {
				return value != null ? value.getClass() : Object.class;
			}
		});
		Method member = null;
		try {
			member = desctiptor.findBestOverload(name, paramTypes);
		} catch (NoSuchMethodException e) {
			String message = "Cannot invoke \"{0}\" member.";
			message = format(message, name);
			throw new InvocationTargetException(e, message);
		}

		if (member == null) {
			String message = "Cannot invoke \"{0}\" member.";
			message = format(message, name);
			throw new InvocationTargetException(
					new NoSuchMethodException(name), message);
		}
		return member.invoke(instance, parametersArray);
	}
}
