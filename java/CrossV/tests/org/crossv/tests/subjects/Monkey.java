package org.crossv.tests.subjects;

import java.util.Enumeration;
import java.util.List;

public class Monkey {
	private String name;
	private Monkey mother;
	private Monkey[] relativesAsArray;
	private List<Monkey> relativesAsList;
	private Iterable<Monkey> relativesAsIteravle;
	private Enumeration<Monkey> relativesAsEnumeration;
	public String nickname;
	
	public List<Monkey> getRelativesAsList() {
		return relativesAsList;
	}

	public void setRelativesAsList(List<Monkey> relativesAsList) {
		this.relativesAsList = relativesAsList;
	}

	public Iterable<Monkey> getRelativesAsIterable() {
		return relativesAsIteravle;
	}

	public void setRelativesAsIterable(Iterable<Monkey> relativesAsIterable) {
		this.relativesAsIteravle = relativesAsIterable;
	}

	public Enumeration<Monkey> getRelativesAsEnumeration() {
		return relativesAsEnumeration;
	}

	public void setRelativesAsEnumeration(
			Enumeration<Monkey> relativesAsEnumeration) {
		this.relativesAsEnumeration = relativesAsEnumeration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Monkey getMother() {
		return mother;
	}

	public void setMother(Monkey brother) {
		this.mother = brother;
	}

	public Monkey[] getRelativesAsArray() {
		return relativesAsArray;
	}

	public void setRelativesAsArray(Monkey[] relativesAsArray) {
		this.relativesAsArray = relativesAsArray;
	}
}
