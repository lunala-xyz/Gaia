grammar Gaia;

// ----------------------
// Lexer Rules (Tokens)
// ----------------------

// Keywords (placed before IDENTIFIER to take precedence)
IMPORT : 'import';
FUNCTION : 'function' | 'fun' | 'func' | 'fn';
CONST : 'const';
LET : 'let';
FOR : 'for';
WHILE : 'while';
IF : 'if';
ELSE : 'else';
RETURN : 'return';
FAIL : 'fail';
PRIVATE : 'private';
PUBLIC : 'public';
AS : 'as';
NEW : 'new';
GROUP : 'group';
ONEOF : 'oneOf';

// Primitive/type keywords
TYPE_INT : 'int';
TYPE_FLOAT : 'float';
TYPE_NUMBER : 'number';
TYPE_STRING : 'string';
TYPE_BOOL : 'boolean';

// Boolean literals
TRUE : 'true';
FALSE : 'false';

// Operators
PLUS : '+';
MINUS : '-';
STAR : '*';
SLASH : '/';
PERCENT : '%';
EQ : '==';
NEQ : '!=';
LTE : '<=';
LT : '<';
GTE : '>=';
GT : '>';
ASSIGN : '=';
PLUS_ASSIGN : '+=';
MINUS_ASSIGN : '-=';
ARROW : '->';

// Logical operators (common in C/JS-like languages)
AND : '&&';
OR  : '||';
NOT : '!';

// Punctuation
LPAREN : '(';
RPAREN : ')';
LBRACE : '{';
RBRACE : '}';
LBRACK : '[';
RBRACK : ']';
SEMI : ';';
COLON : ':';
COMMA : ',';
DOT : '.';

// Identifiers and literals
IDENTIFIER : [a-zA-Z_] [a-zA-Z0-9_]*;

// Numeric literals: float before int to prefer longest match
FLOAT_LITERAL : [0-9]+ '.' [0-9]+ ([eE] [+-]? [0-9]+)? | [0-9]+ 'f';
INT_LITERAL : [0-9]+;

STRING_LITERAL
	: '"' ( ESC_SEQ | ~["\\\r\n] )* '"'
	;

fragment ESC_SEQ
	: '\\' [btnfr"'\\]
	| '\\' 'u' HEX HEX HEX HEX
	;

fragment HEX : [0-9a-fA-F];

// Comments and whitespace
LINE_COMMENT : '//' ~[\r\n]* -> skip;
BLOCK_COMMENT : '/*' .*? '*/' -> skip;

WS : [ \t\r\n]+ -> skip;

// Catch-all for unknown characters (helps debug lexer issues)
UNKNOWN : . ;


// ----------------------
// Parser Rules
// ----------------------

// Program root
prog
	: (importStmt | statement)* EOF
	;

// Imports (JS-like)
importStmt
	: IMPORT STRING_LITERAL (AS IDENTIFIER)? SEMI?
	;

// Statements
statement
	: block
	| variableDecl
	| constDecl
	| funcDecl
	| ifStmt
	| forStmt
	| whileStmt
	| returnStmt
	| failStmt
	| exprStatement
	| SEMI
	;

// Blocks
block
	: LBRACE (statement | exprStatement)* RBRACE
	;

// Variable and constant declarations
variableDecl
	: (PUBLIC | PRIVATE)? LET IDENTIFIER (COLON typeExpr)? (ASSIGN expression)? SEMI?
	;

constDecl
	: (PUBLIC | PRIVATE)? CONST IDENTIFIER (COLON typeExpr)? ASSIGN expression SEMI?
	;

// Function declarations (C/JS-like)
funcDecl
	: (PUBLIC | PRIVATE)? FUNCTION IDENTIFIER? LPAREN paramList? RPAREN (COLON typeExpr)? block
	;

// Function expression (anonymous functions) usable in expressions
funcExpr
	: FUNCTION IDENTIFIER? LPAREN paramList? RPAREN (COLON typeExpr)? block
	;

// Parameters: allow optional type annotation (Type before identifier is supported, but also just identifier)
paramList
	: param (COMMA param)*
	;

param
	: (typeExpr)? IDENTIFIER
	;

// Control flow
ifStmt
	: IF LPAREN expression RPAREN statement (ELSE statement)?
	;

forStmt
	: FOR LPAREN forInit? SEMI? expression? SEMI? forUpdate? RPAREN statement
	;

forInit
	: variableDeclNoSemi
	| exprList
	;

variableDeclNoSemi
	: (PUBLIC | PRIVATE)? LET IDENTIFIER (COLON typeExpr)? (ASSIGN expression)?
	;

forUpdate
	: exprList
	;

exprList
	: expression (COMMA expression)*
	;

whileStmt
	: WHILE LPAREN expression RPAREN statement
	;

returnStmt
	: RETURN expression? SEMI?
	;

failStmt
	: FAIL expression? SEMI?
	;

// Expression statements
exprStatement
	: expression SEMI?
	;

// ----------------------
// Expressions (JS/C-like precedence)
// ----------------------

expression
	: assignment
	;

assignment
	: conditional ( (ASSIGN | PLUS_ASSIGN | MINUS_ASSIGN) assignment )?
	;

conditional
	: logicalOr // (ternary operator could be added later)
	;

logicalOr
	: logicalAnd ( OR logicalAnd )*
	;

logicalAnd
	: equality ( AND equality )*
	;

equality
	: relational ( (EQ | NEQ) relational )*
	;

relational
	: additive ( (LT | LTE | GT | GTE) additive )*
	;

additive
	: multiplicative ( (PLUS | MINUS) multiplicative )*
	;

multiplicative
	: unary ( (STAR | SLASH | PERCENT) unary )*
	;

unary
	: (PLUS | MINUS | NOT) unary
	| postfix
	;

postfix
	: primary (postfixOp)*
	;

postfixOp
	: call
	| memberAccess
	| indexAccess
	;

// Calls and accessors
call
	: LPAREN argumentList? RPAREN
	;

argumentList
	: expression (COMMA expression)*
	;

memberAccess
	: DOT IDENTIFIER
	;

indexAccess
	: LBRACK expression RBRACK
	;

// Primary expressions
primary
	: INT_LITERAL
	| FLOAT_LITERAL
	| STRING_LITERAL
	| TRUE
	| FALSE
	| IDENTIFIER
	| arrayLiteral
	| objectLiteral
	| funcExpr
	| newExpression
	| LPAREN expression RPAREN
	;

newExpression
	: NEW primary
	;

arrayLiteral
	: LBRACK (expression (COMMA expression)*)? RBRACK
	;

objectLiteral
	: LBRACE (property (COMMA property)*)? RBRACE
	;

property
	: (IDENTIFIER | STRING_LITERAL) COLON expression
	;

// Types
typeExpr
	: TYPE_INT
	| TYPE_FLOAT
	| TYPE_NUMBER
	| TYPE_STRING
	| TYPE_BOOL
	| ONEOF IDENTIFIER
	| IDENTIFIER
	| typeExpr LBRACK RBRACK // array type, e.g., int[]
	;
