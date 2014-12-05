grammar CrossV;

@header {
package org.crossv.parsing.grammars.antlr4;

import static org.crossv.expressions.Expressions.add;
import static org.crossv.expressions.Expressions.and;
import static org.crossv.expressions.Expressions.bitwiseAnd;
import static org.crossv.expressions.Expressions.bitwiseOr;
import static org.crossv.expressions.Expressions.bitwiseXor;
import static org.crossv.expressions.Expressions.call;
import static org.crossv.expressions.Expressions.cast;
import static org.crossv.expressions.Expressions.coalesce;
import static org.crossv.expressions.Expressions.complemented;
import static org.crossv.expressions.Expressions.conditional;
import static org.crossv.expressions.Expressions.constant;
import static org.crossv.expressions.Expressions.context;
import static org.crossv.expressions.Expressions.divide;
import static org.crossv.expressions.Expressions.equal;
import static org.crossv.expressions.Expressions.greaterThan;
import static org.crossv.expressions.Expressions.greaterThanOrEqual;
import static org.crossv.expressions.Expressions.instance;
import static org.crossv.expressions.Expressions.instanceOf;
import static org.crossv.expressions.Expressions.leftShift;
import static org.crossv.expressions.Expressions.lessThan;
import static org.crossv.expressions.Expressions.lessThanOrEqual;
import static org.crossv.expressions.Expressions.memberAccess;
import static org.crossv.expressions.Expressions.modulo;
import static org.crossv.expressions.Expressions.multiply;
import static org.crossv.expressions.Expressions.negate;
import static org.crossv.expressions.Expressions.not;
import static org.crossv.expressions.Expressions.notEqual;
import static org.crossv.expressions.Expressions.or;
import static org.crossv.expressions.Expressions.plus;
import static org.crossv.expressions.Expressions.rightShift;
import static org.crossv.expressions.Expressions.sequenceIndex;
import static org.crossv.expressions.Expressions.sequenceLength;
import static org.crossv.expressions.Expressions.subtract;
import static org.crossv.expressions.Expressions.validIf;
import static org.crossv.expressions.Expressions.warnIf;
import static org.crossv.expressions.Expressions.when;
import static org.crossv.primitives.ClassDescriptor.transformToTypeIfPrimitive;
import static org.crossv.primitives.Numbers.toNumber;

import org.crossv.expressions.Expression;
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

	) '[' evaluations
	{evaluations.add($evaluations.result);}

	(
		',' evaluations
		{evaluations.add($evaluations.result);}

	)* ']'
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

evaluations returns [Expression result]
:
	validationEvaluations
	{$result = $validationEvaluations.result;}

	| warningEvaluations
	{$result = $warningEvaluations.result;}

;

validationEvaluations returns [Expression result]
:
	{Expression scope = null;}

	(
		'obj' '.' memberName = IDENTIFIER
		{scope = memberAccess(instance(), $memberName.text);}

		| 'obj' '.' methodName = IDENTIFIER '()'
		{scope = call(instance(), $methodName.text);}

		| scopeText = STRING_LITERAL
		{scope = constant($scopeText.text.replaceAll("^\"|\"$", ""));}

	) 'validif' anyExpressions 'else' message = STRING_LITERAL
	{$result = validIf(scope, $anyExpressions.result, constant($message.text.replaceAll("^\"|\"$", "")));}

;

warningEvaluations returns [Expression result]
:
	{Expression scope = null;}

	(
		'obj' '.' memberName = IDENTIFIER
		{scope = memberAccess(instance(), $memberName.text);}

		| 'obj' '.' methodName = IDENTIFIER '()'
		{scope = call(instance(), $methodName.text);}

		| scopeText = STRING_LITERAL
		{scope = constant($scopeText.text.replaceAll("^\"|\"$", ""));}

	) 'warnif' anyExpressions 'then' message = STRING_LITERAL
	{$result = warnIf(scope, $anyExpressions.result, constant($message.text.replaceAll("^\"|\"$", "")));}

;

terms returns [Expression result]
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

	| test = terms '?' if3True = anyExpressions ':' if3False = anyExpressions
	{ $result = conditional($test.result, $if3True.result, $if3False.result);}

	| nullable = terms '??' if2True = terms
	{ $result = coalesce($nullable.result, $if2True.result);}

	| sequence = terms '[' index = terms ']'
	{ $result = sequenceIndex($sequence.result, $index.result);}

	| sequence = terms '.' 'length'
	{ $result = sequenceLength($sequence.result);}

	| inst = terms '.' method = IDENTIFIER
	{List<Expression> params = new ArrayList<Expression>();}

	(
		'(' fisrtParam = anyExpressions
		{params.add($fisrtParam.result);}

		(
			',' otherParam = anyExpressions
			{params.add($otherParam.result);}

		)* ')'
		| '()'
	)
	{$result = call($inst.result, $method.text, params); }

	| inst = terms '.' member = IDENTIFIER
	{$result = memberAccess($inst.result, $member.text); }

	|
	{String clazz="";}

	'(' firstId = IDENTIFIER
	{clazz = $firstId.text;}

	(
		'.' otherId = IDENTIFIER
		{clazz += "." + $otherId.text;}

	)* ')' toCast = terms
	{
		$result =  $toCast.result;		
		if(!clazz.equals(""))
			$result = cast(clazz, $result);
	}

	| '(' exp = anyExpressions ')'
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
			{stringValues.add($str2.text.replaceAll("^\"|\"$", ""));}

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

unaryOperations returns [Expression result]
:
	'+'+ plus = terms
	{$result = plus($plus.result);}

	|
	{boolean applyNegative = false;}

	(
		'-'
		{applyNegative = !applyNegative;}

	)+ minus = terms
	{if(applyNegative) $result = negate($minus.result); else $result = $minus.result;}

	|
	{boolean applyComplement = false;}

	(
		'~'
		{applyComplement = !applyComplement;}

	)+ comp = terms
	{if(applyComplement) $result = complemented($comp.result); else $result = $comp.result;}

	|
	{boolean applyNot = false;}

	(
		'!'
		{applyNot = !applyNot;}

	)+ not = terms
	{if(applyNot) $result = not($not.result); else $result = $not.result;}

	| negate = terms
	{$result = $negate.result;}

;

multiplicityOperations returns [Expression result]
:
	left = unaryOperations
	{$result = $left.result;}

	(
		'*' right = unaryOperations
		{ $result = multiply($result, $right.result);}

		| '/' right = unaryOperations
		{ $result = divide($result, $right.result);}

		| '%' right = unaryOperations
		{ $result = modulo($result, $right.result);}

	)*
;

additiveOperations returns [Expression result]
:
	left = multiplicityOperations
	{$result = $left.result;}

	(
		'+' right = multiplicityOperations
		{ $result = add($result, $right.result);}

		| '-' right = multiplicityOperations
		{ $result = subtract($result, $right.result);}

	)*
;

shiftOperations returns [Expression result]
:
	left = additiveOperations
	{$result = $left.result;}

	(
		'<<' right = additiveOperations
		{ $result = leftShift($result, $right.result);}

		| '>>' right = additiveOperations
		{ $result = rightShift($result, $right.result);}

	)*
;

relationalOperations returns [Expression result]
:
	left = shiftOperations
	{$result = $left.result;}

	(
		'<' right = shiftOperations
		{ $result = lessThan($result, $right.result);}

		| '>' right = shiftOperations
		{ $result = greaterThan($result, $right.result);}

		| '<=' right = shiftOperations
		{ $result = lessThanOrEqual($result, $right.result);}

		| '>=' right = shiftOperations
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

equalityOperations returns [Expression result]
:
	left = relationalOperations
	{$result = $left.result;}

	(
		'==' right = relationalOperations
		{ $result = equal($result, $right.result);}

		| '!=' right = relationalOperations
		{ $result = notEqual($result, $right.result);}

	)*
;

bitwiseOperations returns [Expression result]
:
	left = equalityOperations
	{$result = $left.result;}

	(
		'&' right = equalityOperations
		{ $result = bitwiseAnd($result, $right.result);}

		| '|' right = equalityOperations
		{ $result = bitwiseOr($result, $right.result);}

		| '^' right = equalityOperations
		{ $result = bitwiseXor($result, $right.result);}

	)*
;

logicalOperations returns [Expression result]
:
	left = bitwiseOperations
	{$result = $left.result;}

	(
		'&&' right = bitwiseOperations
		{ $result = and($result, $right.result);}

		| '||' right = bitwiseOperations
		{ $result = or($result, $right.result);}

	)*
;

anyExpressions returns [Expression result]
:
	logicalOperations
	{$result = $logicalOperations.result;}

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