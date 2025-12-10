package me.minekid.gaia.runtime;

import java.util.ArrayList;
import java.util.List;

public class GaiaArray extends GaiaValue {
    private final List<GaiaValue> elements;

    public GaiaArray() {
        this.elements = new ArrayList<>();
    }

    public GaiaArray(List<GaiaValue> elements) {
        this.elements = new ArrayList<>(elements);
    }

    public List<GaiaValue> getElements() {
        return elements;
    }

    public GaiaValue get(int index) {
        if (index < 0 || index >= elements.size()) return new GaiaError("Index out of bounds: " + index);
        return elements.get(index);
    }

    public void set(int index, GaiaValue value) {
        if (index >= 0 && index < elements.size()) {
            elements.set(index, value);
        }
    }

    public void add(GaiaValue value) {
        elements.add(value);
    }

    public int length() {
        return elements.size();
    }

    @Override
    public String getTypeName() {
        return "array";
    }

    @Override
    public boolean isTruthy() {
        return !elements.isEmpty();
    }

    @Override
    public String toGaiaString() {
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(elements.get(i).toGaiaString());
        }

        sb.append("]");
        return sb.toString();
    }
}
