grammar CrossV;

@header {
package org.crossv.parsing.grammars.antlr4;
}

options {
	language = Java;
}

validations
:
	validation EOF
;

validation
:
	'when'
	(
		objInstanceOf
		| objInstanceOf '&&' contextInstanceOf
		| contextInstanceOf '&&' objInstanceOf
	) '[' evaluation
	(
		',' evaluation
	)* ']'
;

objInstanceOf
:
	'obj' 'instanceof' IDENTIFIER
	(
		'.' IDENTIFIER
	)*
;

contextInstanceOf
:
	'context' 'instanceof' IDENTIFIER
	(
		'.' IDENTIFIER
	)*
;

evaluation
:
	validif
	| warnif
;

validif
:
	(
		'obj' '.' IDENTIFIER
		| 'obj' '.' IDENTIFIER '()'
		| STRING_LITERAL
	) 'validif' expression 'else' STRING_LITERAL
;

warnif
:
	(
		'obj' '.' IDENTIFIER
		| 'obj' '.' IDENTIFIER '()'
		| STRING_LITERAL
	) 'warnif' expression 'then' STRING_LITERAL
;

term
:
	IDENTIFIER
	| STRING_LITERAL
	| INTEGER_LITERAL
	| BOOLEAN_LITERAL
	| FLOAT_LITERAL
	| '(' expression ')'
;

negation
:
	'!'* term
;

unary
:
	(
		'+'
		| '-'
	)* negation
;

multiply
:
	unary
	(
		(
			'*'
			| '/'
			| '%'
		) unary
	)*
;

add
:
	multiply
	(
		(
			'+'
			| '-'
		) multiply
	)*
;

relation
:
	add
	(
		(
			'=='
			| '!='
			| '<'
			| '<='
			| '>='
			| '>'
		) add
	)*
;

expression
:
	relation
	(
		(
			'&&'
			| '||'
		) relation
	)*
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