package me.minekid.gaia.typechecker;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;


public class Scope {
    private final Scope parent;
    private final Map<String, Symbol> symbols;
    private final ScopeKind kind;

    public Scope(Scope parent, ScopeKind kind) {
        this.parent = parent;
        this.symbols = new HashMap<>();
        this.kind = kind;
    }

    public Scope getParent() {
        return parent;
    }

    public boolean define(Symbol symbol) {
        if (symbols.containsKey(symbol.name())) return false;
        
        symbols.put(symbol.name(), symbol);
        
        return true;
    }

    public Symbol lookup(String name) {
        Symbol symbol = symbols.get(name);
        
        if (symbol != null) return symbol;
        if (!isNull(parent)) return parent.lookup(name);
        
        return null;
    }

    public boolean isInsideFunction() {
        if (kind == ScopeKind.FUNCTION) return true;
        if (!isNull(parent)) return parent.isInsideFunction();
        
        return false;
    }

    public GaiaType getEnclosingFunctionReturnType() {
        if (kind == ScopeKind.FUNCTION && symbols.containsKey("$return")) return symbols.get("$return").type();
        
        if (!isNull(parent)) return parent.getEnclosingFunctionReturnType();
        
        return null;
    }

    public enum ScopeKind {
        GLOBAL,
        FUNCTION,
        BLOCK,
        CLASS
    }
}
