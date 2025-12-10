package me.minekid.gaia.typechecker;

/**
 * Represents a primitive type in Gaia (int, float, number, string, boolean).
 */
public class PrimitiveGaiaType extends GaiaType {
    public static final PrimitiveGaiaType INT = new PrimitiveGaiaType("int");
    public static final PrimitiveGaiaType FLOAT = new PrimitiveGaiaType("float");
    public static final PrimitiveGaiaType NUMBER = new PrimitiveGaiaType("number");
    public static final PrimitiveGaiaType STRING = new PrimitiveGaiaType("string");
    public static final PrimitiveGaiaType BOOLEAN = new PrimitiveGaiaType("boolean");
    public static final PrimitiveGaiaType VOID = new PrimitiveGaiaType("void");
    public static final PrimitiveGaiaType NULL = new PrimitiveGaiaType("null");

    private final String name;

    private PrimitiveGaiaType(String name) {
        this.name = name;
    }

    @Override
    public String getTypeName() {
        return name;
    }

    @Override
    public boolean isAssignableFrom(GaiaType other) {

        if (other == this) return true;
        if (this == NUMBER) return other == INT || other == FLOAT;
        if (this == FLOAT) return other == INT;

        return false;
    }

    public static PrimitiveGaiaType fromKeyword(String keyword) {
        return switch (keyword) {
            case "int" -> INT;
            case "float" -> FLOAT;
            case "number" -> NUMBER;
            case "string" -> STRING;
            case "boolean" -> BOOLEAN;
            case "void" -> VOID;
            default -> throw new IllegalArgumentException("Unknown primitive type: " + keyword);
        };
    }
}
