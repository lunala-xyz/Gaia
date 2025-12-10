package me.minekid.gaia.ast.expr;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;

public class ArrayLiteral extends Expression {
    private final List<Expression> elements;

    public ArrayLiteral(List<Expression> elements) {
        this.elements = elements;
    }

    public List<Expression> getElements() {
        return elements;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitArrayLiteral(this);
    }
}
