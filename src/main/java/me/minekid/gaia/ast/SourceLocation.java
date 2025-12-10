package me.minekid.gaia.ast;

import org.jetbrains.annotations.NotNull;

public record SourceLocation(int line, int column, int startIndex, int endIndex, String source) {
    @Override
    public @NotNull String toString() {
        return String.format("line %d, col %d", line, column);
    }
}
