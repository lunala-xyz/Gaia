package me.minekid.gaia.typechecker;

public class ArrayGaiaType extends GaiaType {
    private final GaiaType elementType;

    public ArrayGaiaType(GaiaType elementType) {
        this.elementType = elementType;
    }

    public GaiaType getElementType() {
        return elementType;
    }

    @Override
    public String getTypeName() {
        return elementType.getTypeName() + "[]";
    }

    @Override
    public boolean isAssignableFrom(GaiaType other) {
        if (other instanceof ArrayGaiaType otherArray) return elementType.isAssignableFrom(otherArray.elementType);
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayGaiaType) return elementType.equals(((ArrayGaiaType) obj).elementType);
        return false;
    }

    @Override
    public int hashCode() {
        return elementType.hashCode() * 31 + "[]".hashCode();
    }
}
