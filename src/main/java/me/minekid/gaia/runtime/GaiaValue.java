package me.minekid.gaia.runtime;

public abstract class GaiaValue {
    public abstract String getTypeName();

    public boolean isTruthy() {
        return true;
    }

    public boolean isError() {
        return false;
    }

    public abstract String toGaiaString();

    @Override
    public String toString() {
        return toGaiaString();
    }
}
