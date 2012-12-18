package org.crossv.getters.descriptors;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.Strings;

public class GetterDescriptor<E> {
	private String getterName;
	private Class<E> scopeClass;
	private Class<?> getterClass;

	public GetterDescriptor(Class<E> scopeClass, String getterName) {
		if (scopeClass == null)
			throw new ArgumentNullException("scopeClass");
		if (Strings.isNullOrWhitespace(getterName))
			throw new ArgumentNullException("getterName");
		this.scopeClass = scopeClass;
		this.getterName = getterName;
	}

	public Class<E> getScopeClass() {
		return scopeClass;
	}

	public String getName() {
		return getterName;
	}
	
	public Object getValue(E obj) throws Exception {
		Object result;
		String message;
		List<String> names;
		Method method;
		Field field;
		boolean isMethod;

		isMethod = false;
		names = new ArrayList<String>();
		names.add("get" + getterName);
		names.add("is" + getterName);
		names.add(getterName);
		for (String name : names) {
			try {
				method = scopeClass.getMethod(name);
				getterClass = method.getReturnType();
				isMethod = true;
				return invoke(obj, method);

			} catch (NoSuchMethodException e) {
			}
		}

		try {
			field = scopeClass.getField(getterName);
			result = field.get(scopeClass);
			getterClass = field.getType();
			return getterClass.cast(result);
		} catch (NoSuchFieldException e) {
		}

		if (isMethod) {
			message = "There is no public, parameterless method that "
					+ "maches get\"{0}\" or \"{0}\".";
			message = MessageFormat.format(message, getterName);
			throw new MalformedMemberException(message);
		}

		message = "There is no public method or field that "
				+ "maches \"get{0}\" or \"{0}\".";
		message = MessageFormat.format(message, getterName);
		throw new NoSuchMemberException(message);
	}

	private Object invoke(E obj, Method method) throws Exception {
		Object result;
		if (obj == null)
			return null;
		result = method.invoke(obj);
		return getterClass.cast(result);
	}

	public E castScopeObject(Object obj) {
		return scopeClass.cast(obj);
	}

	public Class<?> getReturnClass(){
		return getterClass;
	}
}
