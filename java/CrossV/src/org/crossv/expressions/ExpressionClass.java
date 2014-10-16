package org.crossv.expressions;

import java.util.Enumeration;

final class ExpressionClass {
	public static final Class<String> CString = String.class;
	public static final Class<Integer> CInteger = Integer.class;
	public static final Class<Long> CLong = Long.class;
	public static final Class<Boolean> CBoolean = Boolean.class;
	public static final Class<Byte> CByte = Byte.class;
	public static final Class<Short> CShort = Short.class;
	public static final Class<Object> CObject = Object.class;
	public static final Class<?> CClass = Class.class;
	public static final Class<Number> CNumber = Number.class;
	public static final Class<Character> CCharacter = Character.class;
	@SuppressWarnings("rawtypes")
	public static final Class<Enumeration> CEnumeration = Enumeration.class;
	@SuppressWarnings("rawtypes")
	public static final Class<Iterable> CIterable = Iterable.class;
	public static final Class<Double> CDouble = Double.class;
	public static final Class<Float> CFloat = Float.class;
}
