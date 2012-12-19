package org.crossv.primitives;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ValueGetterUtil {
	private ValueGetterUtil() {
	}

	public static TryResult<GetterValue> tryGetPropertyValue(
			Class<?> scopeClass, Object scope, String getterName) {
		Method method;
		PropertyDescriptor propertyDescriptor;
		try {
			propertyDescriptor = new PropertyDescriptor(getterName, scopeClass,
					getterName, null);
			method = propertyDescriptor.getReadMethod();
			return new TryResult<GetterValue>(invoke(scope, method));
		} catch (Exception e) {
			return new TryResult<GetterValue>(e);
		}
	}

	public static TryResult<GetterValue> tryGetMethodValue(Class<?> scopeClass,
			Object scope, String getterName) {
		try {
			try {
				return new TryResult<GetterValue>(invoke(scopeClass, scope,
						getterName));
			} catch (NoSuchMethodException e) {
				String decapitalizedName;
				decapitalizedName = Introspector.decapitalize(getterName);
				return new TryResult<GetterValue>(invoke(scopeClass, scope,
						decapitalizedName));
			}
		} catch (Exception e) {
			return new TryResult<GetterValue>(e);
		}
	}

	public static TryResult<GetterValue> tryGetFieldValue(Class<?> scopeClass,
			Object scope, String name) {
		try {
			try {
				return new TryResult<GetterValue>(getFieldValue(scopeClass,
						scope, name));
			} catch (NoSuchFieldException e) {
				String decapitalizedName;
				decapitalizedName = Introspector.decapitalize(name);
				return new TryResult<GetterValue>(getFieldValue(scopeClass,
						scope, decapitalizedName));
			}
		} catch (Exception e) {
			return new TryResult<GetterValue>(e);
		}
	}

	private static GetterValue invoke(Object scope, Method method)
			throws Exception {
		Object result;
		if (scope == null)
			return new GetterValue(method.getReturnType());
		result = method.invoke(scope);
		return new GetterValue(method.getReturnType(), result);
	}

	private static GetterValue invoke(Class<?> scopeClass, Object scope,
			String methodName) throws Exception {
		Method method;
		method = scopeClass.getMethod(methodName);
		return invoke(scope, method);
	}

	private static GetterValue getFieldValue(Class<?> scopeClass, Object scope,
			String name) throws Exception {
		Object result;
		
		Field field;
		field = scopeClass.getField(name);
		if (scope == null)
			return new GetterValue(field.getType());
		result = field.get(scope);
		return new GetterValue(field.getType(), result);
	}
}
