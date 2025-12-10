package me.minekid.gaia.ast.stmt;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;
import me.minekid.gaia.ast.type.TypeExpression;

public class InterfaceDeclaration extends Statement {
    private final String name;
    private final Visibility visibility;
    private final List<InterfaceMember> members;

    public InterfaceDeclaration(Visibility visibility, String name, List<InterfaceMember> members) {
        this.visibility = visibility;
        this.name = name;
        this.members = members;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public List<InterfaceMember> getMembers() {
        return members;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitInterfaceDeclaration(this);
    }

    public record InterfaceMember(TypeExpression type, String name, List<Parameter> parameters, TypeExpression returnType) {
        public boolean isMethod() {
            return parameters != null;
        }
    }
}
