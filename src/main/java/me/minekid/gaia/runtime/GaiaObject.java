package me.minekid.gaia.runtime;

import java.util.Map;
import java.util.HashMap;

public class GaiaObject extends GaiaValue {
    private final String className;
    private final Map<String, GaiaValue> fields;

    public GaiaObject(String className) {
        this.className = className;
        this.fields = new HashMap<>();
    }

    public String getClassName() {
        return className;
    }

    public GaiaValue getField(String name) {
        return fields.getOrDefault(name, GaiaNull.INSTANCE);
    }

    public void setField(String name, GaiaValue value) {
        fields.put(name, value);
    }

    public boolean hasField(String name) {
        return fields.containsKey(name);
    }

    public Map<String, GaiaValue> getFields() {
        return fields;
    }

    @Override
    public String getTypeName() {
        return className;
    }

    @Override
    public String toGaiaString() {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append("{");

        boolean first = true;

        for (Map.Entry<String, GaiaValue> entry : fields.entrySet()) {
            if (!first) sb.append(", ");
            first = false;
            sb.append(entry.getKey()).append(": ").append(entry.getValue().toGaiaString());
        }

        sb.append("}");

        return sb.toString();
    }
}
