package me.minekid.gaia.ast.stmt;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.expr.Expression;

public class FailStatement extends Statement {
    private final Expression value;

    public FailStatement(Expression value) {
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitFailStatement(this);
    }
}
