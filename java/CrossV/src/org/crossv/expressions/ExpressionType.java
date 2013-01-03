package org.crossv.expressions;

public enum ExpressionType {
	NONE,
	// A bitwise or logical AND operation, such as (a & b)
	AND,
	// A conditional AND operation that evaluates the second operand
	// only if the first operand evaluates to true. It corresponds to (a && b)
	AND_ALSO,
	// An operation that obtains the length of a one-dimensional
	// array, such as array.Length
	ARRAY_LENGTH,
	// An indexing operation in a one-dimensional array, such as
	// array[index]
	ARRAY_INDEX,
	// A method call, such as in the obj.sampleMethod()
	// expression
	CALL,
	// A node that represents a null coalescing operation, such as (a ?? b) in
	// C#
	COALESCE,
	// A conditional operation, such as a > b ? a : b
	TERNARY,
	// A constant value
	CONSTANT,
	// A division operation, such as (a / b), for numeric
	// operands
	DIVIDE,
	// A node that represents an equality comparison, such as (a == b)
	EQUAL,
	// A bitwise or logical XOR operation, such as (a ^ b)
	EXCLUSIVE_OR,
	// A "greater than" comparison, such as (a > b)
	GREATER_THAN,
	// A "greater than or equal to" comparison, such as (a >= b)
	GREATER_THAN_OR_EQUAL,
	// A bitwise left-shift operation, such as (a << b)
	LEFT_SHIFT,
	// A "less than" comparison, such as (a < b)
	LESS_THAN,
	// A "less than or equal to" comparison, such as (a <= b)
	LESS_THAN_OR_EQUAL,
	// An operation that reads from a field or getter name, such as
	// obj.SampleProperty
	MEMBER_ACCESS,
	// An arithmetic remainder operation, such as (a % b)
	MODULO,
	// A multiplication operation, such as (a * b)
	MULTIPLY,
	// An arithmetic negation operation, such as (-a)
	NEGATE,
	// A unary plus operation, such as (+a)
	UNARY_PLUS,
	// A bitwise complement or logical negation operation. It is (!a) for
	// Boolean values
	NOT,
	// An inequality comparison, such as (a != b)
	NOT_EQUAL,
	// A bitwise or logical OR operation, such as (a | b)
	OR,
	// A short-circuiting conditional OR operation, such as (a || b)
	OR_ELSE,
	// A bitwise right-shift operation, such as (a >> b)
	RIGHT_SHIFT,
	// A subtraction operation, such as (a - b)
	SUBTRACT,
	// A class test, such as obj instanceof SampleType
	INSTANCE_OF,
	// A true condition value
	IS_TRUE,
	// A false condition value
	IS_FALSE
}
