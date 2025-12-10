package me.minekid.gaia.bytecode;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import me.minekid.gaia.ast.*;
import me.minekid.gaia.ast.expr.*;
import me.minekid.gaia.ast.stmt.*;
import me.minekid.gaia.ast.type.*;

import org.jetbrains.annotations.Nullable;

public class BytecodeEmitter implements AstVisitor<Void> {
    private final Bytecode bytecode;
    private BytecodeFunction currentFunction;
    private final Map<String, Integer> globalVariables;
    private final List<LocalScope> scopeStack;
    private int nextLocalSlot;
    private boolean isTopLevel;

    public BytecodeEmitter() {
        this.bytecode = new Bytecode();
        this.globalVariables = new HashMap<>();
        this.scopeStack = new ArrayList<>();
        this.nextLocalSlot = 0;
        this.isTopLevel = false;
    }

    public Bytecode compile(Program program) {
        currentFunction = new BytecodeFunction("$main", 0, 0);
        scopeStack.clear();
        isTopLevel = true;

        pushScope();

        for (Statement stmt : program.getStatements()) {
            if (stmt instanceof FunctionDeclaration) {
                FunctionDeclaration funcDecl = (FunctionDeclaration) stmt;

                if (funcDecl.getName() != null) {
                    int nameIndex = bytecode.addConstant(funcDecl.getName());

                    globalVariables.put(funcDecl.getName(), nameIndex);
                }
            }
        }

        program.accept(this);

        currentFunction.emit(OpCode.CONST_NULL);
        currentFunction.emit(OpCode.RETURN);

        popScope();

        isTopLevel = false;

        BytecodeFunction mainFunc = new BytecodeFunction("$main", 0, nextLocalSlot);
        byte[] code = currentFunction.getCodeBytes();
        for (byte b : code) {
            mainFunc.emitByte(b & 0xFF);
        }

        int mainIndex = bytecode.addFunction(mainFunc);
        bytecode.setMainFunctionIndex(mainIndex);

        return bytecode;
    }

    public Bytecode getBytecode() {
        return bytecode;
    }

    private int getLine(AstNode node) {
        if (node != null && node.getLocation() != null) {
            return node.getLocation().line();
        }

        return -1;
    }

    private void pushScope() {
        scopeStack.add(new LocalScope());
    }

    private void popScope() {
        if (scopeStack.isEmpty()) return;
        scopeStack.removeLast();
    }

    private int declareLocal(String name) {
        if (!scopeStack.isEmpty()) {
            LocalScope scope = scopeStack.get(scopeStack.size() - 1);
            scope.locals.put(name, nextLocalSlot);

            return nextLocalSlot++;
        }

        return -1;
    }

    private int resolveLocal(String name) {
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            Integer slot = scopeStack.get(i).locals.get(name);

            if (slot != null) {
                return slot;
            }
        }

        return -1;
    }

    private static class LocalScope {
        Map<String, Integer> locals = new HashMap<>();
    }

    @Override
    public Void visitProgram(Program program) {
        for (ImportStatement imp : program.getImports()) {
            imp.accept(this);
        }

        for (Statement stmt : program.getStatements()) {
            stmt.accept(this);
        }

        return null;
    }

    @Override
    public Void visitImportStatement(ImportStatement stmt) {
        return null;
    }

    @Override
    public Void visitBlockStatement(BlockStatement stmt) {
        pushScope();

        for (Statement s : stmt.getStatements()) {
            s.accept(this);
        }

        popScope();

        return null;
    }

    @Override
    public Void visitVariableDeclaration(VariableDeclaration stmt) {
        if (stmt.getInitializer() != null) {
            stmt.getInitializer().accept(this);
        } else {
            currentFunction.emit(OpCode.CONST_NULL, getLine(stmt));
        }

        return resolveDecl(stmt.getName(), getLine(stmt), stmt);
    }

    @Override
    public Void visitConstDeclaration(ConstDeclaration stmt) {
        stmt.getInitializer().accept(this);

        return resolveDecl(stmt.getName(), getLine(stmt), stmt);
    }

    @Nullable
    private Void resolveDecl(String name, int line, Statement stmt) {
        if (isTopLevel && scopeStack.size() == 1) {
            int nameIndex = bytecode.addConstant(name);

            globalVariables.put(name, nameIndex);
            currentFunction.emit(OpCode.STORE_GLOBAL, line);
            currentFunction.emitShort(nameIndex);
        } else {
            int slot = declareLocal(name);

            if (slot >= 0) {
                currentFunction.emit(OpCode.STORE_LOCAL, line);
                currentFunction.emitShort(slot);
            } else {
                int nameIndex = bytecode.addConstant(name);

                globalVariables.put(name, nameIndex);
                currentFunction.emit(OpCode.STORE_GLOBAL, line);
                currentFunction.emitShort(nameIndex);
            }
        }

        return null;
    }

    @Override
    public Void visitFunctionDeclaration(FunctionDeclaration stmt) {
        BytecodeFunction outerFunction = currentFunction;
        List<LocalScope> outerScopes = new ArrayList<>(scopeStack);
        int outerNextLocal = nextLocalSlot;
        int arity = stmt.getParameters().size();

        currentFunction = new BytecodeFunction(stmt.getName(), arity, 0);
        scopeStack.clear();
        nextLocalSlot = 0;

        pushScope();

        for (Parameter param : stmt.getParameters()) {
            declareLocal(param.name());
        }

        stmt.getBody().accept(this);

        currentFunction.emit(OpCode.CONST_NULL, getLine(stmt));
        currentFunction.emit(OpCode.RETURN, getLine(stmt));

        BytecodeFunction compiledFunc = new BytecodeFunction(stmt.getName(), arity, nextLocalSlot);
        byte[] code = currentFunction.getCodeBytes();

        for (byte b : code) {
            compiledFunc.emitByte(b & 0xFF);
        }

        int funcIndex = bytecode.addFunction(compiledFunc);
        popScope();

        currentFunction = outerFunction;
        scopeStack.clear();
        scopeStack.addAll(outerScopes);
        nextLocalSlot = outerNextLocal;

        if (stmt.getName() != null && currentFunction != null) {
            currentFunction.emit(OpCode.LOAD_FUNCTION, getLine(stmt));
            currentFunction.emitShort(funcIndex);

            int slot = resolveLocal(stmt.getName());
            if (slot >= 0) {
                currentFunction.emit(OpCode.STORE_LOCAL, getLine(stmt));
                currentFunction.emitShort(slot);
            } else {
                int nameIndex = bytecode.addConstant(stmt.getName());
                currentFunction.emit(OpCode.STORE_GLOBAL, getLine(stmt));
                currentFunction.emitShort(nameIndex);
            }
        }

        return null;
    }

    @Override
    public Void visitClassDeclaration(ClassDeclaration stmt) {
        //TODO: Compile classes
        int nameIndex = bytecode.addConstant(stmt.getName());
        globalVariables.put(stmt.getName(), nameIndex);
        return null;
    }

    @Override
    public Void visitInterfaceDeclaration(InterfaceDeclaration stmt) {
        return null;
    }

    @Override
    public Void visitCallableDeclaration(CallableDeclaration stmt) {
        return null;
    }

    @Override
    public Void visitIfStatement(IfStatement stmt) {
        stmt.getCondition().accept(this);

        int jumpIfFalseOffset = currentFunction.getCodeOffset();
        currentFunction.emit(OpCode.JUMP_IF_FALSE, getLine(stmt));
        currentFunction.emitShort(0); // Placeholder

        stmt.getThenBranch().accept(this);

        if (stmt.getElseBranch() != null) {
            int jumpOverElseOffset = currentFunction.getCodeOffset();
            currentFunction.emit(OpCode.JUMP, getLine(stmt));
            currentFunction.emitShort(0); // Placeholder

            int elseStart = currentFunction.getCodeOffset();
            currentFunction.patchShort(jumpIfFalseOffset + 1, elseStart);

            stmt.getElseBranch().accept(this);

            int afterElse = currentFunction.getCodeOffset();
            currentFunction.patchShort(jumpOverElseOffset + 1, afterElse);
        } else {
            int afterThen = currentFunction.getCodeOffset();
            currentFunction.patchShort(jumpIfFalseOffset + 1, afterThen);
        }

        return null;
    }

    @Override
    public Void visitForStatement(ForStatement stmt) {
        pushScope();

        if (stmt.getInitializer() != null) {
            if (stmt.getInitializer() instanceof ForStatement.VariableInit) {
                ((ForStatement.VariableInit) stmt.getInitializer()).declaration().accept(this);
            } else if (stmt.getInitializer() instanceof ForStatement.ExpressionListInit) {
                for (Expression expr : ((ForStatement.ExpressionListInit) stmt.getInitializer()).expressions()) {
                    expr.accept(this);
                    currentFunction.emit(OpCode.POP);
                }
            }
        }

        int loopStart = currentFunction.getCodeOffset();
        int jumpToEndOffset = -1;

        if (stmt.getCondition() != null) {
            stmt.getCondition().accept(this);

            jumpToEndOffset = currentFunction.getCodeOffset();

            currentFunction.emit(OpCode.JUMP_IF_FALSE, getLine(stmt));
            currentFunction.emitShort(0);
        }

        stmt.getBody().accept(this);

        if (stmt.getUpdate() != null) {
            for (Expression expr : stmt.getUpdate()) {
                expr.accept(this);
                currentFunction.emit(OpCode.POP);
            }
        }

        currentFunction.emit(OpCode.JUMP, getLine(stmt));
        currentFunction.emitShort(loopStart);

        if (jumpToEndOffset >= 0) {
            int loopEnd = currentFunction.getCodeOffset();
            currentFunction.patchShort(jumpToEndOffset + 1, loopEnd);
        }

        popScope();
        return null;
    }

    @Override
    public Void visitWhileStatement(WhileStatement stmt) {
        int loopStart = currentFunction.getCodeOffset();

        stmt.getCondition().accept(this);

        int jumpToEndOffset = currentFunction.getCodeOffset();
        currentFunction.emit(OpCode.JUMP_IF_FALSE, getLine(stmt));
        currentFunction.emitShort(0);

        stmt.getBody().accept(this);

        currentFunction.emit(OpCode.JUMP, getLine(stmt));
        currentFunction.emitShort(loopStart);

        int loopEnd = currentFunction.getCodeOffset();
        currentFunction.patchShort(jumpToEndOffset + 1, loopEnd);

        return null;
    }

    @Override
    public Void visitReturnStatement(ReturnStatement stmt) {
        if (stmt.getValue() != null) {
            stmt.getValue().accept(this);
        } else {
            currentFunction.emit(OpCode.CONST_NULL, getLine(stmt));
        }

        currentFunction.emit(OpCode.RETURN, getLine(stmt));

        return null;
    }

    @Override
    public Void visitFailStatement(FailStatement stmt) {
        if (stmt.getValue() != null) {
            stmt.getValue().accept(this);
        } else {
            int errorIndex = bytecode.addConstant("Error");

            currentFunction.emit(OpCode.LOAD_CONST, getLine(stmt));
            currentFunction.emitShort(errorIndex);
        }

        currentFunction.emit(OpCode.FAIL, getLine(stmt));
        currentFunction.emit(OpCode.RETURN, getLine(stmt));

        return null;
    }

    @Override
    public Void visitExpressionStatement(ExpressionStatement stmt) {
        stmt.getExpression().accept(this);

        currentFunction.emit(OpCode.POP, getLine(stmt));

        return null;
    }

    @Override
    public Void visitIntLiteral(IntLiteral expr) {
        currentFunction.emit(OpCode.CONST_INT, getLine(expr));
        currentFunction.emitLong(expr.getValue());

        return null;
    }

    @Override
    public Void visitFloatLiteral(FloatLiteral expr) {
        currentFunction.emit(OpCode.CONST_FLOAT, getLine(expr));
        currentFunction.emitDouble(expr.getValue());

        return null;
    }

    @Override
    public Void visitStringLiteral(StringLiteral expr) {
        int index = bytecode.addConstant(expr.getValue());

        currentFunction.emit(OpCode.LOAD_CONST, getLine(expr));
        currentFunction.emitShort(index);

        return null;
    }

    @Override
    public Void visitBooleanLiteral(BooleanLiteral expr) {
        currentFunction.emit(expr.getValue() ? OpCode.CONST_TRUE : OpCode.CONST_FALSE, getLine(expr));
        return null;
    }

    @Override
    public Void visitIdentifier(Identifier expr) {
        int slot = resolveLocal(expr.getName());

        if (slot >= 0) {
            currentFunction.emit(OpCode.LOAD_LOCAL, getLine(expr));
            currentFunction.emitShort(slot);
        } else {
            Integer nameIndex = globalVariables.get(expr.getName());

            if (nameIndex == null) {
                nameIndex = bytecode.addConstant(expr.getName());
                globalVariables.put(expr.getName(), nameIndex);
            }

            currentFunction.emit(OpCode.LOAD_GLOBAL, getLine(expr));
            currentFunction.emitShort(nameIndex);
        }

        return null;
    }

    @Override
    public Void visitArrayLiteral(ArrayLiteral expr) {
        for (Expression element : expr.getElements()) {
            element.accept(this);
        }

        currentFunction.emit(OpCode.NEW_ARRAY, getLine(expr));
        currentFunction.emitShort(expr.getElements().size());

        return null;
    }

    @Override
    public Void visitObjectLiteral(ObjectLiteral expr) {
        int classIndex = bytecode.addConstant("$object");

        currentFunction.emit(OpCode.NEW_OBJECT, getLine(expr));
        currentFunction.emitShort(classIndex);

        for (ObjectLiteral.Property prop : expr.getProperties()) {
            currentFunction.emit(OpCode.DUP);
            prop.getValue().accept(this);

            int fieldIndex = bytecode.addConstant(prop.getKey());

            currentFunction.emit(OpCode.SET_FIELD, getLine(expr));
            currentFunction.emitShort(fieldIndex);
        }

        return null;
    }

    @Override
    public Void visitFunctionExpression(FunctionExpression expr) {
        BytecodeFunction outerFunction = currentFunction;
        List<LocalScope> outerScopes = new ArrayList<>(scopeStack);

        int outerNextLocal = nextLocalSlot;

        int arity = expr.getParameters().size();

        currentFunction = new BytecodeFunction(expr.getName(), arity, 0);
        scopeStack.clear();
        nextLocalSlot = 0;

        pushScope();

        for (Parameter param : expr.getParameters()) {
            declareLocal(param.name());
        }

        expr.getBody().accept(this);

        currentFunction.emit(OpCode.CONST_NULL);
        currentFunction.emit(OpCode.RETURN);

        BytecodeFunction compiledFunc = new BytecodeFunction(expr.getName(), arity, nextLocalSlot);
        byte[] code = currentFunction.getCodeBytes();

        for (byte b : code) {
            compiledFunc.emitByte(b & 0xFF);
        }

        int funcIndex = bytecode.addFunction(compiledFunc);
        popScope();

        currentFunction = outerFunction;
        scopeStack.clear();
        scopeStack.addAll(outerScopes);
        nextLocalSlot = outerNextLocal;

        currentFunction.emit(OpCode.LOAD_FUNCTION, getLine(expr));
        currentFunction.emitShort(funcIndex);

        return null;
    }

    @Override
    public Void visitNewExpression(NewExpression expr) {
        expr.getTarget().accept(this);
        return null;
    }

    @Override
    public Void visitBinaryExpression(BinaryExpression expr) {
        if (expr.getOperator() == BinaryExpression.BinaryOperator.AND) {
            expr.getLeft().accept(this);

            int jumpIfFalse = currentFunction.getCodeOffset();

            currentFunction.emit(OpCode.JUMP_IF_FALSE, getLine(expr));
            currentFunction.emitShort(0);
            currentFunction.emit(OpCode.POP);

            expr.getRight().accept(this);

            int end = currentFunction.getCodeOffset();

            currentFunction.patchShort(jumpIfFalse + 1, end);

            return null;
        }

        if (expr.getOperator() == BinaryExpression.BinaryOperator.OR) {
            expr.getLeft().accept(this);

            int jumpIfTrue = currentFunction.getCodeOffset();

            currentFunction.emit(OpCode.JUMP_IF_TRUE, getLine(expr));
            currentFunction.emitShort(0);
            currentFunction.emit(OpCode.POP);

            expr.getRight().accept(this);

            int end = currentFunction.getCodeOffset();

            currentFunction.patchShort(jumpIfTrue + 1, end);

            return null;
        }

        expr.getLeft().accept(this);
        expr.getRight().accept(this);

        switch (expr.getOperator()) {
            case ADD:
                currentFunction.emit(OpCode.ADD, getLine(expr));
                break;
            case SUBTRACT:
                currentFunction.emit(OpCode.SUB, getLine(expr));
                break;
            case MULTIPLY:
                currentFunction.emit(OpCode.MUL, getLine(expr));
                break;
            case DIVIDE:
                currentFunction.emit(OpCode.DIV, getLine(expr));
                break;
            case MODULO:
                currentFunction.emit(OpCode.MOD, getLine(expr));
                break;
            case EQUAL:
                currentFunction.emit(OpCode.EQ, getLine(expr));
                break;
            case NOT_EQUAL:
                currentFunction.emit(OpCode.NE, getLine(expr));
                break;
            case LESS_THAN:
                currentFunction.emit(OpCode.LT, getLine(expr));
                break;
            case LESS_THAN_OR_EQUAL:
                currentFunction.emit(OpCode.LE, getLine(expr));
                break;
            case GREATER_THAN:
                currentFunction.emit(OpCode.GT, getLine(expr));
                break;
            case GREATER_THAN_OR_EQUAL:
                currentFunction.emit(OpCode.GE, getLine(expr));
                break;
            default:
                throw new RuntimeException("Unknown binary operator: " + expr.getOperator());
        }

        return null;
    }

    @Override
    public Void visitUnaryExpression(UnaryExpression expr) {
        expr.getOperand().accept(this);

        switch (expr.getOperator()) {
            case POSITIVE:
                break;
            case NEGATIVE:
                currentFunction.emit(OpCode.NEG, getLine(expr));
                break;
            case NOT:
                currentFunction.emit(OpCode.NOT, getLine(expr));
                break;
            default:
                throw new RuntimeException("Unknown unary operator: " + expr.getOperator());
        }

        return null;
    }

    @Override
    public Void visitAssignmentExpression(AssignmentExpression expr) {
        if (expr.getOperator() != AssignmentExpression.AssignmentOperator.ASSIGN) {
            expr.getTarget().accept(this);
            expr.getValue().accept(this);

            if (expr.getOperator() == AssignmentExpression.AssignmentOperator.PLUS_ASSIGN) {
                currentFunction.emit(OpCode.ADD, getLine(expr));
            } else if (expr.getOperator() == AssignmentExpression.AssignmentOperator.MINUS_ASSIGN) {
                currentFunction.emit(OpCode.SUB, getLine(expr));
            }

        } else {
            expr.getValue().accept(this);
        }

        currentFunction.emit(OpCode.DUP);

        if (expr.getTarget() instanceof Identifier) {
            String name = ((Identifier) expr.getTarget()).getName();

            int slot = resolveLocal(name);

            if (slot >= 0) {
                currentFunction.emit(OpCode.STORE_LOCAL, getLine(expr));
                currentFunction.emitShort(slot);
            } else {
                Integer nameIndex = globalVariables.get(name);

                if (nameIndex == null) {
                    nameIndex = bytecode.addConstant(name);
                    globalVariables.put(name, nameIndex);
                }

                currentFunction.emit(OpCode.STORE_GLOBAL, getLine(expr));
                currentFunction.emitShort(nameIndex);
            }
        } else if (expr.getTarget() instanceof MemberExpression member) {
            member.getObject().accept(this);

            int fieldIndex = bytecode.addConstant(member.getProperty());

            currentFunction.emit(OpCode.SET_FIELD, getLine(expr));
            currentFunction.emitShort(fieldIndex);
        } else if (expr.getTarget() instanceof IndexExpression index) {
            index.getObject().accept(this);
            index.getIndex().accept(this);

            currentFunction.emit(OpCode.ARRAY_SET, getLine(expr));
        }

        return null;
    }

    @Override
    public Void visitCallExpression(CallExpression expr) {
        expr.getCallee().accept(this);

        for (Expression arg : expr.getArguments()) {
            arg.accept(this);
        }

        currentFunction.emit(OpCode.CALL, getLine(expr));
        currentFunction.emitByte(expr.getArguments().size());

        return null;
    }

    @Override
    public Void visitMemberExpression(MemberExpression expr) {
        expr.getObject().accept(this);

        int fieldIndex = bytecode.addConstant(expr.getProperty());

        currentFunction.emit(OpCode.GET_FIELD, getLine(expr));
        currentFunction.emitShort(fieldIndex);

        return null;
    }

    @Override
    public Void visitIndexExpression(IndexExpression expr) {
        expr.getObject().accept(this);
        expr.getIndex().accept(this);

        currentFunction.emit(OpCode.ARRAY_GET, getLine(expr));

        return null;
    }

    @Override
    public Void visitPrimitiveType(PrimitiveType type) {
        return null;
    }

    @Override
    public Void visitArrayType(ArrayType type) {
        return null;
    }

    @Override
    public Void visitIdentifierType(IdentifierType type) {
        return null;
    }

    @Override
    public Void visitOneOfType(OneOfType type) {
        return null;
    }
}
