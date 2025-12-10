package me.minekid.gaia.typechecker;

import me.minekid.gaia.ast.SourceLocation;

import org.jetbrains.annotations.NotNull;

public record TypeError(String message, SourceLocation location) {

    @Override
    public @NotNull String toString() {
        if (location != null) {
            return String.format("Type error at %s: %s", location, message);
        }
        return "Type error: " + message;
    }
}
