package org.crossv.primitives;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.Strings.toPascalCase;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;

public class ClassDescriptor {
	private Method[] methods;
	private Field[] fields;

	public ClassDescriptor(Class<?> clazz) {
		if (clazz == null)
			throw new ArgumentNullException("clazz");
		this.methods = clazz.getMethods();
		this.fields = clazz.getFields();
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

	public MemberDescriptor findMember(String member) {
		AccessibleObject aproxMatch = null;
		for (Method m : methods) {
			int parameterTypesLength = m.getParameterTypes().length;
			if (parameterTypesLength > 0)
				continue;
			if (m.getName().equals(member))
				return new MemberDescriptor(m);
			else {
				String getMember = toPascalCase(member);
				if (m.getName().equals("get" + getMember)) {
					aproxMatch = m;
				}
			}
		}

		for (Field f : fields) {
			if (f.getName().equals(member))
				return new MemberDescriptor(f);
		}
		return aproxMatch != null ? new MemberDescriptor(aproxMatch) : null;
	}

	public static final Class<Float> CFloat = Float.class;
	public static final Class<Double> CDouble = Double.class;
	@SuppressWarnings("rawtypes")
	public static final Class<Iterable> CIterable = Iterable.class;
	@SuppressWarnings("rawtypes")
	public static final Class<Enumeration> CEnumeration = Enumeration.class;
	public static final Class<Character> CCharacter = Character.class;
	public static final Class<Void> TVoid = Void.TYPE;
	public static final Class<Number> CNumber = Number.class;
	public static final Class<?> CClass = Class.class;
	public static final Class<Object> CObject = Object.class;
	public static final Class<Short> CShort = Short.class;
	public static final Class<Byte> CByte = Byte.class;
	public static final Class<Boolean> CBoolean = Boolean.class;
	public static final Class<Long> CLong = Long.class;
	public static final Class<Integer> CInteger = Integer.class;
	public static final Class<String> CString = String.class;
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

	public static boolean canPromoteNumbers(Class<?>... clazzes) {
		for (Class<?> clazz : clazzes)
			if (!canPerformNumericPromotion(clazz))
				return false;
		return true;
	}

	public static boolean canPerformNumericPromotion(Class<?> clazz) {
		return Number.class.isAssignableFrom(clazz)
				|| Character.class.isAssignableFrom(clazz);
	}

	public static Class<?> getNumericPromotion(Class<?> first, Class<?> second) {
		if (!ClassDescriptor.canPerformNumericPromotion(first))
			throw new ArgumentException("fisrt");
		if (!ClassDescriptor.canPerformNumericPromotion(second))
			throw new ArgumentException("second");
		Class<?> promotion = Integer.class;

		if (Double.class.isAssignableFrom(first)
				|| Double.class.isAssignableFrom(second))
			promotion = Double.class;

		else if (Float.class.isAssignableFrom(first)
				|| Float.class.isAssignableFrom(second))
			promotion = Float.class;

		else if (Long.class.isAssignableFrom(first)
				|| Long.class.isAssignableFrom(second))
			promotion = Long.class;

		return promotion;
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
