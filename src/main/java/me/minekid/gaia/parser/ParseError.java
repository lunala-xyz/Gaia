package me.minekid.gaia.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;

public record ParseError(String fileName, int line, int column, String message) {
    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return String.format("%s:%d:%d: %s", fileName, line, column, message);
    }
}
