package me.minekid.gaia.ast.stmt;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.expr.Expression;
import me.minekid.gaia.ast.type.TypeExpression;

public class ConstDeclaration extends Statement implements ClassDeclaration.ClassMember {
    private final Visibility visibility;
    private final String name;
    private final TypeExpression type;
    private final Expression initializer;

    public ConstDeclaration(
            Visibility visibility, String name, TypeExpression type, Expression initializer) {
        this.visibility = visibility;
        this.name = name;
        this.type = type;
        this.initializer = initializer;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public TypeExpression getType() {
        return type;
    }

    public Expression getInitializer() {
        return initializer;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitConstDeclaration(this);
    }
}
