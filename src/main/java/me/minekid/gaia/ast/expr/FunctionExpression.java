package me.minekid.gaia.ast.expr;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.stmt.BlockStatement;
import me.minekid.gaia.ast.stmt.Parameter;
import me.minekid.gaia.ast.type.TypeExpression;

public class FunctionExpression extends Expression {
    private final String name;
    private final BlockStatement body;
    private final TypeExpression returnType;
    private final List<Parameter> parameters;

    public FunctionExpression(
            String name, List<Parameter> parameters, TypeExpression returnType, BlockStatement body) {
        this.name = name;
        this.parameters = parameters;
        this.returnType = returnType;
        this.body = body;
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
        return visitor.visitFunctionExpression(this);
    }
}
