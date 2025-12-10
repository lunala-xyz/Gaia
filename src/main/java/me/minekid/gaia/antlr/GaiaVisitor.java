// Generated from Gaia.g4 by ANTLR 4.13.2
package me.minekid.gaia.antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced by {@link
 * GaiaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for operations with no return
 *     type.
 */
public interface GaiaVisitor<T> extends ParseTreeVisitor<T> {
  /**
   * Visit a parse tree produced by {@link GaiaParser#prog}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProg(GaiaParser.ProgContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#importStmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitImportStmt(GaiaParser.ImportStmtContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#statement}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitStatement(GaiaParser.StatementContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#block}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitBlock(GaiaParser.BlockContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#variableDecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableDecl(GaiaParser.VariableDeclContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#constDecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitConstDecl(GaiaParser.ConstDeclContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#funcDecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFuncDecl(GaiaParser.FuncDeclContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#funcExpr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFuncExpr(GaiaParser.FuncExprContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#paramList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitParamList(GaiaParser.ParamListContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#param}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitParam(GaiaParser.ParamContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#classDecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitClassDecl(GaiaParser.ClassDeclContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#classMember}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitClassMember(GaiaParser.ClassMemberContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#interfaceDecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitInterfaceDecl(GaiaParser.InterfaceDeclContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#interfaceMember}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitInterfaceMember(GaiaParser.InterfaceMemberContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#callableDecl}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCallableDecl(GaiaParser.CallableDeclContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#ifStmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIfStmt(GaiaParser.IfStmtContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#forStmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitForStmt(GaiaParser.ForStmtContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#forInit}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitForInit(GaiaParser.ForInitContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#variableDeclNoSemi}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitVariableDeclNoSemi(GaiaParser.VariableDeclNoSemiContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#forUpdate}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitForUpdate(GaiaParser.ForUpdateContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#exprList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExprList(GaiaParser.ExprListContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#whileStmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitWhileStmt(GaiaParser.WhileStmtContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#returnStmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitReturnStmt(GaiaParser.ReturnStmtContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#failStmt}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitFailStmt(GaiaParser.FailStmtContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#exprStatement}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExprStatement(GaiaParser.ExprStatementContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#expression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitExpression(GaiaParser.ExpressionContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#assignment}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAssignment(GaiaParser.AssignmentContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#conditional}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitConditional(GaiaParser.ConditionalContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#logicalOr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLogicalOr(GaiaParser.LogicalOrContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#logicalAnd}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitLogicalAnd(GaiaParser.LogicalAndContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#equality}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitEquality(GaiaParser.EqualityContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#relational}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitRelational(GaiaParser.RelationalContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#additive}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitAdditive(GaiaParser.AdditiveContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#multiplicative}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitMultiplicative(GaiaParser.MultiplicativeContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#unary}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitUnary(GaiaParser.UnaryContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#postfix}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPostfix(GaiaParser.PostfixContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#postfixOp}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPostfixOp(GaiaParser.PostfixOpContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#call}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitCall(GaiaParser.CallContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#argumentList}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitArgumentList(GaiaParser.ArgumentListContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#memberAccess}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitMemberAccess(GaiaParser.MemberAccessContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#indexAccess}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitIndexAccess(GaiaParser.IndexAccessContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#primary}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitPrimary(GaiaParser.PrimaryContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#newExpression}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitNewExpression(GaiaParser.NewExpressionContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#arrayLiteral}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitArrayLiteral(GaiaParser.ArrayLiteralContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#objectLiteral}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitObjectLiteral(GaiaParser.ObjectLiteralContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#property}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitProperty(GaiaParser.PropertyContext ctx);
  /**
   * Visit a parse tree produced by {@link GaiaParser#typeExpr}.
   *
   * @param ctx the parse tree
   * @return the visitor result
   */
  T visitTypeExpr(GaiaParser.TypeExprContext ctx);
}
