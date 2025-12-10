package me.minekid.gaia.ast.stmt;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;

public class BlockStatement extends Statement {
    private final List<Statement> statements;

    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitBlockStatement(this);
    }
}
