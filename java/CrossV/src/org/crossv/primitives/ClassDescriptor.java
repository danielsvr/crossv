package org.crossv.primitives;

import static java.text.MessageFormat.format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassDescriptor {
	private Method[] methods;

	public ClassDescriptor(Class<?> clazz) {
		if (clazz == null)
			throw new ArgumentNullException("clazz");
		this.methods = clazz.getMethods();
	}

	public Object execute(Object obj, Method method, Object... parameters)
			throws IllegalAccessException, InvocationTargetException {
		Object result = method.invoke(obj, parameters);
		if (!method.getReturnType().equals(Void.class))
			return result;
		return null;
	}

	public Object execute(Object obj, String method, Object... parameters)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Class<?>[] paramTypes = new Class<?>[parameters.length];
		for (int i = 0; i < parameters.length; i++)
			paramTypes[i] = parameters[i] != null ? parameters[i].getClass()
					: Object.class;

		Method bestOverload = findBestOverload(method, paramTypes);
		return execute(obj, bestOverload, parameters);
	}

	public Method findBestOverload(String method, Class<?>[] paramTypes)
			throws NoSuchMethodException {
		for (Method m : methods) {
			if (!m.getName().equals(method))
				continue;
			Class<?>[] methodParameterTypes = m.getParameterTypes();
			if (methodParameterTypes.length == paramTypes.length) {
				Class<?> methodParameterType;
				Class<?> parameterType;
				boolean isOk = true;
				for (int i = 0; i < methodParameterTypes.length; i++) {
					methodParameterType = methodParameterTypes[i];
					parameterType = paramTypes[i];
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
