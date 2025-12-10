package me.minekid.gaia.ast.stmt;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.type.TypeExpression;

public class CallableDeclaration extends Statement {
    private final Visibility visibility;
    private final String name;
    private final List<Parameter> parameters;
    private final TypeExpression returnType;

    public CallableDeclaration(Visibility visibility, String name, List<Parameter> parameters, TypeExpression returnType) {
        this.visibility = visibility;
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public TypeExpression getReturnType() {
        return returnType;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitCallableDeclaration(this);
    }
}
