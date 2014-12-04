grammar CrossV;

@header {
package org.crossv.parsing.grammars.antlr4;

import static org.crossv.expressions.Expressions.*;
import static org.crossv.primitives.Numbers.toNumber;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;
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
	'null'
	{$result = constant(null);}

	| 'obj'
	{$result = instance();}

	| 'context'
	{$result = context();}

	//	| IDENTIFIER
	//	{$result = null;}

	| STRING_LITERAL
	{$result = constant($STRING_LITERAL.text.replaceAll("^\"|\"$", ""));}

	| NUMBER_LITERAL
	{$result = constant(toNumber($NUMBER_LITERAL.text));}

	| BOOLEAN_LITERAL
	{$result = constant(Boolean.parseBoolean($BOOLEAN_LITERAL.text));}

	| arrayInitialization
	{$result = constant($arrayInitialization.result);}

	|
	{String clazz="";}

	'(' firstId = IDENTIFIER
	{clazz = $firstId.text;}

	(
		'.' otherId = IDENTIFIER
		{clazz += "." + $otherId.text;}

	)* ')' toCast = term
	{
		$result =  $toCast.result;		
		if(!clazz.equals(""))
			$result = Expressions.cast(clazz, $result);
	}

	| '(' exp = expression ')'
	{$result = $exp.result;}

;

arrayInitialization returns [Object result]
:
{
		String clazz = "";
		Integer length = null;
		List<String> stringValues = new ArrayList<String>();
		List<Number> numberValues = new ArrayList<Number>();
		List<Boolean> boolValues = new ArrayList<Boolean>();
	}

	'new' firstId = IDENTIFIER
	{clazz = $firstId.text;}

	(
		'.' otherId = IDENTIFIER
		{clazz += '.' + $otherId.text;}

	)* '['
	(
		length = NUMBER_LITERAL
		{length = toNumber($length.text).intValue();}

	)? ']' '{'
	(
		str1 = STRING_LITERAL
		{stringValues.add($str1.text);}

		(
			',' str2 = STRING_LITERAL
			{stringValues.add($str2.text);}

		)*
		| no1 = NUMBER_LITERAL
		{numberValues.add(toNumber($no1.text));}

		(
			',' no2 = NUMBER_LITERAL
			{numberValues.add(toNumber($no2.text));}

		)*
		| bool1 = BOOLEAN_LITERAL
		{boolValues.add(Boolean.parseBoolean($bool1.text));}

		(
			',' bool2 = BOOLEAN_LITERAL
			{boolValues.add(Boolean.parseBoolean($bool2.text));}

		)*
	) '}'
	{
		Class<?> componentType = null;
		try {
			Class<?> componentClass = Class.forName(clazz);
			componentType = transformToTypeIfPrimitive(componentClass);
		} catch(ClassNotFoundException e) {}
		
		if(componentType == null || (componentType != String.class &&
			!componentType.isPrimitive()))
			throw new InvalidArrayDeclarationException(this, "Only primitive types are supported for array declatations.");
		
		List<?> list = null;
		if(!stringValues.isEmpty())
			list = stringValues;
		if(!numberValues.isEmpty())
			list = numberValues;
		if(!boolValues.isEmpty())
			list = boolValues;
		
		if(length == null)
			length = list.size();
			
		if(length.intValue() != list.size())
			throw new InvalidArrayDeclarationException(this, "Declared length mismatch the number of declared elements.");
		
		$result = java.lang.reflect.Array.newInstance(componentType, length.intValue());
		boolean getFloatValue = componentType.equals(java.lang.Float.TYPE);
		for(int i = 0; i < length.intValue(); i++) {
			Object value = list.get(i);
			if(getFloatValue)
				value = ((Number) value).floatValue();
			java.lang.reflect.Array.set($result, i, value);
		}
	}

;

negation returns [Expression result]
:
{
		String op = "";
		boolean apply = false;
	}

	(
		'!'
		{
			op = "not";
			apply = !apply;
		}

		| '~'
		{
			op = "complement";
			apply = !apply;
		}

	)* term
	{
		$result =  $term.result;
		if(apply) {
			if(op.equals("not"))
				$result = not($result);
			else if (op.equals("complement"))
				$result = complemented($result);
		}
	}

;

unary returns [Expression result]
:
// TODO unify unary and negation
	'+'+ plus = negation
	{$result = plus($plus.result);}

	|
	{boolean applyNegative = false;}

	(
		'-'
		{applyNegative = !applyNegative;}

	)+ minus = negation
	{if(applyNegative) $result = negate($minus.result); else $result = $minus.result;}

	| negate = negation
	{$result = $negate.result;}

;

multiply returns [Expression result]
:
// TODO rename to multiplicity
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
// TODO rename to additive
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

		| 'instanceof'
		{String clazz = "";}

		(
			firstId = IDENTIFIER
			{clazz = $firstId.text;}

			(
				'.' secondId = IDENTIFIER
				{clazz += "." +$secondId.text;}

			)*
		)
		{ $result = instanceOf($result, clazz);}

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

NUMBER_LITERAL
:
	DIGITS NUMBER_SUFFIX?
	| DIGITS '.' DIGITS? NUMBER_SUFFIX?
	| '.' DIGITS NUMBER_SUFFIX?
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
NUMBER_SUFFIX
:
	[lLfFdD]
;

fragment
IDENTIFIER_LETTER
:
	'a' .. 'z'
	| 'A' .. 'Z'
	| '_'
;

fragment
DIGITS
:
	DIGIT+
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