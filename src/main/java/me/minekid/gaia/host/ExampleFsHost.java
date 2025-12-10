package me.minekid.gaia.host;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import me.minekid.gaia.runtime.*;

/**
 * Example host object providing file system operations.
 *
 * <p>This demonstrates how to create a host object with @HostFunction annotated methods that can be
 * called from Gaia code.
 *
 * <p>Usage in Gaia:
 *
 * <pre>
 * let content = fs.read("file.txt");
 * if (content.isError()) {
 *     // handle error
 * }
 *
 * let exists = fs.exists("file.txt");
 * </pre>
 */
public record ExampleFsHost(Path basePath) {
    public ExampleFsHost() {
        this(Paths.get("").toAbsolutePath());
    }

    /**
     * Read the contents of a file.
     *
     * @param pathArg the path to the file (as GaiaValue)
     * @return the file contents as a string, or an Error if the file cannot be read
     */
    @HostFunction(name = "read", description = "Read the contents of a file")
    public GaiaValue read(GaiaValue pathArg) {
        if (!(pathArg instanceof GaiaString)) {
            return new GaiaError("TypeError", "Expected string argument for path");
        }

        String pathStr = ((GaiaString) pathArg).getValue();
        Path path = basePath.resolve(pathStr);

        try {
            String content = Files.readString(path);
            return new GaiaString(content);
        } catch (IOException e) {
            return new GaiaError("IOError", "Failed to read file: " + e.getMessage());
        } catch (SecurityException e) {
            return new GaiaError("PermissionError", "Access denied: " + e.getMessage());
        }
    }

    /**
     * Check if a file exists.
     *
     * @param pathArg the path to check (as GaiaValue)
     * @return true if the file exists, false otherwise
     */
    @HostFunction(name = "exists", description = "Check if a file exists")
    public GaiaValue exists(GaiaValue pathArg) {
        if (!(pathArg instanceof GaiaString)) {
            return new GaiaError("TypeError", "Expected string argument for path");
        }

        String pathStr = ((GaiaString) pathArg).getValue();
        Path path = basePath.resolve(pathStr);

        return GaiaBoolean.of(Files.exists(path));
    }

    /**
     * Write content to a file.
     *
     * @param pathArg    the path to write to
     * @param contentArg the content to write
     * @return true on success, or an Error on failure
     */
    @HostFunction(name = "write", description = "Write content to a file")
    public GaiaValue write(GaiaValue pathArg, GaiaValue contentArg) {
        if (!(pathArg instanceof GaiaString)) {
            return new GaiaError("TypeError", "Expected string argument for path");
        }
        if (!(contentArg instanceof GaiaString)) {
            return new GaiaError("TypeError", "Expected string argument for content");
        }

        String pathStr = ((GaiaString) pathArg).getValue();
        String content = ((GaiaString) contentArg).getValue();
        Path path = basePath.resolve(pathStr);

        try {
            Files.writeString(path, content);
            return GaiaBoolean.TRUE;
        } catch (IOException e) {
            return new GaiaError("IOError", "Failed to write file: " + e.getMessage());
        } catch (SecurityException e) {
            return new GaiaError("PermissionError", "Access denied: " + e.getMessage());
        }
    }

    /**
     * Delete a file.
     *
     * @param pathArg the path to delete
     * @return true on success, or an Error on failure
     */
    @HostFunction(name = "delete", description = "Delete a file")
    public GaiaValue delete(GaiaValue pathArg) {
        if (!(pathArg instanceof GaiaString)) {
            return new GaiaError("TypeError", "Expected string argument for path");
        }

        String pathStr = ((GaiaString) pathArg).getValue();
        Path path = basePath.resolve(pathStr);

        try {
            boolean deleted = Files.deleteIfExists(path);
            return GaiaBoolean.of(deleted);
        } catch (IOException e) {
            return new GaiaError("IOError", "Failed to delete file: " + e.getMessage());
        } catch (SecurityException e) {
            return new GaiaError("PermissionError", "Access denied: " + e.getMessage());
        }
    }
}
