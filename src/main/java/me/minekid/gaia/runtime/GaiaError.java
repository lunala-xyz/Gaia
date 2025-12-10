package me.minekid.gaia.runtime;

public class GaiaError extends GaiaValue {
    private final String message;
    private final String errorType;

    public GaiaError(String message) {
        this.message = message;
        this.errorType = "Error";
    }

    public GaiaError(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorType() {
        return errorType;
    }

    @Override
    public String getTypeName() {
        return errorType;
    }

    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public boolean isTruthy() {
        return false;
    }

    @Override
    public String toGaiaString() {
        return errorType + ": " + message;
    }
}
