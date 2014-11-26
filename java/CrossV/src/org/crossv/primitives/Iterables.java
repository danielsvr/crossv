package org.crossv.primitives;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public final class Iterables {
	public static <E> E[] toArray(Iterable<E> iterable, E[] array) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		List<E> list = asList(iterable);
		if (list == null)
			list = toList(iterable);

		return list.toArray(array);
	}

	public static <E> List<E> toList(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		List<E> list = new ArrayList<E>();
		for (E item : iterable)
			list.add(item);
		return list;
	}

	public static <E> List<E> toList(E[] iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		return Arrays.asList(iterable);
	}

	public static <E> List<E> asList(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		if (iterable instanceof List)
			return (List<E>) iterable;
		return null;
	}

	public static <E extends Comparable<? super E>> List<E> createSortedIterable(
			Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		List<E> list = toList(iterable);
		Collections.sort(list);
		return list;
	}

	public static <E> Iterable<E> createSortedIterable(Iterable<E> iterable,
			Comparator<? super E> comparator) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (comparator == null)
			throw new ArgumentNullException("comparator");

		List<E> list = toList(iterable);
		Collections.sort(list, comparator);
		return list;
	}

	public static <E> boolean any(Iterable<E> iterable, Predicate<E> predicate) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (predicate == null)
			throw new ArgumentNullException("predicate");

		Iterator<E> iterator = iterable.iterator();
		while (iterator.hasNext())
			if (predicate.eval(iterator.next()))
				return true;
		return false;
	}

	public static <E> boolean any(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		Iterator<E> iterator = iterable.iterator();
		if (iterator.hasNext())
			return true;
		return false;
	}

	private static <E> int countInternal(Iterable<E> iterable,
			Predicate<E> predicate) {

		Iterator<E> iterator = iterable.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			E next = iterator.next();
			if (predicate == null || predicate.eval(next))
				count++;
		}
		return count;
	}

	public static <E> int count(Iterable<E> iterable, Predicate<E> predicate) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (predicate == null)
			throw new ArgumentNullException("predicate");

		return countInternal(iterable, predicate);
	}

	public static <E> int count(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		if (iterable instanceof List)
			return ((List<E>) iterable).size();

		return countInternal(iterable, null);
	}

	public static <E> int count(Enumeration<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		return countInternal(toIterable(iterable), null);
	}

	public static <E> E firstOrDefault(Iterable<E> iterable) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");

		Iterator<E> iterator = iterable.iterator();
		if (iterator.hasNext()) {
			E next = iterator.next();
			return next;
		}
		return null;
	}

	public static <E> E firstOrDefault(Iterable<E> iterable,
			Predicate<E> predicate) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (predicate == null)
			throw new ArgumentNullException("predicate");

		Iterator<E> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			E next = iterator.next();
			if (predicate.eval(next))
				return next;
		}
		return null;
	}

	public static <E, ER> Iterable<ER> select(Iterable<E> iterable,
			Function<E, ER> converter) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (converter == null)
			throw new ArgumentNullException("converter");
		return new LazyConverterIterable<E, ER>(iterable, converter);
	}

	public static <E, ER> Iterable<ER> select(E[] iterable,
			Function<E, ER> converter) {
		if (iterable == null)
			throw new ArgumentNullException("iterable");
		if (converter == null)
			throw new ArgumentNullException("converter");
		return new LazyArrayConverterIterable<E, ER>(iterable, converter);
	}

	public static <E> void addAllToList(List<E> list, Iterable<E> iterable) {
		for (E item : emptyIfNull(iterable))
			list.add(item);
	}

	@SuppressWarnings("unchecked")
	public static <E> Iterable<E> toIterable(E obj) {
		if (obj != null && obj.getClass().isArray())
			return new ArrayIterable<E>(obj, true);
		if (obj != null && Enumeration.class.isAssignableFrom(obj.getClass()))
			return (Iterable<E>) toIterable((Enumeration<?>) obj);
		return new ArrayIterable<E>(obj);
	}

	public static <E> Iterable<E> toIterable(Enumeration<E> obj) {
		return new EnumerationToIterable<E>(obj);
	}

	public static <E> Iterable<E> toIterable(E[] objs) {
		return new ArrayIterable<E>(null, objs);
	}

	@SuppressWarnings("unchecked")
	public static <E> Iterable<E> toIterable(E firstItem, E... otherItems) {
		return new ArrayIterable<E>(firstItem, otherItems);
	}

	public static <E> Iterable<E> empty() {
		return new EmptyIterable<E>();
	}

	public static <E> E elementAt(Iterable<E> iterable, int index) {
		int i = -1;
		if (index > -1)
			for (E e : iterable) {
				i++;
				if (i == index)
					return e;
			}

		String message;
		message = "Provided index exceeds the boundary of the iterable instance.";
		message = String.format("%sIndex: %d.", message, index);
		throw new IndexOutOfBoundsException(message);
	}

	public static <E> E elementAt(Enumeration<E> iterable, int index) {
		return elementAt(toIterable(iterable), index);
	}

	public static <E> boolean containsAll(Iterable<E> iterable, Iterable<E> objs) {
		objs = emptyIfNull(objs);
		iterable = emptyIfNull(iterable);

		for (E e : objs) {
			if (count(iterable) == 0)
				return false;
			boolean found = false;
			for (E e1 : iterable)
				if ((e == null && e1 == null) || (e != null && e.equals(e1)))
					found = true;
			if (!found)
				return false;
		}
		return true;
	}

	public static <E> Iterable<E> emptyIfNull(Iterable<E> iterable) {
		return iterable == null ? new EmptyIterable<E>() : iterable;
	}

	public static <E> Iterable<E> emptyIfNull(E[] evaluators) {
		return evaluators == null ? new EmptyIterable<E>() : toList(evaluators);
	}

	public static <E, ER> Iterable<ER> cast(Iterable<E> iterable) {
		return new LazyCastIterable<E, ER>(iterable);
	}

	public static <E> Iterable<E> repeatDefault(int max) {
		return new CreateItemIterable<E>(null, max);
	}

	public static <E> Iterable<E> repeat(E value, int max) {
		return new CreateItemIterable<E>(value, max);
	}

	public static <ER> Iterable<ER> ofClass(Class<ER> clazz, Iterable<?> items) {
		Iterable<Object> objects = cast(items);
		Iterable<Object> whereIterable = where(objects, new OfClassPredicate(
				clazz));
		Iterable<ER> result = cast(whereIterable);
		return result;
	}

	public static <E> Iterable<E> where(Iterable<E> iterable,
			Predicate<E> predicate) {
		return new WhereIterable<E>(iterable, predicate);
	}

	private static class OfClassPredicate implements Predicate<Object> {
		private Class<?> clazz;

		public OfClassPredicate(Class<?> clazz) {
			this.clazz = clazz;
		}

		@Override
		public boolean eval(Object value) {
			if (value == null)
				return false;
			return clazz.isAssignableFrom(value.getClass());
		}
	}
}