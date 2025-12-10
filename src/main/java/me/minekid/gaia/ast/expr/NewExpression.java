package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class NewExpression extends Expression {
    private final Expression target;

    public NewExpression(Expression target) {
        this.target = target;
    }

    public Expression getTarget() {
        return target;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitNewExpression(this);
    }
}
