// Generated from Gaia.g4 by ANTLR 4.13.2
package me.minekid.gaia.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/** This interface defines a complete listener for a parse tree produced by {@link GaiaParser}. */
public interface GaiaListener extends ParseTreeListener {
  /**
   * Enter a parse tree produced by {@link GaiaParser#prog}.
   *
   * @param ctx the parse tree
   */
  void enterProg(GaiaParser.ProgContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#prog}.
   *
   * @param ctx the parse tree
   */
  void exitProg(GaiaParser.ProgContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#importStmt}.
   *
   * @param ctx the parse tree
   */
  void enterImportStmt(GaiaParser.ImportStmtContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#importStmt}.
   *
   * @param ctx the parse tree
   */
  void exitImportStmt(GaiaParser.ImportStmtContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#statement}.
   *
   * @param ctx the parse tree
   */
  void enterStatement(GaiaParser.StatementContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#statement}.
   *
   * @param ctx the parse tree
   */
  void exitStatement(GaiaParser.StatementContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#block}.
   *
   * @param ctx the parse tree
   */
  void enterBlock(GaiaParser.BlockContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#block}.
   *
   * @param ctx the parse tree
   */
  void exitBlock(GaiaParser.BlockContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#variableDecl}.
   *
   * @param ctx the parse tree
   */
  void enterVariableDecl(GaiaParser.VariableDeclContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#variableDecl}.
   *
   * @param ctx the parse tree
   */
  void exitVariableDecl(GaiaParser.VariableDeclContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#constDecl}.
   *
   * @param ctx the parse tree
   */
  void enterConstDecl(GaiaParser.ConstDeclContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#constDecl}.
   *
   * @param ctx the parse tree
   */
  void exitConstDecl(GaiaParser.ConstDeclContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#funcDecl}.
   *
   * @param ctx the parse tree
   */
  void enterFuncDecl(GaiaParser.FuncDeclContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#funcDecl}.
   *
   * @param ctx the parse tree
   */
  void exitFuncDecl(GaiaParser.FuncDeclContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#funcExpr}.
   *
   * @param ctx the parse tree
   */
  void enterFuncExpr(GaiaParser.FuncExprContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#funcExpr}.
   *
   * @param ctx the parse tree
   */
  void exitFuncExpr(GaiaParser.FuncExprContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#paramList}.
   *
   * @param ctx the parse tree
   */
  void enterParamList(GaiaParser.ParamListContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#paramList}.
   *
   * @param ctx the parse tree
   */
  void exitParamList(GaiaParser.ParamListContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#param}.
   *
   * @param ctx the parse tree
   */
  void enterParam(GaiaParser.ParamContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#param}.
   *
   * @param ctx the parse tree
   */
  void exitParam(GaiaParser.ParamContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#classDecl}.
   *
   * @param ctx the parse tree
   */
  void enterClassDecl(GaiaParser.ClassDeclContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#classDecl}.
   *
   * @param ctx the parse tree
   */
  void exitClassDecl(GaiaParser.ClassDeclContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#classMember}.
   *
   * @param ctx the parse tree
   */
  void enterClassMember(GaiaParser.ClassMemberContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#classMember}.
   *
   * @param ctx the parse tree
   */
  void exitClassMember(GaiaParser.ClassMemberContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#interfaceDecl}.
   *
   * @param ctx the parse tree
   */
  void enterInterfaceDecl(GaiaParser.InterfaceDeclContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#interfaceDecl}.
   *
   * @param ctx the parse tree
   */
  void exitInterfaceDecl(GaiaParser.InterfaceDeclContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#interfaceMember}.
   *
   * @param ctx the parse tree
   */
  void enterInterfaceMember(GaiaParser.InterfaceMemberContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#interfaceMember}.
   *
   * @param ctx the parse tree
   */
  void exitInterfaceMember(GaiaParser.InterfaceMemberContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#callableDecl}.
   *
   * @param ctx the parse tree
   */
  void enterCallableDecl(GaiaParser.CallableDeclContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#callableDecl}.
   *
   * @param ctx the parse tree
   */
  void exitCallableDecl(GaiaParser.CallableDeclContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#ifStmt}.
   *
   * @param ctx the parse tree
   */
  void enterIfStmt(GaiaParser.IfStmtContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#ifStmt}.
   *
   * @param ctx the parse tree
   */
  void exitIfStmt(GaiaParser.IfStmtContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#forStmt}.
   *
   * @param ctx the parse tree
   */
  void enterForStmt(GaiaParser.ForStmtContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#forStmt}.
   *
   * @param ctx the parse tree
   */
  void exitForStmt(GaiaParser.ForStmtContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#forInit}.
   *
   * @param ctx the parse tree
   */
  void enterForInit(GaiaParser.ForInitContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#forInit}.
   *
   * @param ctx the parse tree
   */
  void exitForInit(GaiaParser.ForInitContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#variableDeclNoSemi}.
   *
   * @param ctx the parse tree
   */
  void enterVariableDeclNoSemi(GaiaParser.VariableDeclNoSemiContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#variableDeclNoSemi}.
   *
   * @param ctx the parse tree
   */
  void exitVariableDeclNoSemi(GaiaParser.VariableDeclNoSemiContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#forUpdate}.
   *
   * @param ctx the parse tree
   */
  void enterForUpdate(GaiaParser.ForUpdateContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#forUpdate}.
   *
   * @param ctx the parse tree
   */
  void exitForUpdate(GaiaParser.ForUpdateContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#exprList}.
   *
   * @param ctx the parse tree
   */
  void enterExprList(GaiaParser.ExprListContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#exprList}.
   *
   * @param ctx the parse tree
   */
  void exitExprList(GaiaParser.ExprListContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#whileStmt}.
   *
   * @param ctx the parse tree
   */
  void enterWhileStmt(GaiaParser.WhileStmtContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#whileStmt}.
   *
   * @param ctx the parse tree
   */
  void exitWhileStmt(GaiaParser.WhileStmtContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#returnStmt}.
   *
   * @param ctx the parse tree
   */
  void enterReturnStmt(GaiaParser.ReturnStmtContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#returnStmt}.
   *
   * @param ctx the parse tree
   */
  void exitReturnStmt(GaiaParser.ReturnStmtContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#failStmt}.
   *
   * @param ctx the parse tree
   */
  void enterFailStmt(GaiaParser.FailStmtContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#failStmt}.
   *
   * @param ctx the parse tree
   */
  void exitFailStmt(GaiaParser.FailStmtContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#exprStatement}.
   *
   * @param ctx the parse tree
   */
  void enterExprStatement(GaiaParser.ExprStatementContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#exprStatement}.
   *
   * @param ctx the parse tree
   */
  void exitExprStatement(GaiaParser.ExprStatementContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#expression}.
   *
   * @param ctx the parse tree
   */
  void enterExpression(GaiaParser.ExpressionContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#expression}.
   *
   * @param ctx the parse tree
   */
  void exitExpression(GaiaParser.ExpressionContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#assignment}.
   *
   * @param ctx the parse tree
   */
  void enterAssignment(GaiaParser.AssignmentContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#assignment}.
   *
   * @param ctx the parse tree
   */
  void exitAssignment(GaiaParser.AssignmentContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#conditional}.
   *
   * @param ctx the parse tree
   */
  void enterConditional(GaiaParser.ConditionalContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#conditional}.
   *
   * @param ctx the parse tree
   */
  void exitConditional(GaiaParser.ConditionalContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#logicalOr}.
   *
   * @param ctx the parse tree
   */
  void enterLogicalOr(GaiaParser.LogicalOrContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#logicalOr}.
   *
   * @param ctx the parse tree
   */
  void exitLogicalOr(GaiaParser.LogicalOrContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#logicalAnd}.
   *
   * @param ctx the parse tree
   */
  void enterLogicalAnd(GaiaParser.LogicalAndContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#logicalAnd}.
   *
   * @param ctx the parse tree
   */
  void exitLogicalAnd(GaiaParser.LogicalAndContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#equality}.
   *
   * @param ctx the parse tree
   */
  void enterEquality(GaiaParser.EqualityContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#equality}.
   *
   * @param ctx the parse tree
   */
  void exitEquality(GaiaParser.EqualityContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#relational}.
   *
   * @param ctx the parse tree
   */
  void enterRelational(GaiaParser.RelationalContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#relational}.
   *
   * @param ctx the parse tree
   */
  void exitRelational(GaiaParser.RelationalContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#additive}.
   *
   * @param ctx the parse tree
   */
  void enterAdditive(GaiaParser.AdditiveContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#additive}.
   *
   * @param ctx the parse tree
   */
  void exitAdditive(GaiaParser.AdditiveContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#multiplicative}.
   *
   * @param ctx the parse tree
   */
  void enterMultiplicative(GaiaParser.MultiplicativeContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#multiplicative}.
   *
   * @param ctx the parse tree
   */
  void exitMultiplicative(GaiaParser.MultiplicativeContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#unary}.
   *
   * @param ctx the parse tree
   */
  void enterUnary(GaiaParser.UnaryContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#unary}.
   *
   * @param ctx the parse tree
   */
  void exitUnary(GaiaParser.UnaryContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#postfix}.
   *
   * @param ctx the parse tree
   */
  void enterPostfix(GaiaParser.PostfixContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#postfix}.
   *
   * @param ctx the parse tree
   */
  void exitPostfix(GaiaParser.PostfixContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#postfixOp}.
   *
   * @param ctx the parse tree
   */
  void enterPostfixOp(GaiaParser.PostfixOpContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#postfixOp}.
   *
   * @param ctx the parse tree
   */
  void exitPostfixOp(GaiaParser.PostfixOpContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#call}.
   *
   * @param ctx the parse tree
   */
  void enterCall(GaiaParser.CallContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#call}.
   *
   * @param ctx the parse tree
   */
  void exitCall(GaiaParser.CallContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#argumentList}.
   *
   * @param ctx the parse tree
   */
  void enterArgumentList(GaiaParser.ArgumentListContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#argumentList}.
   *
   * @param ctx the parse tree
   */
  void exitArgumentList(GaiaParser.ArgumentListContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#memberAccess}.
   *
   * @param ctx the parse tree
   */
  void enterMemberAccess(GaiaParser.MemberAccessContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#memberAccess}.
   *
   * @param ctx the parse tree
   */
  void exitMemberAccess(GaiaParser.MemberAccessContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#indexAccess}.
   *
   * @param ctx the parse tree
   */
  void enterIndexAccess(GaiaParser.IndexAccessContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#indexAccess}.
   *
   * @param ctx the parse tree
   */
  void exitIndexAccess(GaiaParser.IndexAccessContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#primary}.
   *
   * @param ctx the parse tree
   */
  void enterPrimary(GaiaParser.PrimaryContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#primary}.
   *
   * @param ctx the parse tree
   */
  void exitPrimary(GaiaParser.PrimaryContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#newExpression}.
   *
   * @param ctx the parse tree
   */
  void enterNewExpression(GaiaParser.NewExpressionContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#newExpression}.
   *
   * @param ctx the parse tree
   */
  void exitNewExpression(GaiaParser.NewExpressionContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#arrayLiteral}.
   *
   * @param ctx the parse tree
   */
  void enterArrayLiteral(GaiaParser.ArrayLiteralContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#arrayLiteral}.
   *
   * @param ctx the parse tree
   */
  void exitArrayLiteral(GaiaParser.ArrayLiteralContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#objectLiteral}.
   *
   * @param ctx the parse tree
   */
  void enterObjectLiteral(GaiaParser.ObjectLiteralContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#objectLiteral}.
   *
   * @param ctx the parse tree
   */
  void exitObjectLiteral(GaiaParser.ObjectLiteralContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#property}.
   *
   * @param ctx the parse tree
   */
  void enterProperty(GaiaParser.PropertyContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#property}.
   *
   * @param ctx the parse tree
   */
  void exitProperty(GaiaParser.PropertyContext ctx);
  /**
   * Enter a parse tree produced by {@link GaiaParser#typeExpr}.
   *
   * @param ctx the parse tree
   */
  void enterTypeExpr(GaiaParser.TypeExprContext ctx);
  /**
   * Exit a parse tree produced by {@link GaiaParser#typeExpr}.
   *
   * @param ctx the parse tree
   */
  void exitTypeExpr(GaiaParser.TypeExprContext ctx);
}
