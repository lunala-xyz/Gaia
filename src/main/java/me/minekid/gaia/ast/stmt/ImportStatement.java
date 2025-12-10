package me.minekid.gaia.ast.stmt;

import me.minekid.gaia.ast.AstVisitor;

public class ImportStatement extends Statement {
    private final String path;
    private final String alias;

    public ImportStatement(String path, String alias) {
        this.path = path;
        this.alias = alias;
    }

    public String getPath() {
        return path;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitImportStatement(this);
    }
}
