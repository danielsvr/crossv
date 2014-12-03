grammar CrossV;

@header {
package org.crossv.parsing.grammars.antlr4;
import static org.crossv.expressions.Expressions.*;
import org.crossv.expressions.*;
}

options {
	language = Java;
}

validations returns [Expression[] result]
:
{
		List<Expression> validations = new ArrayList<Expression>();
	}

	(
		validation
		{validations.add($validation.result);}

	)+ EOF
	{$result = validations.toArray(new Expression[0]);}

;

validation returns [Expression result]
:
{
		Expression scope = null;
		List<Expression> evaluations = new ArrayList<Expression>();
	}

	'when'
	(
		singleInstanceOf = objInstanceOf
		{scope = $singleInstanceOf.result;}

		| firstLeft = objInstanceOf '&&' firstRight = contextInstanceOf
		{scope = and($firstLeft.result, $firstRight.result);}

		| secondLeft = contextInstanceOf '&&' secondRight = objInstanceOf
		{scope = and($secondLeft.result, $secondRight.result);}

	) '['
	(
		evaluation
		{evaluations.add($evaluation.result);}

	)+ ']'
	{$result = when(scope, evaluations);}

;

objInstanceOf returns [Expression result]
:
	{String className = "";}

	'obj' 'instanceof' first = IDENTIFIER
	{className += $first.text;}

	(
		'.' other = IDENTIFIER
		{className += "." + $other.text;}

	)*
	{$result = instanceOf(instance(), className);}

;

contextInstanceOf returns [Expression result]
:
	{String className = "";}

	'context' 'instanceof' first = IDENTIFIER
	{className += $first.text;}

	(
		'.' other = IDENTIFIER
		{className += "." + $other.text;}

	)+
	{$result = instanceOf(context(), className);}

;

evaluation returns [Expression result]
:
	validif
	{$result = $validif.result;}

	| warnif
	{$result = $warnif.result;}

;

validif returns [Expression result]
:
	{Expression scope = null;}

	(
		'obj' '.' memberName = IDENTIFIER
		{scope = memberAccess(instance(), $memberName.text);}

		| 'obj' '.' methodName = IDENTIFIER '()'
		{scope = call(instance(), $methodName.text);}

		| scopeText = STRING_LITERAL
		{scope = constant($scopeText.text);}

	) 'validif' expression 'else' message = STRING_LITERAL
	{$result = validIf(scope, $expression.result, constant($message.text));}

;

warnif returns [Expression result]
:
	{Expression scope = null;}

	(
		'obj' '.' memberName = IDENTIFIER
		{scope = memberAccess(instance(), $memberName.text);}

		| 'obj' '.' methodName = IDENTIFIER '()'
		{scope = call(instance(), $methodName.text);}

		| scopeText = STRING_LITERAL
		{scope = constant($scopeText.text);}

	) 'warnif' expression 'then' message = STRING_LITERAL
	{$result = warnIf(scope, $expression.result, constant($message.text));}

;

term returns [Expression result]
:
	IDENTIFIER
	{$result = null;}

	| 'obj'
	{$result = instance();}

	| 'context'
	{$result = context();}

	| STRING_LITERAL
	{$result = constant($STRING_LITERAL.text);}

	| INTEGER_LITERAL
	{$result = constant(Integer.parseInt($INTEGER_LITERAL.text));}

	| BOOLEAN_LITERAL
	{$result = constant(Boolean.parseBoolean($BOOLEAN_LITERAL.text));}

	| FLOAT_LITERAL
	{$result = constant(Double.parseDouble($FLOAT_LITERAL.text));}

	| '(' expression ')'
	{$result = $expression.result;}

;

cast returns [Expression result]
:
	{String clazz="";}

	(
		'(' firstId = IDENTIFIER
		{clazz = $firstId.text;}

		(
			'.' otherId = IDENTIFIER
			{clazz += "." + $otherId.text;}

		)* ')'
	)? term
	{
		$result =  $term.result;		
		if(!clazz.equals(""))
			$result = Expressions.cast(clazz, $result);
	}

;

negation returns [Expression result]
:
	{String op = "";}

	(
		'!'
		{op = "not";}

		| '~'
		{op = "complement";}

	)* cast
	{
		$result =  $cast.result;		
		if(op.equals("not"))
			$result = not($result);
		else if (op.equals("complement"))
			$result = complemented($result);
	}

;

unary returns [Expression result]
:
{
	boolean positive = true;
	boolean apply = false;
}

	(
		'+'
		{apply = true;}

		| '-'
		{
			positive = !positive;
			apply = true;
		}

	)* negation
	{
		$result = $negation.result;
		if(!positive)
			$result = negate($result);
		else if(apply)
			$result = plus($result);
	}

;

multiply returns [Expression result]
:
	left = unary
	{$result = $left.result;}

	(
		'*' right = unary
		{ $result = Expressions.multiply($result, $right.result);}

		| '/' right = unary
		{ $result = divide($result, $right.result);}

		| '%' right = unary
		{ $result = modulo($result, $right.result);}

	)*
;

add returns [Expression result]
:
	left = multiply
	{$result = $left.result;}

	(
		'+' right = multiply
		{ $result = Expressions.add($result, $right.result);}

		| '-' right = multiply
		{ $result = subtract($result, $right.result);}

	)*
;

shift returns [Expression result]
:
	left = add
	{$result = $left.result;}

	(
		'<<' right = add
		{ $result = leftShift($result, $right.result);}

		| '>>' right = add
		{ $result = rightShift($result, $right.result);}

	)*
;

relation returns [Expression result]
:
	left = shift
	{$result = $left.result;}

	(
		'==' right = shift
		{ $result = equal($result, $right.result);}

		| '!=' right = shift
		{ $result = notEqual($result, $right.result);}

		| '<' right = shift
		{ $result = lessThan($result, $right.result);}

		| '>' right = shift
		{ $result = greaterThan($result, $right.result);}

		| '<=' right = shift
		{ $result = lessThanOrEqual($result, $right.result);}

		| '>=' right = shift
		{ $result = greaterThanOrEqual($result, $right.result);}

		| 'instanceof' right = shift
		{ $result = instanceOf($result, $right.result);}

	)*
;

bitwise returns [Expression result]
:
	left = relation
	{$result = $left.result;}

	(
		'&' right = relation
		{ $result = bitwiseAnd($result, $right.result);}

		| '|' right = relation
		{ $result = bitwiseOr($result, $right.result);}

		| '^' right = relation
		{ $result = bitwiseXor($result, $right.result);}

	)*
;

logical returns [Expression result]
:
	left = bitwise
	{$result = $left.result;}

	(
		'&&' right = bitwise
		{ $result = and($result, $right.result);}

		| '||' right = bitwise
		{ $result = or($result, $right.result);}

	)*
;

expression returns [Expression result]
:
	logical
	{$result = $logical.result;}

	| test = expression '?' ifTrue = expression ':' ifFalse = expression
	{ $result = conditional($test.result, $ifTrue.result, $ifFalse.result);}

	| nullable = expression '??' ifTrue = expression
	{ $result = coalesce($nullable.result, $ifTrue.result);}

	// CALL
	// MEMBER ACCESS

;

STRING_LITERAL
:
	'"'
	(
		ESC
		| .
	)*? '"'
;

fragment
ESC
:
	'\\' [btnr"\\]
; // \b, \t, \n etc...

INTEGER_LITERAL
:
	DIGIT+
;

FLOAT_LITERAL
:
	DIGIT+ '.' DIGIT*
	| '.' DIGIT+
;

BOOLEAN_LITERAL
:
	'true'
	| 'True'
	| 'false'
	| 'False'
;

IDENTIFIER
:
	IDENTIFIER_LETTER
	(
		IDENTIFIER_LETTER
		| DIGIT
	)*
;

fragment
IDENTIFIER_LETTER
:
	'a' .. 'z'
	| 'A' .. 'Z'
	| '_'
;

fragment
DIGIT
:
	'0' .. '9'
;

WHITE_SPACE
:
	[ \t\r\n\f]+ -> skip
;

MULTILINE_COMMENT
:
	'/*' .*? '*/' -> skip
;

COMMENT
:
	(
		'//'
		| '#'
	) .*?
	(
		'\n'
		| '\r'
	) -> skip
;