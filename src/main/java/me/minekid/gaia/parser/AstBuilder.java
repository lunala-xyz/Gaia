package me.minekid.gaia.parser;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Supplier;

import me.minekid.gaia.antlr.GaiaBaseVisitor;
import me.minekid.gaia.ast.*;
import me.minekid.gaia.ast.expr.*;
import me.minekid.gaia.ast.stmt.*;
import me.minekid.gaia.ast.type.*;
import me.minekid.gaia.antlr.GaiaParser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import static java.util.Objects.isNull;

public class AstBuilder extends GaiaBaseVisitor<AstNode> {
    private final String source;

    public AstBuilder(String source) {
        this.source = source;
    }

    private SourceLocation getLocation(ParserRuleContext ctx) {
        Token start = ctx.getStart();
        Token stop = ctx.getStop();

        assert (start != null);
        assert (stop != null);

        return new SourceLocation(
                start.getLine(),
                start.getCharPositionInLine(),
                start.getStartIndex(),
                stop.getStopIndex(),
                source);
    }

    private void setLocation(AstNode node, ParserRuleContext ctx) {
        node.setLocation(getLocation(ctx));
    }

    @Override
    public Program visitProg(GaiaParser.ProgContext ctx) {
        List<ImportStatement> imports = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();

        for (GaiaParser.ImportStmtContext importCtx : ctx.importStmt()) {
            imports.add((ImportStatement) visit(importCtx));
        }

        for (GaiaParser.StatementContext stmtCtx : ctx.statement()) {
            Statement stmt = (Statement) visit(stmtCtx);
            if (stmt != null) {
                statements.add(stmt);
            }
        }

        Program program = new Program(imports, statements);
        setLocation(program, ctx);

        return program;
    }

    @Override
    public ImportStatement visitImportStmt(GaiaParser.ImportStmtContext ctx) {
        String path = parseString(ctx.STRING_LITERAL().getText());
        String alias = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : null;
        ImportStatement stmt = new ImportStatement(path, alias);
        setLocation(stmt, ctx);

        return stmt;
    }

    @Override
    public Statement visitStatement(GaiaParser.StatementContext ctx) {
        return visitFirstNonNull(
                ctx::block,
                ctx::variableDecl,
                ctx::constDecl,
                ctx::funcDecl,
                ctx::ifStmt,
                ctx::forStmt,
                ctx::whileStmt,
                ctx::returnStmt,
                ctx::failStmt,
                ctx::exprStatement
        );
    }

    @SafeVarargs
    private Statement visitFirstNonNull(Supplier<? extends ParserRuleContext>... getters) {
        for (Supplier<? extends ParserRuleContext> g : getters) {
            ParserRuleContext node = g.get();
            if (node != null) {
                return (Statement) visit(node);
            }
        }

        return null;
    }

    @Override
    public BlockStatement visitBlock(GaiaParser.BlockContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (GaiaParser.StatementContext stmtCtx : ctx.statement()) {
            Statement stmt = (Statement) visit(stmtCtx);
            if (stmt != null) {
                statements.add(stmt);
            }
        }

        for (GaiaParser.ExprStatementContext exprCtx : ctx.exprStatement()) {
            statements.add((ExpressionStatement) visit(exprCtx));
        }

        BlockStatement block = new BlockStatement(statements);
        setLocation(block, ctx);

        return block;
    }

    @Override
    public VariableDeclaration visitVariableDecl(GaiaParser.VariableDeclContext ctx) {
        Visibility visibility = parseVisibility(ctx.PUBLIC(), ctx.PRIVATE());
        String name = ctx.IDENTIFIER().getText();
        TypeExpression type = ctx.typeExpr() != null ? (TypeExpression) visit(ctx.typeExpr()) : null;
        Expression initializer = ctx.expression() != null ? visitExpressionNode(ctx.expression()) : null;
        VariableDeclaration decl = new VariableDeclaration(visibility, name, type, initializer);

        setLocation(decl, ctx);

        return decl;
    }

    @Override
    public FunctionDeclaration visitFuncDecl(GaiaParser.FuncDeclContext ctx) {
        Visibility visibility = parseVisibility(ctx.PUBLIC(), ctx.PRIVATE());
        String name = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : null;
        List<Parameter> params = ctx.paramList() != null ? visitParameterList(ctx.paramList()) : new ArrayList<>();
        TypeExpression returnType = ctx.typeExpr() != null ? (TypeExpression) visit(ctx.typeExpr()) : null;
        BlockStatement body = (BlockStatement) visit(ctx.block());
        FunctionDeclaration decl = new FunctionDeclaration(visibility, name, params, returnType, body);

        setLocation(decl, ctx);

        return decl;
    }

    @Override
    public ConstDeclaration visitConstDecl(GaiaParser.ConstDeclContext ctx) {
        Visibility visibility = parseVisibility(ctx.PUBLIC(), ctx.PRIVATE());
        String name = ctx.IDENTIFIER().getText();
        TypeExpression type = ctx.typeExpr() != null ? (TypeExpression) visit(ctx.typeExpr()) : null;
        Expression initializer = visitExpressionNode(ctx.expression());
        ConstDeclaration decl = new ConstDeclaration(visibility, name, type, initializer);

        setLocation(decl, ctx);

        return decl;
    }

    @Override
    public IfStatement visitIfStmt(GaiaParser.IfStmtContext ctx) {
        Expression condition = visitExpressionNode(ctx.expression());
        Statement thenBranch = (Statement) visit(ctx.statement(0));
        Statement elseBranch = ctx.statement().size() > 1 ? (Statement) visit(ctx.statement(1)) : null;
        IfStatement stmt = new IfStatement(condition, thenBranch, elseBranch);

        setLocation(stmt, ctx);

        return stmt;
    }

    @Override
    public ForStatement visitForStmt(GaiaParser.ForStmtContext ctx) {
        ForStatement.ForInit initializer = null;

        if (ctx.forInit() != null) {
            if (ctx.forInit().variableDeclNoSemi() != null) {
                VariableDeclaration varDecl = processVariableDeclNoSemi(ctx.forInit().variableDeclNoSemi());
                initializer = new ForStatement.VariableInit(varDecl);
            } else if (ctx.forInit().exprList() != null) {
                List<Expression> expr = visitExpressionList(ctx.forInit().exprList());
                initializer = new ForStatement.ExpressionListInit(expr);
            }
        }

        Expression condition = ctx.expression() != null ? visitExpressionNode(ctx.expression()) : null;
        List<Expression> update = ctx.forUpdate() != null ? visitExpressionList(ctx.forUpdate().exprList()) : new ArrayList<>();
        Statement body = (Statement) visit(ctx.statement());
        ForStatement stmt = new ForStatement(initializer, condition, update, body);

        setLocation(stmt, ctx);

        return stmt;
    }

    private VariableDeclaration processVariableDeclNoSemi(GaiaParser.VariableDeclNoSemiContext ctx) {
        Visibility visibility = parseVisibility(ctx.PUBLIC(), ctx.PRIVATE());
        String name = ctx.IDENTIFIER().getText();
        TypeExpression type = ctx.typeExpr() != null ? (TypeExpression) visit(ctx.typeExpr()) : null;
        Expression initializer = ctx.expression() != null ? visitExpressionNode(ctx.expression()) : null;
        VariableDeclaration decl = new VariableDeclaration(visibility, name, type, initializer);

        setLocation(decl, ctx);

        return decl;
    }

    @Override
    public WhileStatement visitWhileStmt(GaiaParser.WhileStmtContext ctx) {
        Expression condition = visitExpressionNode(ctx.expression());
        Statement body = (Statement) visit(ctx.statement());
        WhileStatement stmt = new WhileStatement(condition, body);

        setLocation(stmt, ctx);

        return stmt;
    }

    @Override
    public ReturnStatement visitReturnStmt(GaiaParser.ReturnStmtContext ctx) {
        Expression value = ctx.expression() != null ? visitExpressionNode(ctx.expression()) : null;
        ReturnStatement stmt = new ReturnStatement(value);

        setLocation(stmt, ctx);

        return stmt;
    }

    @Override
    public FailStatement visitFailStmt(GaiaParser.FailStmtContext ctx) {
        Expression value = ctx.expression() != null ? visitExpressionNode(ctx.expression()) : null;
        FailStatement stmt = new FailStatement(value);

        setLocation(stmt, ctx);

        return stmt;
    }

    @Override
    public ExpressionStatement visitExprStatement(GaiaParser.ExprStatementContext ctx) {
        Expression expr = visitExpressionNode(ctx.expression());
        ExpressionStatement stmt = new ExpressionStatement(expr);

        setLocation(stmt, ctx);

        return stmt;
    }

    private Expression visitExpressionNode(GaiaParser.ExpressionContext ctx) {
        return visitAssignmentExpr(ctx.assignment());
    }

    private Expression visitAssignmentExpr(GaiaParser.AssignmentContext ctx) {
        if (!(ctx.ASSIGN() != null || ctx.PLUS_ASSIGN() != null || ctx.MINUS_ASSIGN() != null))
            return visitConditionalExpr(ctx.conditional());

        Expression target = visitConditionalExpr(ctx.conditional());
        String opText;

        if (ctx.ASSIGN() != null) opText = "=";
        else if (ctx.PLUS_ASSIGN() != null) opText = "+=";
        else opText = "-=";

        AssignmentExpression.AssignmentOperator op = AssignmentExpression.AssignmentOperator.fromSymbol(opText);
        Expression value = visitAssignmentExpr(ctx.assignment());
        AssignmentExpression expr = new AssignmentExpression(target, op, value);

        setLocation(expr, ctx);

        return expr;
    }

    private Expression visitConditionalExpr(GaiaParser.ConditionalContext ctx) {
        return visitLogicalOrExpr(ctx.logicalOr());
    }

    private Expression visitLogicalOrExpr(GaiaParser.LogicalOrContext ctx) {
        Expression left = visitLogicalAndExpr(ctx.logicalAnd(0));

        for (int i = 1; i < ctx.logicalAnd().size(); i++) {
            Expression right = visitLogicalAndExpr(ctx.logicalAnd(i));
            BinaryExpression expr = new BinaryExpression(left, BinaryExpression.BinaryOperator.OR, right);

            setLocation(expr, ctx);
            left = expr;
        }

        return left;
    }

    private Expression visitLogicalAndExpr(GaiaParser.LogicalAndContext ctx) {
        Expression left = visitEqualityExpr(ctx.equality(0));

        for (int i = 1; i < ctx.equality().size(); i++) {
            Expression right = visitEqualityExpr(ctx.equality(i));
            BinaryExpression expr = new BinaryExpression(left, BinaryExpression.BinaryOperator.AND, right);

            setLocation(expr, ctx);
            left = expr;
        }

        return left;
    }

    private Expression visitEqualityExpr(GaiaParser.EqualityContext ctx) {
        Expression left = visitRelationalExpr(ctx.relational(0));

        for (int i = 1; i < ctx.relational().size(); i++) {
            String opText = ctx.getChild(2 * i - 1).getText();
            BinaryExpression.BinaryOperator op = BinaryExpression.BinaryOperator.fromSymbol(opText);
            Expression right = visitRelationalExpr(ctx.relational(i));
            BinaryExpression expr = new BinaryExpression(left, op, right);

            setLocation(expr, ctx);
            left = expr;
        }

        return left;
    }

    private Expression visitRelationalExpr(GaiaParser.RelationalContext ctx) {
        Expression left = visitAdditiveExpr(ctx.additive(0));

        for (int i = 1; i < ctx.additive().size(); i++) {
            String opText = ctx.getChild(2 * i - 1).getText();
            BinaryExpression.BinaryOperator op = BinaryExpression.BinaryOperator.fromSymbol(opText);
            Expression right = visitAdditiveExpr(ctx.additive(i));
            BinaryExpression expr = new BinaryExpression(left, op, right);

            setLocation(expr, ctx);
            left = expr;
        }

        return left;
    }

    private Expression visitAdditiveExpr(GaiaParser.AdditiveContext ctx) {
        Expression left = visitMultiplicativeExpr(ctx.multiplicative(0));

        for (int i = 1; i < ctx.multiplicative().size(); i++) {
            String opText = ctx.getChild(2 * i - 1).getText();
            BinaryExpression.BinaryOperator op = BinaryExpression.BinaryOperator.fromSymbol(opText);
            Expression right = visitMultiplicativeExpr(ctx.multiplicative(i));
            BinaryExpression expr = new BinaryExpression(left, op, right);

            setLocation(expr, ctx);
            left = expr;
        }

        return left;
    }

    private Expression visitMultiplicativeExpr(GaiaParser.MultiplicativeContext ctx) {
        Expression left = visitUnaryExpr(ctx.unary(0));

        for (int i = 1; i < ctx.unary().size(); i++) {
            String opText = ctx.getChild(2 * i - 1).getText();
            BinaryExpression.BinaryOperator op = BinaryExpression.BinaryOperator.fromSymbol(opText);
            Expression right = visitUnaryExpr(ctx.unary(i));
            BinaryExpression expr = new BinaryExpression(left, op, right);

            setLocation(expr, ctx);
            left = expr;
        }

        return left;
    }

    private Expression visitUnaryExpr(GaiaParser.UnaryContext ctx) {
        if (!(ctx.PLUS() != null || ctx.MINUS() != null || ctx.NOT() != null)) return visitPostfixExpr(ctx.postfix());

        String opText;

        if (ctx.PLUS() != null) opText = "+";
        else if (ctx.MINUS() != null) opText = "-";
        else opText = "!";

        UnaryExpression.UnaryOperator op = UnaryExpression.UnaryOperator.fromSymbol(opText);
        Expression operand = visitUnaryExpr(ctx.unary());
        UnaryExpression expr = new UnaryExpression(op, operand);

        setLocation(expr, ctx);

        return expr;
    }

    private Expression visitPostfixExpr(GaiaParser.PostfixContext ctx) {
        Expression expr = visitPrimaryExpr(ctx.primary());

        for (GaiaParser.PostfixOpContext opCtx : ctx.postfixOp()) {
            if (opCtx.call() != null) {
                List<Expression> args = opCtx.call().argumentList() != null ? processArgumentList(opCtx.call().argumentList()) : new ArrayList<>();
                expr = new CallExpression(expr, args);
                setLocation(expr, opCtx);
            } else if (opCtx.memberAccess() != null) {
                String property = opCtx.memberAccess().IDENTIFIER().getText();
                expr = new MemberExpression(expr, property);
                setLocation(expr, opCtx);
            } else if (opCtx.indexAccess() != null) {
                Expression index = visitExpressionNode(opCtx.indexAccess().expression());
                expr = new IndexExpression(expr, index);
                setLocation(expr, opCtx);
            }
        }

        return expr;
    }

    private Expression visitPrimaryExpr(GaiaParser.PrimaryContext ctx) {
        if (ctx.FLOAT_LITERAL() != null) return withLocation(ctx, () -> {
            String text = ctx.FLOAT_LITERAL().getText();
            if (text.endsWith("f") || text.endsWith("F")) {
                text = text.substring(0, text.length() - 1);
            }
            return new FloatLiteral(Double.parseDouble(text));
        });

        if (ctx.funcExpr() != null) return processFuncExpr(ctx.funcExpr());
        if (ctx.newExpression() != null) return visitNewExpr(ctx.newExpression());
        if (ctx.expression() != null) return visitExpressionNode(ctx.expression());
        if (ctx.arrayLiteral() != null) return visitArrayLiteralExpr(ctx.arrayLiteral());
        if (ctx.objectLiteral() != null) return visitObjectLiteralExpr(ctx.objectLiteral());

        if (ctx.TRUE() != null) return withLocation(ctx, () -> new BooleanLiteral(true));
        if (ctx.FALSE() != null) return withLocation(ctx, () -> new BooleanLiteral(false));

        if (ctx.IDENTIFIER() != null) return withLocation(ctx, () -> new Identifier(ctx.IDENTIFIER().getText()));
        if (ctx.INT_LITERAL() != null)
            return withLocation(ctx, () -> new IntLiteral(Long.parseLong(ctx.INT_LITERAL().getText())));
        if (ctx.STRING_LITERAL() != null)
            return withLocation(ctx, () -> new StringLiteral(parseString(ctx.STRING_LITERAL().getText())));

        throw new IllegalStateException("Unknown primary expression: " + ctx.getText());
    }

    private <T extends Expression> T withLocation(ParserRuleContext ctx, Supplier<T> builder) {
        T expr = builder.get();
        setLocation(expr, ctx);
        return expr;
    }

    private ArrayLiteral visitArrayLiteralExpr(GaiaParser.ArrayLiteralContext ctx) {
        List<Expression> elements = new ArrayList<>();

        for (GaiaParser.ExpressionContext exprCtx : ctx.expression()) {
            elements.add(visitExpressionNode(exprCtx));
        }

        ArrayLiteral expr = new ArrayLiteral(elements);
        setLocation(expr, ctx);

        return expr;
    }

    private ObjectLiteral visitObjectLiteralExpr(GaiaParser.ObjectLiteralContext ctx) {
        List<ObjectLiteral.Property> properties = new ArrayList<>();

        for (GaiaParser.PropertyContext propCtx : ctx.property()) {
            String key = !isNull(propCtx.IDENTIFIER()) ? propCtx.IDENTIFIER().getText() : parseString(propCtx.STRING_LITERAL().getText());
            Expression value = visitExpressionNode(propCtx.expression());

            properties.add(new ObjectLiteral.Property(key, value));
        }

        ObjectLiteral expr = new ObjectLiteral(properties);
        setLocation(expr, ctx);

        return expr;
    }

    private FunctionExpression processFuncExpr(GaiaParser.FuncExprContext ctx) {
        String name = ctx.IDENTIFIER() != null ? ctx.IDENTIFIER().getText() : null;
        List<Parameter> params = !isNull(ctx.paramList()) ? visitParameterList(ctx.paramList()) : new ArrayList<>();
        TypeExpression returnType = !isNull(ctx.typeExpr()) ? (TypeExpression) visit(ctx.typeExpr()) : null;
        BlockStatement body = (BlockStatement) visit(ctx.block());
        FunctionExpression expr = new FunctionExpression(name, params, returnType, body);

        setLocation(expr, ctx);

        return expr;
    }

    private NewExpression visitNewExpr(GaiaParser.NewExpressionContext ctx) {
        Expression target = visitPrimaryExpr(ctx.primary());
        NewExpression expr = new NewExpression(target);

        setLocation(expr, ctx);

        return expr;
    }

    @Override
    public TypeExpression visitTypeExpr(GaiaParser.TypeExprContext ctx) {
        TypeExpression typeExpression;

        typeExpression = buildPrimitive(ctx.TYPE_INT(), PrimitiveType.PrimitiveKind.INT, ctx);
        if (typeExpression != null) return typeExpression;

        typeExpression = buildPrimitive(ctx.TYPE_FLOAT(), PrimitiveType.PrimitiveKind.FLOAT, ctx);
        if (typeExpression != null) return typeExpression;

        typeExpression = buildPrimitive(ctx.TYPE_NUMBER(), PrimitiveType.PrimitiveKind.NUMBER, ctx);
        if (typeExpression != null) return typeExpression;

        typeExpression = buildPrimitive(ctx.TYPE_STRING(), PrimitiveType.PrimitiveKind.STRING, ctx);
        if (typeExpression != null) return typeExpression;

        typeExpression = buildPrimitive(ctx.TYPE_BOOL(), PrimitiveType.PrimitiveKind.BOOLEAN, ctx);
        if (typeExpression != null) return typeExpression;

        if (ctx.ONEOF() != null) {
            OneOfType type = new OneOfType(ctx.IDENTIFIER().getText());
            setLocation(type, ctx);
            return type;
        }

        if (ctx.IDENTIFIER() != null) {
            IdentifierType type = new IdentifierType(ctx.IDENTIFIER().getText());
            setLocation(type, ctx);
            return type;
        }

        if (ctx.typeExpr() != null && ctx.LBRACK() != null) {
            TypeExpression elementType = visitTypeExpr(ctx.typeExpr());
            ArrayType type = new ArrayType(elementType);
            setLocation(type, ctx);
            return type;
        }

        throw new IllegalStateException("Unknown type expression: " + ctx.getText());
    }

    private TypeExpression buildPrimitive(TerminalNode node, PrimitiveType.PrimitiveKind kind, ParserRuleContext ctx) {
        if (node == null) return null;

        PrimitiveType type = new PrimitiveType(kind);
        setLocation(type, ctx);

        return type;
    }

    private List<Parameter> visitParameterList(GaiaParser.ParamListContext ctx) {
        List<Parameter> params = new ArrayList<>();

        for (GaiaParser.ParamContext paramCtx : ctx.param()) {
            TypeExpression type = !isNull(paramCtx.typeExpr()) ? (TypeExpression) visit(paramCtx.typeExpr()) : null;
            String name = paramCtx.IDENTIFIER().getText();

            params.add(new Parameter(name, type));
        }

        return params;
    }

    private List<Expression> visitExpressionList(GaiaParser.ExprListContext ctx) {
        List<Expression> expression = new ArrayList<>();

        for (GaiaParser.ExpressionContext exprCtx : ctx.expression()) {
            expression.add(visitExpressionNode(exprCtx));
        }

        return expression;
    }

    private List<Expression> processArgumentList(GaiaParser.ArgumentListContext ctx) {
        List<Expression> args = new ArrayList<>();

        for (GaiaParser.ExpressionContext exprCtx : ctx.expression()) {
            args.add(visitExpressionNode(exprCtx));
        }

        return args;
    }

    private Visibility parseVisibility(TerminalNode publicNode, TerminalNode privateNode) {
        if (publicNode != null) return Visibility.PUBLIC;
        if (privateNode != null) return Visibility.PRIVATE;
        return Visibility.DEFAULT;
    }

    private String parseString(String s) {
        if (s == null) return null;

        if (s.length() >= 2) {
            char first = s.charAt(0);
            char last = s.charAt(s.length() - 1);

            if ((first == '"' && last == '"') || (first == '\'' && last == '\'')) {
                s = s.substring(1, s.length() - 1);
            }
        }

        StringBuilder sb = new StringBuilder(s.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '\\') {
                sb.append(c);
                continue;
            }

            if (i + 1 >= s.length()) {
                sb.append('\\');
                break;
            }

            char esc = s.charAt(++i);
            switch (esc) {
                case 'b' -> sb.append('\b');
                case 't' -> sb.append('\t');
                case 'n' -> sb.append('\n');
                case 'f' -> sb.append('\f');
                case 'r' -> sb.append('\r');
                case '"' -> sb.append('"');
                case '\'' -> sb.append('\'');
                case '\\' -> sb.append('\\');

                case 'u' -> {
                    if (i + 4 < s.length()) {
                        String hex = s.substring(i + 1, i + 5);
                        try {
                            int code = Integer.parseInt(hex, 16);
                            sb.append((char) code);
                            i += 4;
                        } catch (NumberFormatException e) {
                            sb.append("\\u");
                        }
                    } else {
                        sb.append("\\u");
                    }
                }

                case 'x' -> {
                    int start = i + 1;
                    int end = start;
                    while (end < s.length() && isHexChar(s.charAt(end)) && (end - start) < 2) {
                        end++;
                    }
                    if (end > start) {
                        String hex = s.substring(start, end);
                        try {
                            int code = Integer.parseInt(hex, 16);
                            sb.append((char) code);
                            i = end - 1;
                        } catch (NumberFormatException e) {
                            sb.append("\\x");
                        }
                    } else {
                        sb.append("\\x");
                    }
                }

                default -> sb.append(esc);
            }
        }

        return sb.toString();
    }

    private static boolean isHexChar(char c) {
        return (c >= '0' && c <= '9') ||
                (c >= 'a' && c <= 'f') ||
                (c >= 'A' && c <= 'F');
    }
}
