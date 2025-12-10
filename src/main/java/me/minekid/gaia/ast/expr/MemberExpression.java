package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class MemberExpression extends Expression {
    private final Expression object;
    private final String property;

    public MemberExpression(Expression object, String property) {
        this.object = object;
        this.property = property;
    }

    public Expression getObject() {
        return object;
    }

    public String getProperty() {
        return property;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitMemberExpression(this);
    }
}
