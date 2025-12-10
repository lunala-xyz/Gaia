package me.minekid.gaia.ast.type;

import me.minekid.gaia.ast.AstVisitor;

public class PrimitiveType extends TypeExpression {
    private final PrimitiveKind kind;

    public PrimitiveType(PrimitiveKind kind) {
        this.kind = kind;
    }

    public PrimitiveKind getKind() {
        return kind;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitPrimitiveType(this);
    }

    public enum PrimitiveKind {
        INT("int"),
        FLOAT("float"),
        NUMBER("number"),
        STRING("string"),
        BOOLEAN("boolean");

        private final String keyword;

        PrimitiveKind(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }
    }
}
