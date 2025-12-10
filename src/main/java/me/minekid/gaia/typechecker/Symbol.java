package me.minekid.gaia.typechecker;

public record Symbol(String name, GaiaType type, SymbolKind kind, boolean mutable) {
    public enum SymbolKind {
        VARIABLE,
        CONSTANT,
        FUNCTION,
        CLASS,
        INTERFACE,
        PARAMETER
    }
}
