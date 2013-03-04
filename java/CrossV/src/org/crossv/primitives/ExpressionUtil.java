package org.crossv.primitives;

public class ExpressionUtil {

	public static boolean canPerformNumericPromotion(Class<?>... clazzes) {
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
		if (!canPerformNumericPromotion(first))
			throw new ArgumentException("fisrt");
		if (!canPerformNumericPromotion(second))
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
}
