package me.minekid.gaia.host;

public class HostException extends RuntimeException {
    private final String errorType;

    public HostException(String message) {
        super(message);
        this.errorType = "HostError";
    }

    public HostException(String errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public HostException(String message, Throwable cause) {
        super(message, cause);
        this.errorType = "HostError";
    }

    public HostException(String errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }
}
