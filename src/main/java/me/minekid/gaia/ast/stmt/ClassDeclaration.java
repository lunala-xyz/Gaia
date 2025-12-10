package me.minekid.gaia.ast.stmt;

import java.util.List;

import me.minekid.gaia.ast.AstVisitor;

public class ClassDeclaration extends Statement {
    private final Visibility visibility;
    private final String name;
    private final List<ClassMember> members;

    public ClassDeclaration(Visibility visibility, String name, List<ClassMember> members) {
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

    public List<ClassMember> getMembers() {
        return members;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitClassDeclaration(this);
    }

    public interface ClassMember {
    }
}
