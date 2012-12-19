package org.crossv.primitives;

public class GetterValue {
	private Class<?> getterClass;
	private Object value;

	public Class<?> getGetterClass() {
		return getterClass;
	}

	public Object getValue() {
		return value;
	}

	public GetterValue(Class<?> getterClass, Object value) {
		this.getterClass = getterClass;
		this.value = value;
	}

	public GetterValue(Class<?> getterClass) {
		this(getterClass, null);
	}
}
