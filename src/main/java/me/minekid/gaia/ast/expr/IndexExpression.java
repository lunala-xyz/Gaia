package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class IndexExpression extends Expression {
    private final Expression object;
    private final Expression index;

    public IndexExpression(Expression object, Expression index) {
        this.object = object;
        this.index = index;
    }

    public Expression getObject() {
        return object;
    }

    public Expression getIndex() {
        return index;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitIndexExpression(this);
    }
}
