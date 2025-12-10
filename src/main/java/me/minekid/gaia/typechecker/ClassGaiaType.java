package me.minekid.gaia.typechecker;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a class type in Gaia.
 */
public class ClassGaiaType extends GaiaType {
    private final String name;
    private final Map<String, GaiaType> fields;
    private final Map<String, FunctionGaiaType> methods;

    public ClassGaiaType(String name) {
        this.name = name;
        this.fields = new HashMap<>();
        this.methods = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addField(String fieldName, GaiaType type) {
        fields.put(fieldName, type);
    }

    public void addMethod(String methodName, FunctionGaiaType type) {
        methods.put(methodName, type);
    }

    public GaiaType getFieldType(String fieldName) {
        return fields.get(fieldName);
    }

    public FunctionGaiaType getMethodType(String methodName) {
        return methods.get(methodName);
    }

    public boolean hasField(String fieldName) {
        return fields.containsKey(fieldName);
    }

    public boolean hasMethod(String methodName) {
        return methods.containsKey(methodName);
    }

    public Map<String, GaiaType> getFields() {
        return fields;
    }

    public Map<String, FunctionGaiaType> getMethods() {
        return methods;
    }

    @Override
    public String getTypeName() {
        return name;
    }

    @Override
    public boolean isAssignableFrom(GaiaType other) {
        if (other instanceof ClassGaiaType) return name.equals(((ClassGaiaType) other).name);
        return false;
    }
}
