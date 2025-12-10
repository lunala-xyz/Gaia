package me.minekid.gaia.ast.expr;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;

public class ObjectLiteral extends Expression {
    private final List<Property> properties;

    public ObjectLiteral(List<Property> properties) {
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitObjectLiteral(this);
    }

    public static class Property {
        private final String key;
        private final Expression value;

        public Property(String key, Expression value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Expression getValue() {
            return value;
        }
    }
}
