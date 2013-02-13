package org.crossv.primitives;

import static java.text.MessageFormat.format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassDescriptor<E> {
	private final Class<E> clazz;
	private Method[] methods;

	public ClassDescriptor(Class<E> clazz) {
		if (clazz == null)
			throw new ArgumentNullException("clazz");
		this.clazz = clazz;
		this.methods = clazz.getMethods();
	}

	public Object execute(E obj, Method method, Object... parameters)
			throws IllegalAccessException, InvocationTargetException {
		Object result = method.invoke(obj, parameters);
		if (!method.getReturnType().equals(Void.class))
			return result;
		return null;
	}

	public Object execute(E obj, String method, Object... parameters)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Method bestOverload = findBestOverload(method, parameters);
		return execute(obj, bestOverload, parameters);
	}

	private Method findBestOverload(String method, Object[] parameters)
			throws NoSuchMethodException {
		for (Method m : methods) {
			if (!m.getName().equals(method))
				continue;
			Class<?>[] methodParameterTypes = m.getParameterTypes();
			if (methodParameterTypes.length == parameters.length) {
				Class<?> methodParameterType;
				Class<?> parameterType;
				boolean isOk = true;
				for (int i = 0; i < methodParameterTypes.length; i++) {
					methodParameterType = methodParameterTypes[i];
					if (methodParameterType.isPrimitive()
							&& parameters[i] == null) {
						isOk = false;
						break;
					}
					parameterType = parameters[i] != null ? parameters[i]
							.getClass() : Object.class;
					if (!methodParameterType.isAssignableFrom(parameterType)) {
						isOk = false;
						break;
					}
				}
				if (isOk)
					return m;
			}
		}
		throw new NoSuchMethodException(format("Can't find method {0}", method));
	}
}
