package me.minekid.gaia.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class GaiaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IMPORT=1, FUNCTION=2, CONST=3, LET=4, FOR=5, WHILE=6, IF=7, ELSE=8, RETURN=9, 
		FAIL=10, PRIVATE=11, PUBLIC=12, AS=13, NEW=14, GROUP=15, ONEOF=16, TYPE_INT=17, 
		TYPE_FLOAT=18, TYPE_NUMBER=19, TYPE_STRING=20, TYPE_BOOL=21, TRUE=22, 
		FALSE=23, PLUS=24, MINUS=25, STAR=26, SLASH=27, PERCENT=28, EQ=29, NEQ=30, 
		LTE=31, LT=32, GTE=33, GT=34, ASSIGN=35, PLUS_ASSIGN=36, MINUS_ASSIGN=37, 
		ARROW=38, AND=39, OR=40, NOT=41, LPAREN=42, RPAREN=43, LBRACE=44, RBRACE=45, 
		LBRACK=46, RBRACK=47, SEMI=48, COLON=49, COMMA=50, DOT=51, IDENTIFIER=52, 
		FLOAT_LITERAL=53, INT_LITERAL=54, STRING_LITERAL=55, LINE_COMMENT=56, 
		BLOCK_COMMENT=57, WS=58, UNKNOWN=59;
	public static final int
		RULE_prog = 0, RULE_importStmt = 1, RULE_statement = 2, RULE_block = 3, 
		RULE_variableDecl = 4, RULE_constDecl = 5, RULE_funcDecl = 6, RULE_funcExpr = 7, 
		RULE_paramList = 8, RULE_param = 9, RULE_ifStmt = 10, RULE_forStmt = 11, 
		RULE_forInit = 12, RULE_variableDeclNoSemi = 13, RULE_forUpdate = 14, 
		RULE_exprList = 15, RULE_whileStmt = 16, RULE_returnStmt = 17, RULE_failStmt = 18, 
		RULE_exprStatement = 19, RULE_expression = 20, RULE_assignment = 21, RULE_conditional = 22, 
		RULE_logicalOr = 23, RULE_logicalAnd = 24, RULE_equality = 25, RULE_relational = 26, 
		RULE_additive = 27, RULE_multiplicative = 28, RULE_unary = 29, RULE_postfix = 30, 
		RULE_postfixOp = 31, RULE_call = 32, RULE_argumentList = 33, RULE_memberAccess = 34, 
		RULE_indexAccess = 35, RULE_primary = 36, RULE_newExpression = 37, RULE_arrayLiteral = 38, 
		RULE_objectLiteral = 39, RULE_property = 40, RULE_typeExpr = 41;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "importStmt", "statement", "block", "variableDecl", "constDecl", 
			"funcDecl", "funcExpr", "paramList", "param", "ifStmt", "forStmt", "forInit", 
			"variableDeclNoSemi", "forUpdate", "exprList", "whileStmt", "returnStmt", 
			"failStmt", "exprStatement", "expression", "assignment", "conditional", 
			"logicalOr", "logicalAnd", "equality", "relational", "additive", "multiplicative", 
			"unary", "postfix", "postfixOp", "call", "argumentList", "memberAccess", 
			"indexAccess", "primary", "newExpression", "arrayLiteral", "objectLiteral", 
			"property", "typeExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'import'", null, "'const'", "'let'", "'for'", "'while'", "'if'", 
			"'else'", "'return'", "'fail'", "'private'", "'public'", "'as'", "'new'", 
			"'group'", "'oneOf'", "'int'", "'float'", "'number'", "'string'", "'boolean'", 
			"'true'", "'false'", "'+'", "'-'", "'*'", "'/'", "'%'", "'=='", "'!='", 
			"'<='", "'<'", "'>='", "'>'", "'='", "'+='", "'-='", "'->'", "'&&'", 
			"'||'", "'!'", "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "':'", 
			"','", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IMPORT", "FUNCTION", "CONST", "LET", "FOR", "WHILE", "IF", "ELSE", 
			"RETURN", "FAIL", "PRIVATE", "PUBLIC", "AS", "NEW", "GROUP", "ONEOF", 
			"TYPE_INT", "TYPE_FLOAT", "TYPE_NUMBER", "TYPE_STRING", "TYPE_BOOL", 
			"TRUE", "FALSE", "PLUS", "MINUS", "STAR", "SLASH", "PERCENT", "EQ", "NEQ", 
			"LTE", "LT", "GTE", "GT", "ASSIGN", "PLUS_ASSIGN", "MINUS_ASSIGN", "ARROW", 
			"AND", "OR", "NOT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", 
			"RBRACK", "SEMI", "COLON", "COMMA", "DOT", "IDENTIFIER", "FLOAT_LITERAL", 
			"INT_LITERAL", "STRING_LITERAL", "LINE_COMMENT", "BLOCK_COMMENT", "WS", 
			"UNKNOWN"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Gaia.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GaiaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(GaiaParser.EOF, 0); }
		public List<ImportStmtContext> importStmt() {
			return getRuleContexts(ImportStmtContext.class);
		}
		public ImportStmtContext importStmt(int i) {
			return getRuleContext(ImportStmtContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67930027450195710L) != 0)) {
				{
				setState(86);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IMPORT:
					{
					setState(84);
					importStmt();
					}
					break;
				case FUNCTION:
				case CONST:
				case LET:
				case FOR:
				case WHILE:
				case IF:
				case RETURN:
				case FAIL:
				case PRIVATE:
				case PUBLIC:
				case NEW:
				case TRUE:
				case FALSE:
				case PLUS:
				case MINUS:
				case NOT:
				case LPAREN:
				case LBRACE:
				case LBRACK:
				case SEMI:
				case IDENTIFIER:
				case FLOAT_LITERAL:
				case INT_LITERAL:
				case STRING_LITERAL:
					{
					setState(85);
					statement();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(91);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportStmtContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(GaiaParser.IMPORT, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(GaiaParser.STRING_LITERAL, 0); }
		public TerminalNode AS() { return getToken(GaiaParser.AS, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public ImportStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterImportStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitImportStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitImportStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportStmtContext importStmt() throws RecognitionException {
		ImportStmtContext _localctx = new ImportStmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_importStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(IMPORT);
			setState(94);
			match(STRING_LITERAL);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(95);
				match(AS);
				setState(96);
				match(IDENTIFIER);
				}
			}

			setState(100);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(99);
				match(SEMI);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VariableDeclContext variableDecl() {
			return getRuleContext(VariableDeclContext.class,0);
		}
		public ConstDeclContext constDecl() {
			return getRuleContext(ConstDeclContext.class,0);
		}
		public FuncDeclContext funcDecl() {
			return getRuleContext(FuncDeclContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public ForStmtContext forStmt() {
			return getRuleContext(ForStmtContext.class,0);
		}
		public WhileStmtContext whileStmt() {
			return getRuleContext(WhileStmtContext.class,0);
		}
		public ReturnStmtContext returnStmt() {
			return getRuleContext(ReturnStmtContext.class,0);
		}
		public FailStmtContext failStmt() {
			return getRuleContext(FailStmtContext.class,0);
		}
		public ExprStatementContext exprStatement() {
			return getRuleContext(ExprStatementContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(113);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				variableDecl();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				constDecl();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				funcDecl();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				ifStmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(107);
				forStmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(108);
				whileStmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(109);
				returnStmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(110);
				failStmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(111);
				exprStatement();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(112);
				match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(GaiaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GaiaParser.RBRACE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<ExprStatementContext> exprStatement() {
			return getRuleContexts(ExprStatementContext.class);
		}
		public ExprStatementContext exprStatement(int i) {
			return getRuleContext(ExprStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(LBRACE);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67930027450195708L) != 0)) {
				{
				setState(118);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(116);
					statement();
					}
					break;
				case 2:
					{
					setState(117);
					exprStatement();
					}
					break;
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(GaiaParser.LET, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(GaiaParser.COLON, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(GaiaParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public TerminalNode PUBLIC() { return getToken(GaiaParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(GaiaParser.PRIVATE, 0); }
		public VariableDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterVariableDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitVariableDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitVariableDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclContext variableDecl() throws RecognitionException {
		VariableDeclContext _localctx = new VariableDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variableDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIVATE || _la==PUBLIC) {
				{
				setState(125);
				_la = _input.LA(1);
				if ( !(_la==PRIVATE || _la==PUBLIC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(128);
			match(LET);
			setState(129);
			match(IDENTIFIER);
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(130);
				match(COLON);
				setState(131);
				typeExpr(0);
				}
			}

			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(134);
				match(ASSIGN);
				setState(135);
				expression();
				}
			}

			setState(139);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(138);
				match(SEMI);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstDeclContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(GaiaParser.CONST, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TerminalNode ASSIGN() { return getToken(GaiaParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode COLON() { return getToken(GaiaParser.COLON, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public TerminalNode PUBLIC() { return getToken(GaiaParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(GaiaParser.PRIVATE, 0); }
		public ConstDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterConstDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitConstDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitConstDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstDeclContext constDecl() throws RecognitionException {
		ConstDeclContext _localctx = new ConstDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIVATE || _la==PUBLIC) {
				{
				setState(141);
				_la = _input.LA(1);
				if ( !(_la==PRIVATE || _la==PUBLIC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(144);
			match(CONST);
			setState(145);
			match(IDENTIFIER);
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(146);
				match(COLON);
				setState(147);
				typeExpr(0);
				}
			}

			setState(150);
			match(ASSIGN);
			setState(151);
			expression();
			setState(153);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(152);
				match(SEMI);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDeclContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(GaiaParser.FUNCTION, 0); }
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(GaiaParser.COLON, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(GaiaParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(GaiaParser.PRIVATE, 0); }
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterFuncDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitFuncDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIVATE || _la==PUBLIC) {
				{
				setState(155);
				_la = _input.LA(1);
				if ( !(_la==PRIVATE || _la==PUBLIC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(158);
			match(FUNCTION);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(159);
				match(IDENTIFIER);
				}
			}

			setState(162);
			match(LPAREN);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4503599631499264L) != 0)) {
				{
				setState(163);
				paramList();
				}
			}

			setState(166);
			match(RPAREN);
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(167);
				match(COLON);
				setState(168);
				typeExpr(0);
				}
			}

			setState(171);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncExprContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(GaiaParser.FUNCTION, 0); }
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(GaiaParser.COLON, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public FuncExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncExprContext funcExpr() throws RecognitionException {
		FuncExprContext _localctx = new FuncExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(FUNCTION);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(174);
				match(IDENTIFIER);
				}
			}

			setState(177);
			match(LPAREN);
			setState(179);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4503599631499264L) != 0)) {
				{
				setState(178);
				paramList();
				}
			}

			setState(181);
			match(RPAREN);
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(182);
				match(COLON);
				setState(183);
				typeExpr(0);
				}
			}

			setState(186);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GaiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GaiaParser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			param();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(189);
				match(COMMA);
				setState(190);
				param();
				}
				}
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(196);
				typeExpr(0);
				}
				break;
			}
			setState(199);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(GaiaParser.IF, 0); }
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(GaiaParser.ELSE, 0); }
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(IF);
			setState(202);
			match(LPAREN);
			setState(203);
			expression();
			setState(204);
			match(RPAREN);
			setState(205);
			statement();
			setState(208);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(206);
				match(ELSE);
				setState(207);
				statement();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForStmtContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(GaiaParser.FOR, 0); }
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public List<TerminalNode> SEMI() { return getTokens(GaiaParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(GaiaParser.SEMI, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStmtContext forStmt() throws RecognitionException {
		ForStmtContext _localctx = new ForStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_forStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(FOR);
			setState(211);
			match(LPAREN);
			setState(213);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(212);
				forInit();
				}
				break;
			}
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(215);
				match(SEMI);
				}
				break;
			}
			setState(219);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(218);
				expression();
				}
				break;
			}
			setState(222);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(221);
				match(SEMI);
				}
			}

			setState(225);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67648552473477124L) != 0)) {
				{
				setState(224);
				forUpdate();
				}
			}

			setState(227);
			match(RPAREN);
			setState(228);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForInitContext extends ParserRuleContext {
		public VariableDeclNoSemiContext variableDeclNoSemi() {
			return getRuleContext(VariableDeclNoSemiContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitForInit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitForInit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_forInit);
		try {
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LET:
			case PRIVATE:
			case PUBLIC:
				enterOuterAlt(_localctx, 1);
				{
				setState(230);
				variableDeclNoSemi();
				}
				break;
			case FUNCTION:
			case NEW:
			case TRUE:
			case FALSE:
			case PLUS:
			case MINUS:
			case NOT:
			case LPAREN:
			case LBRACE:
			case LBRACK:
			case IDENTIFIER:
			case FLOAT_LITERAL:
			case INT_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(231);
				exprList();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclNoSemiContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(GaiaParser.LET, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TerminalNode COLON() { return getToken(GaiaParser.COLON, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(GaiaParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PUBLIC() { return getToken(GaiaParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(GaiaParser.PRIVATE, 0); }
		public VariableDeclNoSemiContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclNoSemi; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterVariableDeclNoSemi(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitVariableDeclNoSemi(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitVariableDeclNoSemi(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclNoSemiContext variableDeclNoSemi() throws RecognitionException {
		VariableDeclNoSemiContext _localctx = new VariableDeclNoSemiContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_variableDeclNoSemi);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PRIVATE || _la==PUBLIC) {
				{
				setState(234);
				_la = _input.LA(1);
				if ( !(_la==PRIVATE || _la==PUBLIC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(237);
			match(LET);
			setState(238);
			match(IDENTIFIER);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(239);
				match(COLON);
				setState(240);
				typeExpr(0);
				}
			}

			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(243);
				match(ASSIGN);
				setState(244);
				expression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForUpdateContext extends ParserRuleContext {
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitForUpdate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitForUpdate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			exprList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GaiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GaiaParser.COMMA, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			expression();
			setState(254);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(250);
				match(COMMA);
				setState(251);
				expression();
				}
				}
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileStmtContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(GaiaParser.WHILE, 0); }
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStmtContext whileStmt() throws RecognitionException {
		WhileStmtContext _localctx = new WhileStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_whileStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(WHILE);
			setState(258);
			match(LPAREN);
			setState(259);
			expression();
			setState(260);
			match(RPAREN);
			setState(261);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnStmtContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(GaiaParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public ReturnStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnStmtContext returnStmt() throws RecognitionException {
		ReturnStmtContext _localctx = new ReturnStmtContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_returnStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			match(RETURN);
			setState(265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(264);
				expression();
				}
				break;
			}
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(267);
				match(SEMI);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FailStmtContext extends ParserRuleContext {
		public TerminalNode FAIL() { return getToken(GaiaParser.FAIL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public FailStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_failStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterFailStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitFailStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitFailStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FailStmtContext failStmt() throws RecognitionException {
		FailStmtContext _localctx = new FailStmtContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_failStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(FAIL);
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(271);
				expression();
				}
				break;
			}
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(274);
				match(SEMI);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GaiaParser.SEMI, 0); }
		public ExprStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterExprStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitExprStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitExprStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStatementContext exprStatement() throws RecognitionException {
		ExprStatementContext _localctx = new ExprStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_exprStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			expression();
			setState(279);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(278);
				match(SEMI);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			assignment();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(GaiaParser.ASSIGN, 0); }
		public TerminalNode PLUS_ASSIGN() { return getToken(GaiaParser.PLUS_ASSIGN, 0); }
		public TerminalNode MINUS_ASSIGN() { return getToken(GaiaParser.MINUS_ASSIGN, 0); }
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			conditional();
			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518168576L) != 0)) {
				{
				setState(284);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 240518168576L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(285);
				assignment();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConditionalContext extends ParserRuleContext {
		public LogicalOrContext logicalOr() {
			return getRuleContext(LogicalOrContext.class,0);
		}
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitConditional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitConditional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_conditional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			logicalOr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalOrContext extends ParserRuleContext {
		public List<LogicalAndContext> logicalAnd() {
			return getRuleContexts(LogicalAndContext.class);
		}
		public LogicalAndContext logicalAnd(int i) {
			return getRuleContext(LogicalAndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(GaiaParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(GaiaParser.OR, i);
		}
		public LogicalOrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterLogicalOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitLogicalOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitLogicalOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalOrContext logicalOr() throws RecognitionException {
		LogicalOrContext _localctx = new LogicalOrContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_logicalOr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			logicalAnd();
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(291);
				match(OR);
				setState(292);
				logicalAnd();
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LogicalAndContext extends ParserRuleContext {
		public List<EqualityContext> equality() {
			return getRuleContexts(EqualityContext.class);
		}
		public EqualityContext equality(int i) {
			return getRuleContext(EqualityContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(GaiaParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(GaiaParser.AND, i);
		}
		public LogicalAndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalAnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterLogicalAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitLogicalAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitLogicalAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LogicalAndContext logicalAnd() throws RecognitionException {
		LogicalAndContext _localctx = new LogicalAndContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_logicalAnd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			equality();
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(299);
				match(AND);
				setState(300);
				equality();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EqualityContext extends ParserRuleContext {
		public List<RelationalContext> relational() {
			return getRuleContexts(RelationalContext.class);
		}
		public RelationalContext relational(int i) {
			return getRuleContext(RelationalContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(GaiaParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(GaiaParser.EQ, i);
		}
		public List<TerminalNode> NEQ() { return getTokens(GaiaParser.NEQ); }
		public TerminalNode NEQ(int i) {
			return getToken(GaiaParser.NEQ, i);
		}
		public EqualityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equality; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterEquality(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitEquality(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitEquality(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EqualityContext equality() throws RecognitionException {
		EqualityContext _localctx = new EqualityContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_equality);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			relational();
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EQ || _la==NEQ) {
				{
				{
				setState(307);
				_la = _input.LA(1);
				if ( !(_la==EQ || _la==NEQ) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(308);
				relational();
				}
				}
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RelationalContext extends ParserRuleContext {
		public List<AdditiveContext> additive() {
			return getRuleContexts(AdditiveContext.class);
		}
		public AdditiveContext additive(int i) {
			return getRuleContext(AdditiveContext.class,i);
		}
		public List<TerminalNode> LT() { return getTokens(GaiaParser.LT); }
		public TerminalNode LT(int i) {
			return getToken(GaiaParser.LT, i);
		}
		public List<TerminalNode> LTE() { return getTokens(GaiaParser.LTE); }
		public TerminalNode LTE(int i) {
			return getToken(GaiaParser.LTE, i);
		}
		public List<TerminalNode> GT() { return getTokens(GaiaParser.GT); }
		public TerminalNode GT(int i) {
			return getToken(GaiaParser.GT, i);
		}
		public List<TerminalNode> GTE() { return getTokens(GaiaParser.GTE); }
		public TerminalNode GTE(int i) {
			return getToken(GaiaParser.GTE, i);
		}
		public RelationalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterRelational(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitRelational(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitRelational(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationalContext relational() throws RecognitionException {
		RelationalContext _localctx = new RelationalContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_relational);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(314);
			additive();
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) {
				{
				{
				setState(315);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32212254720L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(316);
				additive();
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AdditiveContext extends ParserRuleContext {
		public List<MultiplicativeContext> multiplicative() {
			return getRuleContexts(MultiplicativeContext.class);
		}
		public MultiplicativeContext multiplicative(int i) {
			return getRuleContext(MultiplicativeContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(GaiaParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(GaiaParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(GaiaParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(GaiaParser.MINUS, i);
		}
		public AdditiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additive; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterAdditive(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitAdditive(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitAdditive(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveContext additive() throws RecognitionException {
		AdditiveContext _localctx = new AdditiveContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_additive);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(322);
			multiplicative();
			setState(327);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(323);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(324);
					multiplicative();
					}
					} 
				}
				setState(329);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultiplicativeContext extends ParserRuleContext {
		public List<UnaryContext> unary() {
			return getRuleContexts(UnaryContext.class);
		}
		public UnaryContext unary(int i) {
			return getRuleContext(UnaryContext.class,i);
		}
		public List<TerminalNode> STAR() { return getTokens(GaiaParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(GaiaParser.STAR, i);
		}
		public List<TerminalNode> SLASH() { return getTokens(GaiaParser.SLASH); }
		public TerminalNode SLASH(int i) {
			return getToken(GaiaParser.SLASH, i);
		}
		public List<TerminalNode> PERCENT() { return getTokens(GaiaParser.PERCENT); }
		public TerminalNode PERCENT(int i) {
			return getToken(GaiaParser.PERCENT, i);
		}
		public MultiplicativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicative; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterMultiplicative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitMultiplicative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitMultiplicative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeContext multiplicative() throws RecognitionException {
		MultiplicativeContext _localctx = new MultiplicativeContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_multiplicative);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			unary();
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 469762048L) != 0)) {
				{
				{
				setState(331);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 469762048L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(332);
				unary();
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryContext extends ParserRuleContext {
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(GaiaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(GaiaParser.MINUS, 0); }
		public TerminalNode NOT() { return getToken(GaiaParser.NOT, 0); }
		public PostfixContext postfix() {
			return getRuleContext(PostfixContext.class,0);
		}
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterUnary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitUnary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitUnary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_unary);
		int _la;
		try {
			setState(341);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PLUS:
			case MINUS:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2199073587200L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(339);
				unary();
				}
				break;
			case FUNCTION:
			case NEW:
			case TRUE:
			case FALSE:
			case LPAREN:
			case LBRACE:
			case LBRACK:
			case IDENTIFIER:
			case FLOAT_LITERAL:
			case INT_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				postfix();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixContext extends ParserRuleContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public List<PostfixOpContext> postfixOp() {
			return getRuleContexts(PostfixOpContext.class);
		}
		public PostfixOpContext postfixOp(int i) {
			return getRuleContext(PostfixOpContext.class,i);
		}
		public PostfixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixContext postfix() throws RecognitionException {
		PostfixContext _localctx = new PostfixContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_postfix);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			primary();
			setState(347);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(344);
					postfixOp();
					}
					} 
				}
				setState(349);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixOpContext extends ParserRuleContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public MemberAccessContext memberAccess() {
			return getRuleContext(MemberAccessContext.class,0);
		}
		public IndexAccessContext indexAccess() {
			return getRuleContext(IndexAccessContext.class,0);
		}
		public PostfixOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixOp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterPostfixOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitPostfixOp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitPostfixOp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixOpContext postfixOp() throws RecognitionException {
		PostfixOpContext _localctx = new PostfixOpContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_postfixOp);
		try {
			setState(353);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(350);
				call();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
				memberAccess();
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 3);
				{
				setState(352);
				indexAccess();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_call);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			match(LPAREN);
			setState(357);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67648552473477124L) != 0)) {
				{
				setState(356);
				argumentList();
				}
			}

			setState(359);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GaiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GaiaParser.COMMA, i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterArgumentList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitArgumentList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitArgumentList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			expression();
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(362);
				match(COMMA);
				setState(363);
				expression();
				}
				}
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MemberAccessContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(GaiaParser.DOT, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public MemberAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterMemberAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitMemberAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitMemberAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MemberAccessContext memberAccess() throws RecognitionException {
		MemberAccessContext _localctx = new MemberAccessContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_memberAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(DOT);
			setState(370);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexAccessContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(GaiaParser.LBRACK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(GaiaParser.RBRACK, 0); }
		public IndexAccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexAccess; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterIndexAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitIndexAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitIndexAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexAccessContext indexAccess() throws RecognitionException {
		IndexAccessContext _localctx = new IndexAccessContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_indexAccess);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			match(LBRACK);
			setState(373);
			expression();
			setState(374);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode INT_LITERAL() { return getToken(GaiaParser.INT_LITERAL, 0); }
		public TerminalNode FLOAT_LITERAL() { return getToken(GaiaParser.FLOAT_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(GaiaParser.STRING_LITERAL, 0); }
		public TerminalNode TRUE() { return getToken(GaiaParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(GaiaParser.FALSE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public ArrayLiteralContext arrayLiteral() {
			return getRuleContext(ArrayLiteralContext.class,0);
		}
		public ObjectLiteralContext objectLiteral() {
			return getRuleContext(ObjectLiteralContext.class,0);
		}
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GaiaParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GaiaParser.RPAREN, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_primary);
		try {
			setState(390);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				match(INT_LITERAL);
				}
				break;
			case FLOAT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(FLOAT_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
				match(STRING_LITERAL);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(379);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(380);
				match(FALSE);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 6);
				{
				setState(381);
				match(IDENTIFIER);
				}
				break;
			case LBRACK:
				enterOuterAlt(_localctx, 7);
				{
				setState(382);
				arrayLiteral();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 8);
				{
				setState(383);
				objectLiteral();
				}
				break;
			case FUNCTION:
				enterOuterAlt(_localctx, 9);
				{
				setState(384);
				funcExpr();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 10);
				{
				setState(385);
				newExpression();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 11);
				{
				setState(386);
				match(LPAREN);
				setState(387);
				expression();
				setState(388);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NewExpressionContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(GaiaParser.NEW, 0); }
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_newExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			match(NEW);
			setState(393);
			primary();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArrayLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACK() { return getToken(GaiaParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(GaiaParser.RBRACK, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GaiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GaiaParser.COMMA, i);
		}
		public ArrayLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterArrayLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitArrayLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitArrayLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayLiteralContext arrayLiteral() throws RecognitionException {
		ArrayLiteralContext _localctx = new ArrayLiteralContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_arrayLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395);
			match(LBRACK);
			setState(404);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 67648552473477124L) != 0)) {
				{
				setState(396);
				expression();
				setState(401);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(397);
					match(COMMA);
					setState(398);
					expression();
					}
					}
					setState(403);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(406);
			match(RBRACK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObjectLiteralContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(GaiaParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(GaiaParser.RBRACE, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GaiaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GaiaParser.COMMA, i);
		}
		public ObjectLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_objectLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterObjectLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitObjectLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitObjectLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectLiteralContext objectLiteral() throws RecognitionException {
		ObjectLiteralContext _localctx = new ObjectLiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_objectLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(LBRACE);
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER || _la==STRING_LITERAL) {
				{
				setState(409);
				property();
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(410);
					match(COMMA);
					setState(411);
					property();
					}
					}
					setState(416);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(419);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(GaiaParser.COLON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(GaiaParser.STRING_LITERAL, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_property);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==STRING_LITERAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(422);
			match(COLON);
			setState(423);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeExprContext extends ParserRuleContext {
		public TerminalNode TYPE_INT() { return getToken(GaiaParser.TYPE_INT, 0); }
		public TerminalNode TYPE_FLOAT() { return getToken(GaiaParser.TYPE_FLOAT, 0); }
		public TerminalNode TYPE_NUMBER() { return getToken(GaiaParser.TYPE_NUMBER, 0); }
		public TerminalNode TYPE_STRING() { return getToken(GaiaParser.TYPE_STRING, 0); }
		public TerminalNode TYPE_BOOL() { return getToken(GaiaParser.TYPE_BOOL, 0); }
		public TerminalNode ONEOF() { return getToken(GaiaParser.ONEOF, 0); }
		public TerminalNode IDENTIFIER() { return getToken(GaiaParser.IDENTIFIER, 0); }
		public TypeExprContext typeExpr() {
			return getRuleContext(TypeExprContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(GaiaParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(GaiaParser.RBRACK, 0); }
		public TypeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).enterTypeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GaiaListener ) ((GaiaListener)listener).exitTypeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GaiaVisitor ) return ((GaiaVisitor<? extends T>)visitor).visitTypeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeExprContext typeExpr() throws RecognitionException {
		return typeExpr(0);
	}

	private TypeExprContext typeExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeExprContext _localctx = new TypeExprContext(_ctx, _parentState);
		TypeExprContext _prevctx = _localctx;
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_typeExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE_INT:
				{
				setState(426);
				match(TYPE_INT);
				}
				break;
			case TYPE_FLOAT:
				{
				setState(427);
				match(TYPE_FLOAT);
				}
				break;
			case TYPE_NUMBER:
				{
				setState(428);
				match(TYPE_NUMBER);
				}
				break;
			case TYPE_STRING:
				{
				setState(429);
				match(TYPE_STRING);
				}
				break;
			case TYPE_BOOL:
				{
				setState(430);
				match(TYPE_BOOL);
				}
				break;
			case ONEOF:
				{
				setState(431);
				match(ONEOF);
				setState(432);
				match(IDENTIFIER);
				}
				break;
			case IDENTIFIER:
				{
				setState(433);
				match(IDENTIFIER);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(441);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_typeExpr);
					setState(436);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(437);
					match(LBRACK);
					setState(438);
					match(RBRACK);
					}
					} 
				}
				setState(443);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 41:
			return typeExpr_sempred((TypeExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean typeExpr_sempred(TypeExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001;\u01bd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0001\u0000\u0001\u0000\u0005\u0000W\b\u0000\n"+
		"\u0000\f\u0000Z\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001b\b\u0001\u0001\u0001\u0003\u0001"+
		"e\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002r\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"w\b\u0003\n\u0003\f\u0003z\t\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0003\u0004\u007f\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u0085\b\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0089\b"+
		"\u0004\u0001\u0004\u0003\u0004\u008c\b\u0004\u0001\u0005\u0003\u0005\u008f"+
		"\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0095"+
		"\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u009a\b\u0005"+
		"\u0001\u0006\u0003\u0006\u009d\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u00a1\b\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00a5\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00aa\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0003\u0007\u00b0\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0003\u0007\u00b4\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u00b9\b\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u00c0\b\b\n\b\f\b\u00c3\t\b\u0001\t\u0003\t\u00c6\b\t\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u00d1"+
		"\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00d6\b\u000b\u0001"+
		"\u000b\u0003\u000b\u00d9\b\u000b\u0001\u000b\u0003\u000b\u00dc\b\u000b"+
		"\u0001\u000b\u0003\u000b\u00df\b\u000b\u0001\u000b\u0003\u000b\u00e2\b"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0003\f\u00e9"+
		"\b\f\u0001\r\u0003\r\u00ec\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r"+
		"\u00f2\b\r\u0001\r\u0001\r\u0003\r\u00f6\b\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00fd\b\u000f\n\u000f\f\u000f"+
		"\u0100\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0011\u0001\u0011\u0003\u0011\u010a\b\u0011\u0001\u0011"+
		"\u0003\u0011\u010d\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u0111\b"+
		"\u0012\u0001\u0012\u0003\u0012\u0114\b\u0012\u0001\u0013\u0001\u0013\u0003"+
		"\u0013\u0118\b\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u011f\b\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0005\u0017\u0126\b\u0017\n\u0017\f\u0017\u0129\t\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u012e\b\u0018\n\u0018"+
		"\f\u0018\u0131\t\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019"+
		"\u0136\b\u0019\n\u0019\f\u0019\u0139\t\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0005\u001a\u013e\b\u001a\n\u001a\f\u001a\u0141\t\u001a\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0005\u001b\u0146\b\u001b\n\u001b\f\u001b\u0149"+
		"\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0005\u001c\u014e\b\u001c"+
		"\n\u001c\f\u001c\u0151\t\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u0156\b\u001d\u0001\u001e\u0001\u001e\u0005\u001e\u015a\b\u001e"+
		"\n\u001e\f\u001e\u015d\t\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u0162\b\u001f\u0001 \u0001 \u0003 \u0166\b \u0001 \u0001 \u0001"+
		"!\u0001!\u0001!\u0005!\u016d\b!\n!\f!\u0170\t!\u0001\"\u0001\"\u0001\""+
		"\u0001#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001$\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001$\u0003$\u0187"+
		"\b$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001&\u0005&\u0190\b&\n"+
		"&\f&\u0193\t&\u0003&\u0195\b&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0005\'\u019d\b\'\n\'\f\'\u01a0\t\'\u0003\'\u01a2\b\'\u0001\'\u0001"+
		"\'\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0003)\u01b3\b)\u0001)\u0001)\u0001)\u0005)\u01b8"+
		"\b)\n)\f)\u01bb\t)\u0001)\u0000\u0001R*\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:"+
		"<>@BDFHJLNPR\u0000\b\u0001\u0000\u000b\f\u0001\u0000#%\u0001\u0000\u001d"+
		"\u001e\u0001\u0000\u001f\"\u0001\u0000\u0018\u0019\u0001\u0000\u001a\u001c"+
		"\u0002\u0000\u0018\u0019))\u0002\u00004477\u01e4\u0000X\u0001\u0000\u0000"+
		"\u0000\u0002]\u0001\u0000\u0000\u0000\u0004q\u0001\u0000\u0000\u0000\u0006"+
		"s\u0001\u0000\u0000\u0000\b~\u0001\u0000\u0000\u0000\n\u008e\u0001\u0000"+
		"\u0000\u0000\f\u009c\u0001\u0000\u0000\u0000\u000e\u00ad\u0001\u0000\u0000"+
		"\u0000\u0010\u00bc\u0001\u0000\u0000\u0000\u0012\u00c5\u0001\u0000\u0000"+
		"\u0000\u0014\u00c9\u0001\u0000\u0000\u0000\u0016\u00d2\u0001\u0000\u0000"+
		"\u0000\u0018\u00e8\u0001\u0000\u0000\u0000\u001a\u00eb\u0001\u0000\u0000"+
		"\u0000\u001c\u00f7\u0001\u0000\u0000\u0000\u001e\u00f9\u0001\u0000\u0000"+
		"\u0000 \u0101\u0001\u0000\u0000\u0000\"\u0107\u0001\u0000\u0000\u0000"+
		"$\u010e\u0001\u0000\u0000\u0000&\u0115\u0001\u0000\u0000\u0000(\u0119"+
		"\u0001\u0000\u0000\u0000*\u011b\u0001\u0000\u0000\u0000,\u0120\u0001\u0000"+
		"\u0000\u0000.\u0122\u0001\u0000\u0000\u00000\u012a\u0001\u0000\u0000\u0000"+
		"2\u0132\u0001\u0000\u0000\u00004\u013a\u0001\u0000\u0000\u00006\u0142"+
		"\u0001\u0000\u0000\u00008\u014a\u0001\u0000\u0000\u0000:\u0155\u0001\u0000"+
		"\u0000\u0000<\u0157\u0001\u0000\u0000\u0000>\u0161\u0001\u0000\u0000\u0000"+
		"@\u0163\u0001\u0000\u0000\u0000B\u0169\u0001\u0000\u0000\u0000D\u0171"+
		"\u0001\u0000\u0000\u0000F\u0174\u0001\u0000\u0000\u0000H\u0186\u0001\u0000"+
		"\u0000\u0000J\u0188\u0001\u0000\u0000\u0000L\u018b\u0001\u0000\u0000\u0000"+
		"N\u0198\u0001\u0000\u0000\u0000P\u01a5\u0001\u0000\u0000\u0000R\u01b2"+
		"\u0001\u0000\u0000\u0000TW\u0003\u0002\u0001\u0000UW\u0003\u0004\u0002"+
		"\u0000VT\u0001\u0000\u0000\u0000VU\u0001\u0000\u0000\u0000WZ\u0001\u0000"+
		"\u0000\u0000XV\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y[\u0001"+
		"\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000[\\\u0005\u0000\u0000\u0001"+
		"\\\u0001\u0001\u0000\u0000\u0000]^\u0005\u0001\u0000\u0000^a\u00057\u0000"+
		"\u0000_`\u0005\r\u0000\u0000`b\u00054\u0000\u0000a_\u0001\u0000\u0000"+
		"\u0000ab\u0001\u0000\u0000\u0000bd\u0001\u0000\u0000\u0000ce\u00050\u0000"+
		"\u0000dc\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000e\u0003\u0001"+
		"\u0000\u0000\u0000fr\u0003\u0006\u0003\u0000gr\u0003\b\u0004\u0000hr\u0003"+
		"\n\u0005\u0000ir\u0003\f\u0006\u0000jr\u0003\u0014\n\u0000kr\u0003\u0016"+
		"\u000b\u0000lr\u0003 \u0010\u0000mr\u0003\"\u0011\u0000nr\u0003$\u0012"+
		"\u0000or\u0003&\u0013\u0000pr\u00050\u0000\u0000qf\u0001\u0000\u0000\u0000"+
		"qg\u0001\u0000\u0000\u0000qh\u0001\u0000\u0000\u0000qi\u0001\u0000\u0000"+
		"\u0000qj\u0001\u0000\u0000\u0000qk\u0001\u0000\u0000\u0000ql\u0001\u0000"+
		"\u0000\u0000qm\u0001\u0000\u0000\u0000qn\u0001\u0000\u0000\u0000qo\u0001"+
		"\u0000\u0000\u0000qp\u0001\u0000\u0000\u0000r\u0005\u0001\u0000\u0000"+
		"\u0000sx\u0005,\u0000\u0000tw\u0003\u0004\u0002\u0000uw\u0003&\u0013\u0000"+
		"vt\u0001\u0000\u0000\u0000vu\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000"+
		"\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y{\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000{|\u0005-\u0000\u0000|\u0007\u0001"+
		"\u0000\u0000\u0000}\u007f\u0007\u0000\u0000\u0000~}\u0001\u0000\u0000"+
		"\u0000~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000"+
		"\u0080\u0081\u0005\u0004\u0000\u0000\u0081\u0084\u00054\u0000\u0000\u0082"+
		"\u0083\u00051\u0000\u0000\u0083\u0085\u0003R)\u0000\u0084\u0082\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0088\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0005#\u0000\u0000\u0087\u0089\u0003(\u0014"+
		"\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000"+
		"\u0000\u0089\u008b\u0001\u0000\u0000\u0000\u008a\u008c\u00050\u0000\u0000"+
		"\u008b\u008a\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000"+
		"\u008c\t\u0001\u0000\u0000\u0000\u008d\u008f\u0007\u0000\u0000\u0000\u008e"+
		"\u008d\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f"+
		"\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0005\u0003\u0000\u0000\u0091"+
		"\u0094\u00054\u0000\u0000\u0092\u0093\u00051\u0000\u0000\u0093\u0095\u0003"+
		"R)\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000"+
		"\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0005#\u0000\u0000"+
		"\u0097\u0099\u0003(\u0014\u0000\u0098\u009a\u00050\u0000\u0000\u0099\u0098"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u000b"+
		"\u0001\u0000\u0000\u0000\u009b\u009d\u0007\u0000\u0000\u0000\u009c\u009b"+
		"\u0001\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e"+
		"\u0001\u0000\u0000\u0000\u009e\u00a0\u0005\u0002\u0000\u0000\u009f\u00a1"+
		"\u00054\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001"+
		"\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2\u00a4\u0005"+
		"*\u0000\u0000\u00a3\u00a5\u0003\u0010\b\u0000\u00a4\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a6\u00a9\u0005+\u0000\u0000\u00a7\u00a8\u00051\u0000\u0000"+
		"\u00a8\u00aa\u0003R)\u0000\u00a9\u00a7\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0003\u0006\u0003\u0000\u00ac\r\u0001\u0000\u0000\u0000\u00ad\u00af\u0005"+
		"\u0002\u0000\u0000\u00ae\u00b0\u00054\u0000\u0000\u00af\u00ae\u0001\u0000"+
		"\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001\u0000"+
		"\u0000\u0000\u00b1\u00b3\u0005*\u0000\u0000\u00b2\u00b4\u0003\u0010\b"+
		"\u0000\u00b3\u00b2\u0001\u0000\u0000\u0000\u00b3\u00b4\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b8\u0005+\u0000\u0000"+
		"\u00b6\u00b7\u00051\u0000\u0000\u00b7\u00b9\u0003R)\u0000\u00b8\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0001\u0000\u0000\u0000\u00ba\u00bb\u0003\u0006\u0003\u0000\u00bb\u000f"+
		"\u0001\u0000\u0000\u0000\u00bc\u00c1\u0003\u0012\t\u0000\u00bd\u00be\u0005"+
		"2\u0000\u0000\u00be\u00c0\u0003\u0012\t\u0000\u00bf\u00bd\u0001\u0000"+
		"\u0000\u0000\u00c0\u00c3\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u0011\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c6\u0003R)\u0000"+
		"\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000"+
		"\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u00054\u0000\u0000\u00c8"+
		"\u0013\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005\u0007\u0000\u0000\u00ca"+
		"\u00cb\u0005*\u0000\u0000\u00cb\u00cc\u0003(\u0014\u0000\u00cc\u00cd\u0005"+
		"+\u0000\u0000\u00cd\u00d0\u0003\u0004\u0002\u0000\u00ce\u00cf\u0005\b"+
		"\u0000\u0000\u00cf\u00d1\u0003\u0004\u0002\u0000\u00d0\u00ce\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u0015\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0005\u0005\u0000\u0000\u00d3\u00d5\u0005*\u0000"+
		"\u0000\u00d4\u00d6\u0003\u0018\f\u0000\u00d5\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d8\u0001\u0000\u0000\u0000"+
		"\u00d7\u00d9\u00050\u0000\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000\u00d8"+
		"\u00d9\u0001\u0000\u0000\u0000\u00d9\u00db\u0001\u0000\u0000\u0000\u00da"+
		"\u00dc\u0003(\u0014\u0000\u00db\u00da\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc\u00de\u0001\u0000\u0000\u0000\u00dd\u00df"+
		"\u00050\u0000\u0000\u00de\u00dd\u0001\u0000\u0000\u0000\u00de\u00df\u0001"+
		"\u0000\u0000\u0000\u00df\u00e1\u0001\u0000\u0000\u0000\u00e0\u00e2\u0003"+
		"\u001c\u000e\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0005"+
		"+\u0000\u0000\u00e4\u00e5\u0003\u0004\u0002\u0000\u00e5\u0017\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e9\u0003\u001a\r\u0000\u00e7\u00e9\u0003\u001e\u000f"+
		"\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e9\u0019\u0001\u0000\u0000\u0000\u00ea\u00ec\u0007\u0000\u0000"+
		"\u0000\u00eb\u00ea\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000"+
		"\u0000\u00ec\u00ed\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u0004\u0000"+
		"\u0000\u00ee\u00f1\u00054\u0000\u0000\u00ef\u00f0\u00051\u0000\u0000\u00f0"+
		"\u00f2\u0003R)\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f5\u0001\u0000\u0000\u0000\u00f3\u00f4\u0005"+
		"#\u0000\u0000\u00f4\u00f6\u0003(\u0014\u0000\u00f5\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f6\u0001\u0000\u0000\u0000\u00f6\u001b\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\u0003\u001e\u000f\u0000\u00f8\u001d\u0001\u0000\u0000"+
		"\u0000\u00f9\u00fe\u0003(\u0014\u0000\u00fa\u00fb\u00052\u0000\u0000\u00fb"+
		"\u00fd\u0003(\u0014\u0000\u00fc\u00fa\u0001\u0000\u0000\u0000\u00fd\u0100"+
		"\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00fe\u00ff"+
		"\u0001\u0000\u0000\u0000\u00ff\u001f\u0001\u0000\u0000\u0000\u0100\u00fe"+
		"\u0001\u0000\u0000\u0000\u0101\u0102\u0005\u0006\u0000\u0000\u0102\u0103"+
		"\u0005*\u0000\u0000\u0103\u0104\u0003(\u0014\u0000\u0104\u0105\u0005+"+
		"\u0000\u0000\u0105\u0106\u0003\u0004\u0002\u0000\u0106!\u0001\u0000\u0000"+
		"\u0000\u0107\u0109\u0005\t\u0000\u0000\u0108\u010a\u0003(\u0014\u0000"+
		"\u0109\u0108\u0001\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000"+
		"\u010a\u010c\u0001\u0000\u0000\u0000\u010b\u010d\u00050\u0000\u0000\u010c"+
		"\u010b\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d"+
		"#\u0001\u0000\u0000\u0000\u010e\u0110\u0005\n\u0000\u0000\u010f\u0111"+
		"\u0003(\u0014\u0000\u0110\u010f\u0001\u0000\u0000\u0000\u0110\u0111\u0001"+
		"\u0000\u0000\u0000\u0111\u0113\u0001\u0000\u0000\u0000\u0112\u0114\u0005"+
		"0\u0000\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000"+
		"\u0000\u0000\u0114%\u0001\u0000\u0000\u0000\u0115\u0117\u0003(\u0014\u0000"+
		"\u0116\u0118\u00050\u0000\u0000\u0117\u0116\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0001\u0000\u0000\u0000\u0118\'\u0001\u0000\u0000\u0000\u0119\u011a"+
		"\u0003*\u0015\u0000\u011a)\u0001\u0000\u0000\u0000\u011b\u011e\u0003,"+
		"\u0016\u0000\u011c\u011d\u0007\u0001\u0000\u0000\u011d\u011f\u0003*\u0015"+
		"\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000"+
		"\u0000\u011f+\u0001\u0000\u0000\u0000\u0120\u0121\u0003.\u0017\u0000\u0121"+
		"-\u0001\u0000\u0000\u0000\u0122\u0127\u00030\u0018\u0000\u0123\u0124\u0005"+
		"(\u0000\u0000\u0124\u0126\u00030\u0018\u0000\u0125\u0123\u0001\u0000\u0000"+
		"\u0000\u0126\u0129\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000"+
		"\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128/\u0001\u0000\u0000\u0000"+
		"\u0129\u0127\u0001\u0000\u0000\u0000\u012a\u012f\u00032\u0019\u0000\u012b"+
		"\u012c\u0005\'\u0000\u0000\u012c\u012e\u00032\u0019\u0000\u012d\u012b"+
		"\u0001\u0000\u0000\u0000\u012e\u0131\u0001\u0000\u0000\u0000\u012f\u012d"+
		"\u0001\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u01301\u0001"+
		"\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0132\u0137\u0003"+
		"4\u001a\u0000\u0133\u0134\u0007\u0002\u0000\u0000\u0134\u0136\u00034\u001a"+
		"\u0000\u0135\u0133\u0001\u0000\u0000\u0000\u0136\u0139\u0001\u0000\u0000"+
		"\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0137\u0138\u0001\u0000\u0000"+
		"\u0000\u01383\u0001\u0000\u0000\u0000\u0139\u0137\u0001\u0000\u0000\u0000"+
		"\u013a\u013f\u00036\u001b\u0000\u013b\u013c\u0007\u0003\u0000\u0000\u013c"+
		"\u013e\u00036\u001b\u0000\u013d\u013b\u0001\u0000\u0000\u0000\u013e\u0141"+
		"\u0001\u0000\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u013f\u0140"+
		"\u0001\u0000\u0000\u0000\u01405\u0001\u0000\u0000\u0000\u0141\u013f\u0001"+
		"\u0000\u0000\u0000\u0142\u0147\u00038\u001c\u0000\u0143\u0144\u0007\u0004"+
		"\u0000\u0000\u0144\u0146\u00038\u001c\u0000\u0145\u0143\u0001\u0000\u0000"+
		"\u0000\u0146\u0149\u0001\u0000\u0000\u0000\u0147\u0145\u0001\u0000\u0000"+
		"\u0000\u0147\u0148\u0001\u0000\u0000\u0000\u01487\u0001\u0000\u0000\u0000"+
		"\u0149\u0147\u0001\u0000\u0000\u0000\u014a\u014f\u0003:\u001d\u0000\u014b"+
		"\u014c\u0007\u0005\u0000\u0000\u014c\u014e\u0003:\u001d\u0000\u014d\u014b"+
		"\u0001\u0000\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d"+
		"\u0001\u0000\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u01509\u0001"+
		"\u0000\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152\u0153\u0007"+
		"\u0006\u0000\u0000\u0153\u0156\u0003:\u001d\u0000\u0154\u0156\u0003<\u001e"+
		"\u0000\u0155\u0152\u0001\u0000\u0000\u0000\u0155\u0154\u0001\u0000\u0000"+
		"\u0000\u0156;\u0001\u0000\u0000\u0000\u0157\u015b\u0003H$\u0000\u0158"+
		"\u015a\u0003>\u001f\u0000\u0159\u0158\u0001\u0000\u0000\u0000\u015a\u015d"+
		"\u0001\u0000\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015b\u015c"+
		"\u0001\u0000\u0000\u0000\u015c=\u0001\u0000\u0000\u0000\u015d\u015b\u0001"+
		"\u0000\u0000\u0000\u015e\u0162\u0003@ \u0000\u015f\u0162\u0003D\"\u0000"+
		"\u0160\u0162\u0003F#\u0000\u0161\u015e\u0001\u0000\u0000\u0000\u0161\u015f"+
		"\u0001\u0000\u0000\u0000\u0161\u0160\u0001\u0000\u0000\u0000\u0162?\u0001"+
		"\u0000\u0000\u0000\u0163\u0165\u0005*\u0000\u0000\u0164\u0166\u0003B!"+
		"\u0000\u0165\u0164\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000"+
		"\u0000\u0166\u0167\u0001\u0000\u0000\u0000\u0167\u0168\u0005+\u0000\u0000"+
		"\u0168A\u0001\u0000\u0000\u0000\u0169\u016e\u0003(\u0014\u0000\u016a\u016b"+
		"\u00052\u0000\u0000\u016b\u016d\u0003(\u0014\u0000\u016c\u016a\u0001\u0000"+
		"\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000"+
		"\u0000\u0000\u016e\u016f\u0001\u0000\u0000\u0000\u016fC\u0001\u0000\u0000"+
		"\u0000\u0170\u016e\u0001\u0000\u0000\u0000\u0171\u0172\u00053\u0000\u0000"+
		"\u0172\u0173\u00054\u0000\u0000\u0173E\u0001\u0000\u0000\u0000\u0174\u0175"+
		"\u0005.\u0000\u0000\u0175\u0176\u0003(\u0014\u0000\u0176\u0177\u0005/"+
		"\u0000\u0000\u0177G\u0001\u0000\u0000\u0000\u0178\u0187\u00056\u0000\u0000"+
		"\u0179\u0187\u00055\u0000\u0000\u017a\u0187\u00057\u0000\u0000\u017b\u0187"+
		"\u0005\u0016\u0000\u0000\u017c\u0187\u0005\u0017\u0000\u0000\u017d\u0187"+
		"\u00054\u0000\u0000\u017e\u0187\u0003L&\u0000\u017f\u0187\u0003N\'\u0000"+
		"\u0180\u0187\u0003\u000e\u0007\u0000\u0181\u0187\u0003J%\u0000\u0182\u0183"+
		"\u0005*\u0000\u0000\u0183\u0184\u0003(\u0014\u0000\u0184\u0185\u0005+"+
		"\u0000\u0000\u0185\u0187\u0001\u0000\u0000\u0000\u0186\u0178\u0001\u0000"+
		"\u0000\u0000\u0186\u0179\u0001\u0000\u0000\u0000\u0186\u017a\u0001\u0000"+
		"\u0000\u0000\u0186\u017b\u0001\u0000\u0000\u0000\u0186\u017c\u0001\u0000"+
		"\u0000\u0000\u0186\u017d\u0001\u0000\u0000\u0000\u0186\u017e\u0001\u0000"+
		"\u0000\u0000\u0186\u017f\u0001\u0000\u0000\u0000\u0186\u0180\u0001\u0000"+
		"\u0000\u0000\u0186\u0181\u0001\u0000\u0000\u0000\u0186\u0182\u0001\u0000"+
		"\u0000\u0000\u0187I\u0001\u0000\u0000\u0000\u0188\u0189\u0005\u000e\u0000"+
		"\u0000\u0189\u018a\u0003H$\u0000\u018aK\u0001\u0000\u0000\u0000\u018b"+
		"\u0194\u0005.\u0000\u0000\u018c\u0191\u0003(\u0014\u0000\u018d\u018e\u0005"+
		"2\u0000\u0000\u018e\u0190\u0003(\u0014\u0000\u018f\u018d\u0001\u0000\u0000"+
		"\u0000\u0190\u0193\u0001\u0000\u0000\u0000\u0191\u018f\u0001\u0000\u0000"+
		"\u0000\u0191\u0192\u0001\u0000\u0000\u0000\u0192\u0195\u0001\u0000\u0000"+
		"\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0194\u018c\u0001\u0000\u0000"+
		"\u0000\u0194\u0195\u0001\u0000\u0000\u0000\u0195\u0196\u0001\u0000\u0000"+
		"\u0000\u0196\u0197\u0005/\u0000\u0000\u0197M\u0001\u0000\u0000\u0000\u0198"+
		"\u01a1\u0005,\u0000\u0000\u0199\u019e\u0003P(\u0000\u019a\u019b\u0005"+
		"2\u0000\u0000\u019b\u019d\u0003P(\u0000\u019c\u019a\u0001\u0000\u0000"+
		"\u0000\u019d\u01a0\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000\u0000"+
		"\u0000\u019e\u019f\u0001\u0000\u0000\u0000\u019f\u01a2\u0001\u0000\u0000"+
		"\u0000\u01a0\u019e\u0001\u0000\u0000\u0000\u01a1\u0199\u0001\u0000\u0000"+
		"\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2\u01a3\u0001\u0000\u0000"+
		"\u0000\u01a3\u01a4\u0005-\u0000\u0000\u01a4O\u0001\u0000\u0000\u0000\u01a5"+
		"\u01a6\u0007\u0007\u0000\u0000\u01a6\u01a7\u00051\u0000\u0000\u01a7\u01a8"+
		"\u0003(\u0014\u0000\u01a8Q\u0001\u0000\u0000\u0000\u01a9\u01aa\u0006)"+
		"\uffff\uffff\u0000\u01aa\u01b3\u0005\u0011\u0000\u0000\u01ab\u01b3\u0005"+
		"\u0012\u0000\u0000\u01ac\u01b3\u0005\u0013\u0000\u0000\u01ad\u01b3\u0005"+
		"\u0014\u0000\u0000\u01ae\u01b3\u0005\u0015\u0000\u0000\u01af\u01b0\u0005"+
		"\u0010\u0000\u0000\u01b0\u01b3\u00054\u0000\u0000\u01b1\u01b3\u00054\u0000"+
		"\u0000\u01b2\u01a9\u0001\u0000\u0000\u0000\u01b2\u01ab\u0001\u0000\u0000"+
		"\u0000\u01b2\u01ac\u0001\u0000\u0000\u0000\u01b2\u01ad\u0001\u0000\u0000"+
		"\u0000\u01b2\u01ae\u0001\u0000\u0000\u0000\u01b2\u01af\u0001\u0000\u0000"+
		"\u0000\u01b2\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b9\u0001\u0000\u0000"+
		"\u0000\u01b4\u01b5\n\u0001\u0000\u0000\u01b5\u01b6\u0005.\u0000\u0000"+
		"\u01b6\u01b8\u0005/\u0000\u0000\u01b7\u01b4\u0001\u0000\u0000\u0000\u01b8"+
		"\u01bb\u0001\u0000\u0000\u0000\u01b9\u01b7\u0001\u0000\u0000\u0000\u01b9"+
		"\u01ba\u0001\u0000\u0000\u0000\u01baS\u0001\u0000\u0000\u0000\u01bb\u01b9"+
		"\u0001\u0000\u0000\u0000:VXadqvx~\u0084\u0088\u008b\u008e\u0094\u0099"+
		"\u009c\u00a0\u00a4\u00a9\u00af\u00b3\u00b8\u00c1\u00c5\u00d0\u00d5\u00d8"+
		"\u00db\u00de\u00e1\u00e8\u00eb\u00f1\u00f5\u00fe\u0109\u010c\u0110\u0113"+
		"\u0117\u011e\u0127\u012f\u0137\u013f\u0147\u014f\u0155\u015b\u0161\u0165"+
		"\u016e\u0186\u0191\u0194\u019e\u01a1\u01b2\u01b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}