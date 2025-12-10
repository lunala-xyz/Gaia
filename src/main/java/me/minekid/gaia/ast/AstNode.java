package me.minekid.gaia.ast;


public abstract class AstNode {
    private SourceLocation location;

    public SourceLocation getLocation() {
        return location;
    }

    public void setLocation(SourceLocation location) {
        this.location = location;
    }

    public abstract <T> T accept(AstVisitor<T> visitor);
}
