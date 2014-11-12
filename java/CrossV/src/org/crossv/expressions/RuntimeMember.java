package org.crossv.expressions;

import java.lang.reflect.AccessibleObject;

public class RuntimeMember extends AccessibleObject {
	private Expression instance;
	private String name;

	public RuntimeMember(Expression instance, String name) {
		this.instance = instance;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Expression getInstance() {
		return instance;
	}
}
