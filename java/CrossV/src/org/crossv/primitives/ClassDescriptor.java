package org.crossv.primitives;

import static java.text.MessageFormat.format;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class ClassDescriptor {
	private Method[] methods;
	private final static Hashtable<Class<?>, Class<?>> primitiveTypesToClasses = createPrimitiveToClassesTable();

	private static Hashtable<Class<?>, Class<?>> createPrimitiveToClassesTable() {
		Hashtable<Class<?>, Class<?>> table = new Hashtable<Class<?>, Class<?>>();
		table.put(Character.TYPE, Character.class);
		table.put(Boolean.TYPE, Boolean.class);
		table.put(Byte.TYPE, Byte.class);
		table.put(Short.TYPE, Short.class);
		table.put(Integer.TYPE, Integer.class);
		table.put(Long.TYPE, Long.class);
		table.put(Float.TYPE, Float.class);
		table.put(Double.TYPE, Double.class);
		return table;
	}

	private final static Hashtable<Class<?>, Class<?>> primitiveClassesToTypes = createPrimitiveToTypesTable();

	private static Hashtable<Class<?>, Class<?>> createPrimitiveToTypesTable() {
		Hashtable<Class<?>, Class<?>> table = new Hashtable<Class<?>, Class<?>>();
		table.put(Character.class, Character.TYPE);
		table.put(Boolean.class, Boolean.TYPE);
		table.put(Byte.class, Byte.TYPE);
		table.put(Short.class, Short.TYPE);
		table.put(Integer.class, Integer.TYPE);
		table.put(Long.class, Long.TYPE);
		table.put(Float.class, Float.TYPE);
		table.put(Double.class, Double.TYPE);
		return table;
	}

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
					methodParameterType = transformToClassIfPrimitive(methodParameterTypes[i]);
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

	public static Class<?> transformToClassIfPrimitive(Class<?> clazz) {
		if (primitiveTypesToClasses.containsKey(clazz))
			return primitiveTypesToClasses.get(clazz);
		return clazz;
	}
	public static Class<?> transformToTypeIfPrimitive(Class<?> clazz) {
		if (primitiveClassesToTypes.containsKey(clazz))
			return primitiveClassesToTypes.get(clazz);
		return clazz;
	}
}
