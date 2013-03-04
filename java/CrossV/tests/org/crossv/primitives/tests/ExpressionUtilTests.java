package org.crossv.primitives.tests;

import static org.crossv.primitives.ExpressionUtil.getNumericPromotion;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.crossv.primitives.ArgumentException;
import org.junit.Test;

public class ExpressionUtilTests {
	
	@Test
	public void promote_FirstIsDoubleSecondChar_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Double.class, Character.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsCharacterSecondDouble_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Character.class, Double.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsDoubleSecondByte_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Double.class, Byte.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsByteSecondDouble_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Byte.class, Double.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsDoubleSecondShort_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Double.class, Short.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsShortSecondDouble_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Short.class, Double.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsDoubleSecondInt_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Double.class, Integer.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsIntSecondDouble_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Integer.class, Double.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsDoubleSecondLong_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Double.class, Long.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsLongSecondDouble_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Long.class, Double.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsDoubleSecondFloat_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Double.class, Float.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsFloatSecondDouble_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Float.class, Double.class);
		assertThat("Class is Double", clazz.equals(Double.class), is(true));
	}

	@Test
	public void promote_FirstIsFloatSecondChar_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Float.class, Character.class);
		assertThat("Class is Double", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsCharacterSecondFloat_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Character.class, Float.class);
		assertThat("Class is Double", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsByteSecondFloat_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Byte.class, Float.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsFloatSecondByte_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Float.class, Byte.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsShortSecondFloat_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Short.class, Float.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsFloatSecondShort_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Float.class, Short.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsIntSecondFloat_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Integer.class, Float.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsFloatSecondInt_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Float.class, Integer.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsLongSecondFloat_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Long.class, Float.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsFloatSecondLong_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Float.class, Long.class);
		assertThat("Class is Float", clazz.equals(Float.class), is(true));
	}

	@Test
	public void promote_FirstIsByteSecondLong_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Byte.class, Long.class);
		assertThat("Class is Long", clazz.equals(Long.class), is(true));
	}

	@Test
	public void promote_FirstIsLongSecondByte_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Long.class, Byte.class);
		assertThat("Class is Long", clazz.equals(Long.class), is(true));
	}

	@Test
	public void promote_FirstIsShortSecondLong_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Short.class, Long.class);
		assertThat("Class is Long", clazz.equals(Long.class), is(true));
	}

	@Test
	public void promote_FirstIsLongSecondShort_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Long.class, Short.class);
		assertThat("Class is Long", clazz.equals(Long.class), is(true));
	}

	@Test
	public void promote_FirstIsIntSecondLong_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Integer.class, Long.class);
		assertThat("Class is Long", clazz.equals(Long.class), is(true));
	}

	@Test
	public void promote_FirstIsLongSecondInt_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Long.class, Integer.class);
		assertThat("Class is Long", clazz.equals(Long.class), is(true));
	}

	@Test
	public void promote_FirstIsByteSecondByte_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Byte.class, Byte.class);
		assertThat("Class is Long", clazz.equals(Integer.class), is(true));
	}

	@Test
	public void promote_FirstIsIntSecondShort_ReturnsDouble() {
		Class<?> clazz = getNumericPromotion(Integer.class, Short.class);
		assertThat("Class is Long", clazz.equals(Integer.class), is(true));
	}

	@Test(expected = ArgumentException.class)
	public void promote_FirstIsStringSecondShort_ReturnsDouble() {
		getNumericPromotion(String.class, Short.class);
	}
	
	@Test(expected = ArgumentException.class)
	public void promote_FirstIsByteSecondString_ReturnsDouble() {
		getNumericPromotion(Byte.class, String.class);
	}
}