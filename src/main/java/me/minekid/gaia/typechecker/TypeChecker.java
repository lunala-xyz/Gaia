package me.minekid.gaia.typechecker;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.stream.Collectors;

import me.minekid.gaia.ast.*;
import me.minekid.gaia.ast.expr.*;
import me.minekid.gaia.ast.stmt.*;
import me.minekid.gaia.ast.type.*;

import static java.util.Objects.isNull;

public class TypeChecker implements AstVisitor<GaiaType> {
    private final List<TypeError> errors = new ArrayList<>();
    private Scope currentScope;
    private final Map<String, ClassGaiaType> classTypes = new HashMap<>();
    private final Map<String, OneOfGaiaType> oneOfTypes = new HashMap<>();
    private final Map<String, FunctionGaiaType> callableTypes = new HashMap<>();

    public TypeChecker() {
        // Start with global scope
        currentScope = new Scope(null, Scope.ScopeKind.GLOBAL);
    }

    /**
     * Type check a program.
     *
     * @param program the program to check
     * @return true if no type errors were found
     */
    public boolean check(Program program) {
        errors.clear();
        classTypes.clear();
        oneOfTypes.clear();
        callableTypes.clear();
        currentScope = new Scope(null, Scope.ScopeKind.GLOBAL);

        collectDeclarations(program);

        program.accept(this);
        return errors.isEmpty();
    }


    public List<TypeError> getErrors() {
        return new ArrayList<>(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    private void error(String message, AstNode node) {
        errors.add(new TypeError(message, node != null ? node.getLocation() : null));
    }

    private void collectDeclarations(Program program) {
        for (Statement stmt : program.getStatements()) {
            if (stmt instanceof ClassDeclaration cd) {
                classTypes.put(cd.getName(), new ClassGaiaType(cd.getName()));
            } else if (stmt instanceof CallableDeclaration call) {
                callableTypes.put(call.getName(), buildFunctionType(call.getParameters(), call.getReturnType()));
            }
        }

        for (Statement stmt : program.getStatements()) {
            if (!(stmt instanceof ClassDeclaration cd)) continue;
            ClassGaiaType classType = classTypes.get(cd.getName());
            if (classType == null) continue;

            for (ClassDeclaration.ClassMember member : cd.getMembers()) {
                if (member instanceof VariableDeclaration v) {
                    classType.addField(v.getName(), resolveOrInfer(v.getType(), v.getInitializer()));
                } else if (member instanceof ConstDeclaration c) {
                    classType.addField(c.getName(), resolveOrInfer(c.getType(), c.getInitializer()));
                } else if (member instanceof FunctionDeclaration f) {
                    classType.addMethod(f.getName(), buildFunctionType(f.getParameters(), f.getReturnType()));
                }
            }
        }
    }

    private FunctionGaiaType buildFunctionType(List<Parameter> params, TypeExpression returnTypeExpr) {
        List<GaiaType> paramTypes = params.stream()
                .map(p -> resolveType(p.type()))
                .collect(Collectors.toList());

        GaiaType returnType = returnTypeExpr != null ? resolveType(returnTypeExpr) : PrimitiveGaiaType.VOID;

        return new FunctionGaiaType(paramTypes, returnType);
    }

    private GaiaType resolveOrInfer(TypeExpression declared, Expression initializer) {
        return !isNull(declared) ? resolveType(declared) : inferType(initializer);
    }

    public GaiaType resolveType(TypeExpression typeExpr) {
        if (isNull(typeExpr)) return PrimitiveGaiaType.VOID;
        return typeExpr.accept(this);
    }

    private GaiaType inferType(Expression expr) {
        if (isNull(expr)) return PrimitiveGaiaType.VOID;
        return expr.accept(this);
    }

    private void pushScope(Scope.ScopeKind kind) {
        currentScope = new Scope(currentScope, kind);
    }

    private void popScope() {
        if (isNull(currentScope.getParent())) return;
        currentScope = currentScope.getParent();
    }

    @Override
    public GaiaType visitProgram(Program program) {
        for (ImportStatement imp : program.getImports()) {
            imp.accept(this);
        }

        for (Statement stmt : program.getStatements()) {
            stmt.accept(this);
        }

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitImportStatement(ImportStatement stmt) {
        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitBlockStatement(BlockStatement stmt) {
        pushScope(Scope.ScopeKind.BLOCK);

        for (Statement s : stmt.getStatements()) {
            s.accept(this);
        }

        popScope();

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitVariableDeclaration(VariableDeclaration stmt) {
        GaiaType declaredType = stmt.getType() != null ? resolveType(stmt.getType()) : null;
        GaiaType initType = stmt.getInitializer() != null ? inferType(stmt.getInitializer()) : null;

        GaiaType varType;
        if (declaredType != null && initType != null) {
            if (!declaredType.isAssignableFrom(initType)) {
                error("Cannot assign " + initType + " to variable of type " + declaredType, stmt);
            }
            varType = declaredType;
        } else if (declaredType != null) {
            varType = declaredType;
        } else if (initType != null) {
            varType = initType;
        } else {
            error("Variable declaration requires either a type annotation or an initializer", stmt);
            varType = PrimitiveGaiaType.VOID;
        }

        Symbol symbol = new Symbol(stmt.getName(), varType, Symbol.SymbolKind.VARIABLE, true);
        if (!currentScope.define(symbol)) {
            error("Variable '" + stmt.getName() + "' is already defined in this scope", stmt);
        }

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitConstDeclaration(ConstDeclaration stmt) {
        GaiaType declaredType = stmt.getType() != null ? resolveType(stmt.getType()) : null;
        GaiaType initType = inferType(stmt.getInitializer());

        GaiaType constType = initType;

        if (declaredType != null) {
            if (!declaredType.isAssignableFrom(initType)) {
                error("Cannot assign " + initType + " to constant of type " + declaredType, stmt);
            }
            constType = declaredType;
        }

        Symbol symbol = new Symbol(stmt.getName(), constType, Symbol.SymbolKind.CONSTANT, false);

        if (!currentScope.define(symbol)) {
            error("Constant '" + stmt.getName() + "' is already defined in this scope", stmt);
        }

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitFunctionDeclaration(FunctionDeclaration stmt) {
        List<GaiaType> paramTypes = new ArrayList<>();

        for (Parameter param : stmt.getParameters()) {
            paramTypes.add(resolveType(param.type()));
        }

        GaiaType returnType = stmt.getReturnType() != null ? resolveType(stmt.getReturnType()) : PrimitiveGaiaType.VOID;
        FunctionGaiaType funcType = new FunctionGaiaType(paramTypes, returnType);

        if (stmt.getName() != null) {
            Symbol symbol = new Symbol(stmt.getName(), funcType, Symbol.SymbolKind.FUNCTION, false);
            if (!currentScope.define(symbol)) {
                error("Function '" + stmt.getName() + "' is already defined in this scope", stmt);
            }
        }

        pushScope(Scope.ScopeKind.FUNCTION);
        currentScope.define(new Symbol("$return", returnType, Symbol.SymbolKind.VARIABLE, false));

        for (int i = 0; i < stmt.getParameters().size(); i++) {
            Parameter param = stmt.getParameters().get(i);
            GaiaType paramType = paramTypes.get(i);
            Symbol paramSymbol =
                    new Symbol(param.name(), paramType, Symbol.SymbolKind.PARAMETER, false);
            currentScope.define(paramSymbol);
        }

        stmt.getBody().accept(this);
        popScope();

        return funcType;
    }

    @Override
    public GaiaType visitClassDeclaration(ClassDeclaration stmt) {
        ClassGaiaType classType = classTypes.get(stmt.getName());

        if (classType == null) {
            error("Unknown class '" + stmt.getName() + "'", stmt);
            return PrimitiveGaiaType.VOID;
        }

        Symbol symbol = new Symbol(stmt.getName(), classType, Symbol.SymbolKind.CLASS, false);
        currentScope.define(symbol);

        pushScope(Scope.ScopeKind.CLASS);

        for (ClassDeclaration.ClassMember member : stmt.getMembers()) {
            if (member instanceof FunctionDeclaration) {
                ((FunctionDeclaration) member).accept(this);
            } else if (member instanceof VariableDeclaration) {
                ((VariableDeclaration) member).accept(this);
            } else if (member instanceof ConstDeclaration) {
                ((ConstDeclaration) member).accept(this);
            }
        }

        popScope();

        return classType;
    }

    @Override
    public GaiaType visitInterfaceDeclaration(InterfaceDeclaration stmt) {
        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitCallableDeclaration(CallableDeclaration stmt) {
        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitIfStatement(IfStatement stmt) {
        GaiaType condType = inferType(stmt.getCondition());

        if (condType != PrimitiveGaiaType.BOOLEAN) {
            error("If condition must be boolean, got " + condType, stmt.getCondition());
        }

        stmt.getThenBranch().accept(this);

        if (stmt.getElseBranch() != null) {
            stmt.getElseBranch().accept(this);
        }

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitForStatement(ForStatement stmt) {
        pushScope(Scope.ScopeKind.BLOCK);

        if (stmt.getInitializer() != null) {
            if (stmt.getInitializer() instanceof ForStatement.VariableInit) {
                ((ForStatement.VariableInit) stmt.getInitializer()).declaration().accept(this);
            } else if (stmt.getInitializer() instanceof ForStatement.ExpressionListInit) {
                for (Expression expr : ((ForStatement.ExpressionListInit) stmt.getInitializer()).expressions()) {
                    inferType(expr);
                }
            }
        }

        if (stmt.getCondition() != null) {
            GaiaType condType = inferType(stmt.getCondition());
            if (condType != PrimitiveGaiaType.BOOLEAN) {
                error("For loop condition must be boolean, got " + condType, stmt.getCondition());
            }
        }

        if (stmt.getUpdate() != null) {
            for (Expression expr : stmt.getUpdate()) {
                inferType(expr);
            }
        }

        stmt.getBody().accept(this);
        popScope();

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitWhileStatement(WhileStatement stmt) {
        GaiaType condType = inferType(stmt.getCondition());
        if (condType != PrimitiveGaiaType.BOOLEAN) {
            error("While condition must be boolean, got " + condType, stmt.getCondition());
        }
        stmt.getBody().accept(this);
        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitReturnStatement(ReturnStatement stmt) {
        if (!currentScope.isInsideFunction()) {
            error("Return statement outside of function", stmt);
            return PrimitiveGaiaType.VOID;
        }

        GaiaType expectedReturn = currentScope.getEnclosingFunctionReturnType();
        GaiaType actualReturn = stmt.getValue() != null ? inferType(stmt.getValue()) : PrimitiveGaiaType.VOID;

        if (expectedReturn != null && !expectedReturn.isAssignableFrom(actualReturn)) {
            error("Return type mismatch: expected " + expectedReturn + ", got " + actualReturn, stmt);
        }

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitFailStatement(FailStatement stmt) {
        if (stmt.getValue() != null) {
            inferType(stmt.getValue());
        }

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitExpressionStatement(ExpressionStatement stmt) {
        inferType(stmt.getExpression());

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitIntLiteral(IntLiteral expr) {
        return PrimitiveGaiaType.INT;
    }

    @Override
    public GaiaType visitFloatLiteral(FloatLiteral expr) {
        return PrimitiveGaiaType.FLOAT;
    }

    @Override
    public GaiaType visitStringLiteral(StringLiteral expr) {
        return PrimitiveGaiaType.STRING;
    }

    @Override
    public GaiaType visitBooleanLiteral(BooleanLiteral expr) {
        return PrimitiveGaiaType.BOOLEAN;
    }

    @Override
    public GaiaType visitIdentifier(Identifier expr) {
        Symbol symbol = currentScope.lookup(expr.getName());

        if (symbol == null) {
            error("Undefined identifier: " + expr.getName(), expr);
            return PrimitiveGaiaType.VOID;
        }

        return symbol.type();
    }

    @Override
    public GaiaType visitArrayLiteral(ArrayLiteral expr) {
        if (expr.getElements().isEmpty()) return new ArrayGaiaType(PrimitiveGaiaType.VOID);

        GaiaType elementType = inferType(expr.getElements().getFirst());

        for (int i = 1; i < expr.getElements().size(); i++) {
            GaiaType t = inferType(expr.getElements().get(i));
            if (!elementType.isAssignableFrom(t) && !t.isAssignableFrom(elementType)) {
                error("Array elements have inconsistent types: " + elementType + " and " + t, expr);
            }
        }

        return new ArrayGaiaType(elementType);
    }

    @Override
    public GaiaType visitObjectLiteral(ObjectLiteral expr) {
        ClassGaiaType objectType = new ClassGaiaType("$object");

        for (ObjectLiteral.Property prop : expr.getProperties()) {
            GaiaType propType = inferType(prop.getValue());
            objectType.addField(prop.getKey(), propType);
        }

        return objectType;
    }

    @Override
    public GaiaType visitFunctionExpression(FunctionExpression expr) {
        List<GaiaType> paramTypes = new ArrayList<>();

        for (Parameter param : expr.getParameters()) {
            paramTypes.add(resolveType(param.type()));
        }

        GaiaType returnType = expr.getReturnType() != null ? resolveType(expr.getReturnType()) : PrimitiveGaiaType.VOID;

        FunctionGaiaType funcType = new FunctionGaiaType(paramTypes, returnType);

        pushScope(Scope.ScopeKind.FUNCTION);

        currentScope.define(new Symbol("$return", returnType, Symbol.SymbolKind.VARIABLE, false));

        for (int i = 0; i < expr.getParameters().size(); i++) {
            Parameter param = expr.getParameters().get(i);
            GaiaType paramType = paramTypes.get(i);
            currentScope.define(new Symbol(param.name(), paramType, Symbol.SymbolKind.PARAMETER, false));
        }

        expr.getBody().accept(this);
        popScope();

        return funcType;
    }

    @Override
    public GaiaType visitNewExpression(NewExpression expr) {
        GaiaType targetType = inferType(expr.getTarget());

        if (expr.getTarget() instanceof Identifier) {
            String className = ((Identifier) expr.getTarget()).getName();
            ClassGaiaType classType = classTypes.get(className);
            if (classType != null) {
                return classType;
            }
        }

        return targetType;
    }

    @Override
    public GaiaType visitBinaryExpression(BinaryExpression expr) {
        GaiaType leftType = inferType(expr.getLeft());
        GaiaType rightType = inferType(expr.getRight());

        return switch (expr.getOperator()) {
            case ADD:
                if (leftType == PrimitiveGaiaType.STRING || rightType == PrimitiveGaiaType.STRING)
                    yield PrimitiveGaiaType.STRING;

            case SUBTRACT, MULTIPLY, DIVIDE, MODULO:
                if (isNotNumeric(leftType) || isNotNumeric(rightType)) {
                    error(String.format("Arithmetic operators require numeric operands, got %s and %s", leftType, rightType), expr);
                    yield PrimitiveGaiaType.NUMBER;
                }

                if (leftType == PrimitiveGaiaType.FLOAT || rightType == PrimitiveGaiaType.FLOAT) {
                    yield PrimitiveGaiaType.FLOAT;
                }

                if (leftType == PrimitiveGaiaType.NUMBER || rightType == PrimitiveGaiaType.NUMBER) {
                    yield PrimitiveGaiaType.NUMBER;
                }

                yield PrimitiveGaiaType.INT;

            case LESS_THAN, LESS_THAN_OR_EQUAL, GREATER_THAN, GREATER_THAN_OR_EQUAL:
                if (isNotNumeric(leftType) || isNotNumeric(rightType)) {
                    error(String.format("Comparison operators require numeric operands, got %s and %s", leftType, rightType), expr);
                }
                yield PrimitiveGaiaType.BOOLEAN;

            case EQUAL, NOT_EQUAL:
                yield PrimitiveGaiaType.BOOLEAN;

            case AND, OR:
                if (leftType != PrimitiveGaiaType.BOOLEAN || rightType != PrimitiveGaiaType.BOOLEAN) {
                    error(String.format("Logical operators require boolean operands, got %s and %s", leftType, rightType), expr);
                }
                yield PrimitiveGaiaType.BOOLEAN;
        };
    }

    @Override
    public GaiaType visitUnaryExpression(UnaryExpression expr) {
        GaiaType operandType = inferType(expr.getOperand());

        return switch (expr.getOperator()) {
            case POSITIVE, NEGATIVE -> {
                if (isNotNumeric(operandType)) {
                    error("Numeric operator requires numeric operand, got " + operandType, expr);
                    yield PrimitiveGaiaType.NUMBER;
                }
                yield operandType;
            }
            case NOT -> {
                if (operandType != PrimitiveGaiaType.BOOLEAN) {
                    error("Logical not requires boolean operand, got " + operandType, expr);
                }
                yield PrimitiveGaiaType.BOOLEAN;
            }
        };
    }

    @Override
    public GaiaType visitAssignmentExpression(AssignmentExpression expr) {
        GaiaType targetType = inferType(expr.getTarget());
        GaiaType valueType = inferType(expr.getValue());

        if (expr.getTarget() instanceof Identifier) {
            String name = ((Identifier) expr.getTarget()).getName();
            Symbol symbol = currentScope.lookup(name);
            if (symbol != null && !symbol.mutable()) {
                error("Cannot assign to constant '" + name + "'", expr);
            }
        }

        if (expr.getOperator() == AssignmentExpression.AssignmentOperator.ASSIGN) {
            if (!targetType.isAssignableFrom(valueType)) {
                error("Cannot assign " + valueType + " to " + targetType, expr);
            }
        } else {
            // Compound assignment (+=, -=)
            if (isNotNumeric(targetType) || isNotNumeric(valueType)) {
                error("Compound assignment requires numeric operands", expr);
            }
        }

        return targetType;
    }

    @Override
    public GaiaType visitCallExpression(CallExpression expr) {
        GaiaType calleeType = inferType(expr.getCallee());

        if (calleeType instanceof ClassGaiaType) return calleeType;

        if (!(calleeType instanceof FunctionGaiaType funcType)) return PrimitiveGaiaType.VOID;

        if (expr.getArguments().size() != funcType.getParameterTypes().size()) {
            error(String.format("Expected %s arguments, got %s", funcType.getParameterTypes().size(), expr.getArguments().size()), expr);

            return funcType.getReturnType();
        }

        for (int i = 0; i < expr.getArguments().size(); i++) {
            GaiaType argType = inferType(expr.getArguments().get(i));
            GaiaType paramType = funcType.getParameterTypes().get(i);

            if (!paramType.isAssignableFrom(argType)) {
                error(String.format("Argument %d type mismatch: expected %s, got %s", i + 1, paramType, argType), expr.getArguments().get(i));
            }
        }

        return funcType.getReturnType();
    }

    @Override
    public GaiaType visitMemberExpression(MemberExpression expr) {
        GaiaType objectType = inferType(expr.getObject());

        if (!(objectType instanceof ClassGaiaType classType)) return PrimitiveGaiaType.VOID;

        if (classType.hasField(expr.getProperty())) {
            return classType.getFieldType(expr.getProperty());
        }

        if (classType.hasMethod(expr.getProperty())) {
            return classType.getMethodType(expr.getProperty());
        }

        error("Unknown member '" + expr.getProperty() + "' on type " + classType.getName(), expr);

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitIndexExpression(IndexExpression expr) {
        GaiaType objectType = inferType(expr.getObject());
        GaiaType indexType = inferType(expr.getIndex());

        if (isNotNumeric(indexType)) {
            error("Array index must be numeric, got " + indexType, expr.getIndex());
        }

        if (objectType instanceof ArrayGaiaType) {
            return ((ArrayGaiaType) objectType).getElementType();
        }

        error("Cannot index non-array type " + objectType, expr);

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitPrimitiveType(PrimitiveType type) {
        return PrimitiveGaiaType.fromKeyword(type.getKind().getKeyword());
    }

    @Override
    public GaiaType visitArrayType(ArrayType type) {
        GaiaType elementType = resolveType(type.getElementType());

        return new ArrayGaiaType(elementType);
    }

    @Override
    public GaiaType visitIdentifierType(IdentifierType type) {
        ClassGaiaType classType = classTypes.get(type.getName());

        if (classType != null) {
            return classType;
        }

        if (callableTypes.containsKey(type.getName())) {
            return callableTypes.get(type.getName());
        }

        error("Unknown type: " + type.getName(), type);

        return PrimitiveGaiaType.VOID;
    }

    @Override
    public GaiaType visitOneOfType(OneOfType type) {
        OneOfGaiaType oneOfType = oneOfTypes.get(type.getGroupName());

        if (oneOfType != null) {
            return oneOfType;
        }

        error("Unknown oneOf group: " + type.getGroupName(), type);

        return PrimitiveGaiaType.VOID;
    }

    private boolean isNotNumeric(GaiaType type) {
        return type != PrimitiveGaiaType.INT
                && type != PrimitiveGaiaType.FLOAT
                && type != PrimitiveGaiaType.NUMBER;
    }
}
