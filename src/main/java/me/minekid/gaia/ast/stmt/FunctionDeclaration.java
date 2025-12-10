package me.minekid.gaia.ast.stmt;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.type.TypeExpression;

public class FunctionDeclaration extends Statement implements ClassDeclaration.ClassMember {
    private final Visibility visibility;
    private final String name;
    private final List<Parameter> parameters;
    private final TypeExpression returnType;
    private final BlockStatement body;

    public FunctionDeclaration(Visibility visibility, String name, List<Parameter> parameters, TypeExpression returnType, BlockStatement body) {
        this.visibility = visibility;
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
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

    public BlockStatement getBody() {
        return body;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitFunctionDeclaration(this);
    }
}
