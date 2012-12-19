package org.crossv.getters;

import static org.crossv.primitives.ValueGetterUtil.tryGetFieldValue;
import static org.crossv.primitives.ValueGetterUtil.tryGetMethodValue;
import static org.crossv.primitives.ValueGetterUtil.tryGetPropertyValue;

import java.text.MessageFormat;

import org.crossv.primitives.ArgumentNullException;
import org.crossv.primitives.GetterValue;
import org.crossv.primitives.Strings;
import org.crossv.primitives.TryResult;

public class GetterDescriptor<E> {
	private String getterName;
	private Class<E> scopeClass;
	private Class<?> getterClass;
	boolean isMethod;

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
		String message;
		isMethod = false;
		TryResult<GetterValue> posibleValue;

		posibleValue = tryGetPropertyValue(scopeClass, obj, getterName);
		if (!posibleValue.isSuccessful()) {
			posibleValue = tryGetMethodValue(scopeClass, obj, getterName);
			if (!posibleValue.isSuccessful()) {
				posibleValue = tryGetFieldValue(scopeClass, obj, getterName);
				if (!posibleValue.isSuccessful()) {
					message = "There is no public, parameterless, property "
							+ "getter or field that maches \"{0}\".";
					message = MessageFormat.format(message, getterName);
					throw new NoSuchMemberException(message);
				}
			}
		}
		if (!posibleValue.isSuccessful() && posibleValue.getCause() != null)
			throw posibleValue.getCause();

		GetterValue result = posibleValue.getResult();
		getterClass = result.getGetterClass();
		return result.getValue();
	}

	public E castScopeObject(Object obj) {
		return scopeClass.cast(obj);
	}

	public Class<?> getReturnClass() throws GetterValidationException {
		return getterClass;
	}
}
