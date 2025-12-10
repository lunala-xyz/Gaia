package me.minekid.gaia.ast.stmt;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.expr.Expression;

public class ForStatement extends Statement {
    private final ForInit initializer;
    private final Expression condition;
    private final List<Expression> update;
    private final Statement body;

    public ForStatement(ForInit initializer, Expression condition, List<Expression> update, Statement body) {
        this.initializer = initializer;
        this.condition = condition;
        this.update = update;
        this.body = body;
    }

    public ForInit getInitializer() {
        return initializer;
    }

    public Expression getCondition() {
        return condition;
    }

    public List<Expression> getUpdate() {
        return update;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitForStatement(this);
    }


    public interface ForInit {
    }

    public record ExpressionListInit(List<Expression> expressions) implements ForInit {
    }

    public record VariableInit(VariableDeclaration declaration) implements ForInit {
    }
}
