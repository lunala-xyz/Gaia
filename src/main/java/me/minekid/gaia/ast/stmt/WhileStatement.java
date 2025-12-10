package me.minekid.gaia.ast.stmt;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.expr.Expression;

public class WhileStatement extends Statement {
    private final Expression condition;
    private final Statement body;

    public WhileStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitWhileStatement(this);
    }
}
