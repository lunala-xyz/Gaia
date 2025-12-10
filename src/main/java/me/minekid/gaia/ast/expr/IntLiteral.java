package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class IntLiteral extends Expression {
    private final long value;

    public IntLiteral(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitIntLiteral(this);
    }
}
