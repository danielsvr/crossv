package org.crossv.expressions;

import static org.crossv.primitives.Iterables.toIterable;
import static org.crossv.primitives.Iterables.contains;

enum EvalOp {
	DIVIDE, MODULO, MULTIPLY, PLUS, SUBTRACT, LEFT_SHIFT, RIGHT_SHIFT, GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN, LESS_THAN_OR_EQUAL, EQUAL, NOT_EQUAL, AND, OR, XOR, OR_ELSE, AND_ALSO;
	public boolean isAdditivityOp() {
		return contains(toIterable(getAdditivityOps()), this);
	}

	public static EvalOp[] getAdditivityOps() {
		return new EvalOp[] { PLUS, SUBTRACT };
	}

	public static String getAdditivityOpsToString() {
		EvalOp[] ops = getAdditivityOps();
		return opsToString(ops);
	}

	private static String opsToString(EvalOp[] ops) {
		if (ops == null || ops.length == 0)
			return "";

		StringBuffer buffer;
		buffer = new StringBuffer(ops[0].name());
		for (int i = 1; i < ops.length; i++)
			buffer.append(",").append(ops[i].name());

		return buffer.toString();
	}

	public boolean isBitwiseOp() {
		return contains(toIterable(getBitwiseOps()), this);
	}

	public static EvalOp[] getBitwiseOps() {
		return new EvalOp[] { AND, OR, XOR };
	}

	public static String getBitwiseOpsToString() {
		EvalOp[] ops = getBitwiseOps();
		return opsToString(ops);
	}

	public static String getEqualityOpsToString() {
		EvalOp[] ops = getEqualityOps();
		return opsToString(ops);
	}

	public static EvalOp[] getEqualityOps() {
		return new EvalOp[] { EQUAL, NOT_EQUAL };
	}

	public boolean isEqualityOp() {
		return contains(toIterable(getEqualityOps()), this);
	}

	public boolean isMultiplicityOp() {
		return contains(toIterable(getMultiplicityOps()), this);
	}

	public static String getMultiplicityOpsToString() {
		EvalOp[] ops = getMultiplicityOps();
		return opsToString(ops);
	}

	public static EvalOp[] getMultiplicityOps() {
		return new EvalOp[] { DIVIDE, MODULO, MULTIPLY };
	}

	public static EvalOp[] getBinaryConditionalOps() {
		return new EvalOp[] { AND_ALSO, OR_ELSE };
	}

	public static String getBinaryConditionalOpsToString() {
		EvalOp[] ops = getBinaryConditionalOps();
		return opsToString(ops);
	}

	public boolean isBinaryConditionalOp() {
		return contains(toIterable(getBinaryConditionalOps()), this);
	}

	public static String getNumericalComparisonOpsToString() {
		EvalOp[] ops = getNumericalComparisonOps();
		return opsToString(ops);
	}

	public static EvalOp[] getNumericalComparisonOps() {
		return new EvalOp[] { GREATER_THAN, GREATER_THAN_OR_EQUAL, LESS_THAN,
				LESS_THAN_OR_EQUAL };
	}

	public boolean isNumericalComparisonOp() {
		return contains(toIterable(getNumericalComparisonOps()), this);
	}

	public static String getShiftOpsToString() {
		EvalOp[] ops = getShiftOps();
		return opsToString(ops);
	}

	public static EvalOp[] getShiftOps() {
		return new EvalOp[] { LEFT_SHIFT, RIGHT_SHIFT };
	}

	public boolean isShiftOp() {
		return contains(toIterable(getShiftOps()), this);
	}
}
