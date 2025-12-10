package me.minekid.gaia.ast.type;

import me.minekid.gaia.ast.AstVisitor;

public class IdentifierType extends TypeExpression {
    private final String name;

    public IdentifierType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitIdentifierType(this);
    }
}
