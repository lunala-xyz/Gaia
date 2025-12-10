package me.minekid.gaia.host;

import java.util.Map;
import java.util.HashMap;

import me.minekid.gaia.runtime.GaiaObject;
import me.minekid.gaia.vm.GaiaInterpreter;

/**
 * Registry for managing host objects and functions.
 *
 * <p>This class provides methods to register host objects (Java objects with @HostFunction
 * annotated methods) and make them available to Gaia code.
 */
public class HostRegistry {
    private final Map<String, Object> hostObjects;
    private final Map<String, AnnotatedHostObjectAdapter> adapters;

    public HostRegistry() {
        this.hostObjects = new HashMap<>();
        this.adapters = new HashMap<>();
    }

    /**
     * Register a host object with a given name.
     *
     * @param name       the name under which the object will be accessible in Gaia
     * @param hostObject the Java object with @HostFunction annotated methods
     */
    public void registerHostObject(String name, Object hostObject) {
        hostObjects.put(name, hostObject);
        adapters.put(name, new AnnotatedHostObjectAdapter(hostObject));
    }

    /**
     * Get a registered host object by name.
     */
    public Object getHostObject(String name) {
        return hostObjects.get(name);
    }

    /**
     * Get the adapter for a host object.
     */
    public AnnotatedHostObjectAdapter getAdapter(String name) {
        return adapters.get(name);
    }

    /**
     * Check if a host object is registered.
     */
    public boolean hasHostObject(String name) {
        return hostObjects.containsKey(name);
    }

    /**
     * Get all registered host object names.
     */
    public Iterable<String> getHostObjectNames() {
        return hostObjects.keySet();
    }

    /**
     * Register all host objects with an interpreter. Each host object will be available as a global
     * variable with its name.
     */
    public void registerWithInterpreter(GaiaInterpreter interpreter) {
        for (Map.Entry<String, AnnotatedHostObjectAdapter> entry : adapters.entrySet()) {
            String name = entry.getKey();
            AnnotatedHostObjectAdapter adapter = entry.getValue();

            // Create a Gaia object representing the host object
            GaiaObject hostObj = adapter.createHostObject();
            interpreter.setGlobal(name, hostObj);
        }
    }

    /**
     * Create a Gaia object representing a host object.
     */
    public GaiaObject createGaiaObject(String name) {
        AnnotatedHostObjectAdapter adapter = adapters.get(name);
        if (adapter == null) {
            throw new IllegalArgumentException("No host object registered with name: " + name);
        }
        return adapter.createHostObject();
    }

    /**
     * Register a host object instance with the VM.
     *
     * <p>This method scans the host instance for @HostFunction annotations and registers each
     * annotated method as a callable function in Gaia.
     *
     * @param hostInstance the Java object to register
     * @param name         the name under which the object will be accessible
     * @param interpreter  the Gaia interpreter
     */
    public static void register(Object hostInstance, String name, GaiaInterpreter interpreter) {
        AnnotatedHostObjectAdapter adapter = new AnnotatedHostObjectAdapter(hostInstance);
        interpreter.setGlobal(name, adapter.createHostObject());
    }

    /**
     * Register a host object's methods directly (without a namespace).
     *
     * <p>Each @HostFunction annotated method will be registered as a global function.
     *
     * @param hostInstance the Java object to register
     * @param interpreter  the Gaia interpreter
     */
    public static void registerGlobal(Object hostInstance, GaiaInterpreter interpreter) {
        AnnotatedHostObjectAdapter adapter = new AnnotatedHostObjectAdapter(hostInstance);
        adapter.registerWith(interpreter);
    }
}
