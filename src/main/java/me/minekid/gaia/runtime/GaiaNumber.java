package me.minekid.gaia.runtime;

public class GaiaNumber extends GaiaValue {
    private final double value;
    private final boolean isInteger;

    public GaiaNumber(long value) {
        this.value = value;
        this.isInteger = true;
    }

    public GaiaNumber(double value) {
        this.value = value;
        this.isInteger = false;
    }

    public double getValue() {
        return value;
    }

    public long asLong() {
        return (long) value;
    }

    public double asDouble() {
        return value;
    }

    public boolean isInteger() {
        return isInteger;
    }

    @Override
    public String getTypeName() {
        return isInteger ? "int" : "float";
    }

    @Override
    public boolean isTruthy() {
        return value != 0;
    }

    @Override
    public String toGaiaString() {
        if (isInteger) return String.valueOf((long) value);
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GaiaNumber) return value == ((GaiaNumber) obj).value;
        return false;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
