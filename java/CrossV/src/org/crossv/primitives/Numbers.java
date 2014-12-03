package org.crossv.primitives;

import sun.misc.FloatingDecimal;

public final class Numbers {

	public static Number toNumber(String text) {
		return toNumber(text, 10);
	}

	public static Number toNumber(String text, int radix) {
		if (text == null) {
			throw new NumberFormatException("null");
		}

		int len = text.length();
		if (len <= 0)
			throw new NumberFormatException("Illegal token: " + text);

		if (radix < Character.MIN_RADIX) {
			throw new NumberFormatException("radix " + radix
					+ " less than Character.MIN_RADIX");
		}

		if (radix > Character.MAX_RADIX) {
			throw new NumberFormatException("radix " + radix
					+ " greater than Character.MAX_RADIX");
		}

		boolean shouldBeLong = text.endsWith("l") || text.endsWith("L");
		boolean shouldBeFloat = text.endsWith("f") || text.endsWith("F");
		boolean shouldBeDouble = text.endsWith("d") || text.endsWith("D");

		boolean tryToInt = !shouldBeLong && !shouldBeFloat && !shouldBeDouble;
		boolean tryToLong = !shouldBeFloat && !shouldBeDouble;

		boolean tryToFloat = shouldBeFloat && !shouldBeLong && !shouldBeDouble;
		boolean tryToDouble = !shouldBeLong && !shouldBeFloat;

		if (tryToInt || tryToLong) {
			boolean isInt = !shouldBeLong;
			boolean isLong = shouldBeLong;

			int intResult = 0;
			long longResult = 0;
			boolean negative = false;
			int i = 0;

			int intLimit = -Integer.MAX_VALUE;
			long longLimit = -Long.MAX_VALUE;
			int intMultmin;
			long longMultmin;
			int digit;

			char firstChar = text.charAt(0);
			if (firstChar < '0') { // Possible leading "+" or "-"
				if (firstChar == '-') {
					negative = true;
					intLimit = Integer.MIN_VALUE;
				} else if (firstChar != '+')
					throw new NumberFormatException("Illegal token: " + text);

				if (len == 1) // Cannot have lone "+" or "-"
					throw new NumberFormatException("Illegal token: " + text);
				i++;
			}
			intMultmin = intLimit / radix;
			longMultmin = longLimit / radix;
			while (i < len) {
				// Accumulating negatively avoids surprises near MAX_VALUE
				char chr = text.charAt(i);
				digit = Character.digit(chr, radix);
				if (digit < 0) {
					if (i == len - 1 && (chr != 'l' || chr != 'L'))
						break;
					if (chr == '.') {
						isInt = false;
						isLong = false;
						break;
					}
					throw new NumberFormatException("Illegal token: " + text);
				}

				i++;
				if (isInt) {
					if (intResult < intMultmin) {
						isInt = false;
						isLong = true;
						longResult = intResult;
					}
					intResult *= radix;
					if (intResult < intLimit + digit) {
						isInt = false;
						isLong = true;
						longResult = intResult;
					}
					intResult -= digit;
				}
				if (isLong) {
					if (longResult < longMultmin) {
						isLong = false;
					}
					longResult *= radix;
					if (longResult < longLimit + digit) {
						isLong = false;
					}
					longResult -= digit;
				}
				if (!isInt && !isLong) {
					tryToFloat = false;
					tryToDouble = true;
					break;
				}
			}
			if (isInt)
				return negative ? intResult : -intResult;
			if (isLong)
				return negative ? longResult : -longResult;
		}

		FloatingDecimal floatDecimal = FloatingDecimal
				.readJavaFormatString(text);
		if (tryToFloat)
			return floatDecimal.floatValue();

		if (tryToDouble)
			return floatDecimal.doubleValue();

		throw new NumberFormatException("Illegal token: " + text);
	}
}
