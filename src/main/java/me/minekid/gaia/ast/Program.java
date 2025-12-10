package me.minekid.gaia.ast;

import java.util.List;

import me.minekid.gaia.ast.stmt.Statement;
import me.minekid.gaia.ast.stmt.ImportStatement;

public class Program extends AstNode {
    private final List<ImportStatement> imports;
    private final List<Statement> statements;

    public Program(List<ImportStatement> imports, List<Statement> statements) {
        this.imports = imports;
        this.statements = statements;
    }

    public List<ImportStatement> getImports() {
        return imports;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitProgram(this);
    }
}
