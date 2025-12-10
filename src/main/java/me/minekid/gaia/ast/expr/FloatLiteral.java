package me.minekid.gaia.ast.expr;

import me.minekid.gaia.ast.AstVisitor;

public class FloatLiteral extends Expression {
    private final double value;

    public FloatLiteral(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitFloatLiteral(this);
    }
}
