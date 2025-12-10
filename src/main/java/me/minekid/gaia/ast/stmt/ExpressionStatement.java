package me.minekid.gaia.ast.stmt;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.expr.Expression;

public class ExpressionStatement extends Statement {
    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitExpressionStatement(this);
    }
}
