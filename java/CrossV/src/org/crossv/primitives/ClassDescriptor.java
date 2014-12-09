package org.crossv.primitives;

import static java.text.MessageFormat.format;
import static org.crossv.primitives.Strings.toPascalCase;
import static org.crossv.primitives.Iterables.toArray;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Hashtable;

import org.crossv.expressions.EvaluationDescriptor;

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
		if (!method.getReturnType().equals(CVoid))
			return result;
		return null;
	}

	public Object execute(Object obj, String method, Object... parameters)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		Class<?>[] paramTypes = new Class<?>[parameters.length];
		for (int i = 0; i < parameters.length; i++)
			paramTypes[i] = parameters[i] != null ? parameters[i].getClass()
					: CObject;

		Method bestOverload = findBestOverload(method, paramTypes);
		return execute(obj, bestOverload, parameters);
	}

	public Method findBestOverload(String method, Iterable<Class<?>> paramTypes)
			throws NoSuchMethodException {
		Class<?>[] paramTypesArray = toArray(paramTypes, new Class<?>[0]);
		return findBestOverload(method, paramTypesArray);
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
		throw new NoSuchMethodException(format("Can''t find method \"{0}\".",
				method));
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

	@SuppressWarnings("rawtypes")
	public static final Class<Iterable> CIterable = Iterable.class;
	@SuppressWarnings("rawtypes")
	public static final Class<Enumeration> CEnumeration = Enumeration.class;
	public static final Class<Number> CNumber = Number.class;
	public static final Class<?> CClass = Class.class;
	public static final Class<EvaluationDescriptor> CEvaluatorDescriptor = EvaluationDescriptor.class;

	public static final Class<Void> TVoid = Void.TYPE;
	public static final Class<Float> TFloat = Float.TYPE;
	public static final Class<Double> TDouble = Double.TYPE;
	public static final Class<Character> TCharacter = Character.TYPE;
	public static final Class<Short> TShort = Short.TYPE;
	public static final Class<Byte> TByte = Byte.TYPE;
	public static final Class<Boolean> TBoolean = Boolean.TYPE;
	public static final Class<Long> TLong = Long.TYPE;
	public static final Class<Integer> TInteger = Integer.TYPE;

	public static final Class<Void> CVoid = Void.class;
	public static final Class<Float> CFloat = Float.class;
	public static final Class<Double> CDouble = Double.class;
	public static final Class<Character> CCharacter = Character.class;
	public static final Class<Object> CObject = Object.class;
	public static final Class<RuntimeObject> CRuntimeObject = RuntimeObject.class;
	public static final Class<Short> CShort = Short.class;
	public static final Class<Byte> CByte = Byte.class;
	public static final Class<Boolean> CBoolean = Boolean.class;
	public static final Class<Long> CLong = Long.class;
	public static final Class<Integer> CInteger = Integer.class;
	public static final Class<String> CString = String.class;

	private final static Hashtable<Class<?>, Class<?>> primitiveTypesToClasses = createPrimitiveToClassesTable();

	private static Hashtable<Class<?>, Class<?>> createPrimitiveToClassesTable() {
		Hashtable<Class<?>, Class<?>> table = new Hashtable<Class<?>, Class<?>>();
		table.put(TCharacter, CCharacter);
		table.put(TBoolean, CBoolean);
		table.put(TByte, CByte);
		table.put(TShort, CShort);
		table.put(TInteger, CInteger);
		table.put(TLong, CLong);
		table.put(TFloat, CFloat);
		table.put(TDouble, CDouble);
		return table;
	}

	private final static Hashtable<Class<?>, Class<?>> primitiveClassesToTypes = createPrimitiveToTypesTable();

	private static Hashtable<Class<?>, Class<?>> createPrimitiveToTypesTable() {
		Hashtable<Class<?>, Class<?>> table = new Hashtable<Class<?>, Class<?>>();
		table.put(CCharacter, TCharacter);
		table.put(CBoolean, TBoolean);
		table.put(CByte, TByte);
		table.put(CShort, TShort);
		table.put(CInteger, TInteger);
		table.put(CLong, TLong);
		table.put(CFloat, TFloat);
		table.put(CDouble, TDouble);
		return table;
	}

	public static boolean canPromoteNumbers(Class<?>... clazzes) {
		for (Class<?> clazz : clazzes)
			if (!canPerformNumericPromotion(clazz))
				return false;
		return true;
	}

	public static boolean canPerformNumericPromotion(Class<?> clazz) {
		return CNumber.isAssignableFrom(clazz)
				|| CCharacter.isAssignableFrom(clazz);
	}

	public static Class<?> getNumericPromotion(Class<?> first, Class<?> second) {
		if (!ClassDescriptor.canPerformNumericPromotion(first))
			throw new ArgumentException("fisrt");
		if (!ClassDescriptor.canPerformNumericPromotion(second))
			throw new ArgumentException("second");
		Class<?> promotion = CInteger;

		if (CDouble.isAssignableFrom(first) || CDouble.isAssignableFrom(second))
			promotion = CDouble;

		else if (CFloat.isAssignableFrom(first)
				|| CFloat.isAssignableFrom(second))
			promotion = CFloat;

		else if (CLong.isAssignableFrom(first)
				|| CLong.isAssignableFrom(second))
			promotion = CLong;

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

	public static Class<?> forName(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO auto-resolve short named types (int, boolean, string, ...
			// etc) and some well established classes on first ClassNotFound
			throw new ClassNotFoundAtRuntimeException(e);
		}
	}
}
