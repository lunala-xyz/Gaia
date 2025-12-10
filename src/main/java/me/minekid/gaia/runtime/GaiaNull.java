package me.minekid.gaia.runtime;

public class GaiaNull extends GaiaValue {
    public static final GaiaNull INSTANCE = new GaiaNull();

    private GaiaNull() {}

    @Override
    public String getTypeName() {
        return "null";
    }

    @Override
    public boolean isTruthy() {
        return false;
    }

    @Override
    public String toGaiaString() {
        return "null";
    }
}
