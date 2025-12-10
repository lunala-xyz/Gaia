package me.minekid.gaia.ast.type;

import me.minekid.gaia.ast.AstVisitor;

public class OneOfType extends TypeExpression {
    private final String groupName;

    public OneOfType(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public <T> T accept(AstVisitor<T> visitor) {
        return visitor.visitOneOfType(this);
    }
}
