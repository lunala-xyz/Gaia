package me.minekid.gaia.ast;

import me.minekid.gaia.ast.expr.*;
import me.minekid.gaia.ast.stmt.*;
import me.minekid.gaia.ast.type.*;

public interface AstVisitor<T> {
    T visitProgram(Program program);

    T visitImportStatement(ImportStatement stmt);

    T visitBlockStatement(BlockStatement stmt);

    T visitVariableDeclaration(VariableDeclaration stmt);

    T visitConstDeclaration(ConstDeclaration stmt);

    T visitFunctionDeclaration(FunctionDeclaration stmt);

    T visitClassDeclaration(ClassDeclaration stmt);

    T visitInterfaceDeclaration(InterfaceDeclaration stmt);

    T visitCallableDeclaration(CallableDeclaration stmt);

    T visitIfStatement(IfStatement stmt);

    T visitForStatement(ForStatement stmt);

    T visitWhileStatement(WhileStatement stmt);

    T visitReturnStatement(ReturnStatement stmt);

    T visitFailStatement(FailStatement stmt);

    T visitExpressionStatement(ExpressionStatement stmt);

    T visitIntLiteral(IntLiteral expr);

    T visitFloatLiteral(FloatLiteral expr);

    T visitStringLiteral(StringLiteral expr);

    T visitBooleanLiteral(BooleanLiteral expr);

    T visitIdentifier(Identifier expr);

    T visitArrayLiteral(ArrayLiteral expr);

    T visitObjectLiteral(ObjectLiteral expr);

    T visitFunctionExpression(FunctionExpression expr);

    T visitNewExpression(NewExpression expr);

    T visitBinaryExpression(BinaryExpression expr);

    T visitUnaryExpression(UnaryExpression expr);

    T visitAssignmentExpression(AssignmentExpression expr);

    T visitCallExpression(CallExpression expr);

    T visitMemberExpression(MemberExpression expr);

    T visitIndexExpression(IndexExpression expr);

    T visitPrimitiveType(PrimitiveType type);

    T visitArrayType(ArrayType type);

    T visitIdentifierType(IdentifierType type);

    T visitOneOfType(OneOfType type);
}
