package me.minekid.gaia.host;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to mark a method as a host function callable from Gaia.
 *
 * <p>Methods annotated with @HostFunction will be automatically discovered and registered when the
 * host object is registered with the VM.
 *
 * <p>Example:
 *
 * <pre>
 * public class FileSystem {
 *     @HostFunction(name = "read")
 *     public GaiaValue read(GaiaValue path) {
 *         // implementation
 *     }
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HostFunction {
    /**
     * The name of the function as it will be called from Gaia. If empty, the method name will be
     * used.
     */
    String name() default "";

    /**
     * Description of the function for documentation.
     */
    String description() default "";
}
