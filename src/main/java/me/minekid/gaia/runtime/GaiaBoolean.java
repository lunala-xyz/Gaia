package me.minekid.gaia.runtime;

public class GaiaBoolean extends GaiaValue {
    public static final GaiaBoolean TRUE = new GaiaBoolean(true);
    public static final GaiaBoolean FALSE = new GaiaBoolean(false);

    private final boolean value;

    private GaiaBoolean(boolean value) {
        this.value = value;
    }

    public static GaiaBoolean of(boolean value) {
        return value ? TRUE : FALSE;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String getTypeName() {
        return "boolean";
    }

    @Override
    public boolean isTruthy() {
        return value;
    }

    @Override
    public String toGaiaString() {
        return String.valueOf(value);
    }
}
