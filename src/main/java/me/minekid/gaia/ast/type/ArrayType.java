package me.minekid.gaia.ast.type;

import me.minekid.gaia.ast.AstVisitor;

public class ArrayType extends TypeExpression {
    private final TypeExpression elementType;

    public ArrayType(TypeExpression elementType) {
        this.elementType = elementType;
    }

    public TypeExpression getElementType() {
        return elementType;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitArrayType(this);
    }
}
