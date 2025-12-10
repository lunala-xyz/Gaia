package me.minekid.gaia.runtime;

public class GaiaString extends GaiaValue {
    private final String value;

    public GaiaString(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getTypeName() {
        return "string";
    }

    @Override
    public boolean isTruthy() {
        return !value.isEmpty();
    }

    @Override
    public String toGaiaString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GaiaString) return value.equals(((GaiaString) obj).value);
        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
