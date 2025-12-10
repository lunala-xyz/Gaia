package me.minekid.gaia.ast.stmt;

import me.minekid.gaia.ast.type.TypeExpression;

public record Parameter(String name, TypeExpression type) {
}
